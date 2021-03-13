package com.nc.ocp.nio.files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import com.nc.ocp.nio.exceptions.OcpNioException;

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
    @DisplayName("Check files existense")
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
        void fileDoesntExistsTest() {
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
}

