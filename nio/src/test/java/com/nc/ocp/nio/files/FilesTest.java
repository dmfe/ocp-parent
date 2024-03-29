package com.nc.ocp.nio.files;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nc.ocp.nio.exceptions.OcpNioException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

/**
 * FilesTest
 */
@RunWith(JUnitPlatform.class)
class FilesTest {

    private FilesSample fs = new FilesSample();

    @Nested
    @DisplayName("Check files existence")
    class CheckFilesExistence {
        @DisplayName("Directory exists")
        @Test
        void directoryExistsTest() {
            assertTrue(fs.checkFile("horse"));
        }

        @DisplayName("File exists")
        @Test
        void fileExistsTest() {
            assertTrue(fs.checkFile("horse/food.txt"));
        }

        @DisplayName("File doesn't exist")
        @Test
        void fileNotExistsTest() {
            assertFalse(fs.checkFile("horse/somefood.txt"));
        }
    }

    @Nested
    @DisplayName("Compare two files/directories")
    class CompareTwoEntities {
        @DisplayName("Directories are the same")
        @Test
        void directoriesAreTheSameTest() {
            assertTrue(fs.isSameFiles("test-data/horse", "test-data/steed"));
        }

        @DisplayName("Directories are different")
        @Test
        void directoriesAreDifferentTest() {
            assertFalse(fs.isSameFiles("test-data/horse", "test-data/zebra"));
        }

        @DisplayName("Files are the same")
        @Test
        void filesAreTheSameTest() {
            assertTrue(fs.isSameFiles("test-data/horse/food.txt", "test-data/zebra/food.source"));
        }

        @DisplayName("Files are different")
        @Test
        void filesAreDifferent() {
            assertFalse(fs.isSameFiles("test-data/horse/food.txt", "test-data/horse/stalefood.txt"));
        }

        @DisplayName("One of the files is not exist")
        @Test
        void fileIsNotExists() {
            OcpNioException ex = assertThrows(OcpNioException.class, () -> {
                fs.isSameFiles("test-data/horse/foot.txt", "test-data/horse/stalefood.txt");
            });

            assertEquals("Error while comparing files: test-data/horse/foot.txt", ex.getMessage());
        }
    }

    @Nested
    @DisplayName("Create directories")
    class CreateDirectories {

        @DisplayName("Create directory when parent directory exists")
        @Test
        void createDirectoryWhenParentExists() {
            String dirName = UUID.randomUUID().toString();
            fs.createDirectory("test-data/horse/" + dirName);

            assertTrue(fs.checkFile("horse/" + dirName));
        }

        @DisplayName("Create directory when parent directory doesn't exist")
        @Test
        void createDirectoryWhenParentNotExists() {
            OcpNioException ex = assertThrows(OcpNioException.class, () -> {
                fs.createDirectory("test-data/something/ape");
            });

            assertEquals("Error while creation directory: test-data/something/ape", ex.getMessage());
        }

        @DisplayName("Create directories")
        @Test
        void createDirectories() {
            String path = UUID.randomUUID().toString() + "/" + UUID.randomUUID().toString();
            fs.createDirectories("test-data/horse/" + path);

            assertTrue(fs.checkFile("horse/" + path));
        }
    }

    @DisplayName("Copy two files with buffered streams")
    @Test
    void copyTwoFiles() {
        List<String> expectedText = List.of("This is the sample file", "blablablah");

        fs.copyWithBufferedStreams();

        assertEquals(expectedText, fs.readFile("test-data/horse/b"));
    }

    @DisplayName("Set last modified time.")
    @Test
    void setLastModifiedTest() {

        long currentMillis = System.currentTimeMillis();
        String fileName = "test-data/horse/food.txt";

        fs.setLastModified(fileName, currentMillis);

        assertEquals(currentMillis, fs.getLastModified(fileName));
    }

    /**
     * You should run this under superuser permissions
     **/
    @DisplayName("Set owner.")
    @Test
    @Disabled
    void setOwnerTest() throws Exception {
        String fileName = "test-data/chicken/feathers_" + UUID.randomUUID().toString() + ".txt";
        String ownerName = "nobody";

        UserPrincipal expectedOwner = FileSystems.getDefault().getUserPrincipalLookupService()
                .lookupPrincipalByName(ownerName);

        fs.setOwner(fileName, ownerName);
        Path path = Paths.get(fileName);
        UserPrincipal actualOwner = Files.getOwner(path);

        assertEquals(expectedOwner, actualOwner);
    }

    @DisplayName("Get basic file attributes.")
    @Test
    void getBasicAttributesTest() {
        String filename = "test-data/turtles/sea.txt";

        BasicFileAttributes actualAttributes = fs.getBasicFileAttributes(filename);

        assertAll(
                () -> assertFalse(actualAttributes.isDirectory()),
                () -> assertTrue(actualAttributes.isRegularFile()),
                () -> assertFalse(actualAttributes.isSymbolicLink()),
                () -> assertFalse(actualAttributes.isOther()),
                () -> assertEquals(166, actualAttributes.size()),
                () -> assertEquals("(dev=831,ino=11539057)", actualAttributes.fileKey().toString())
        );
    }

    @DisplayName("Modify basic file attribute.")
    @Test
    void modifyBasicFileAttributeTest() throws Exception {
        String filename = "test-data/turtles/ocean.txt";
        long delta = 10_000;

        long oldLastModifiedTimeMillis = fs.modifyLastModyfiedTime(filename, delta);

        long expectedTime = oldLastModifiedTimeMillis + delta;
        long actualTime = Files.readAttributes(Paths.get(filename), BasicFileAttributes.class)
                .lastModifiedTime().toMillis();

        assertEquals(expectedTime, actualTime);
    }
}
