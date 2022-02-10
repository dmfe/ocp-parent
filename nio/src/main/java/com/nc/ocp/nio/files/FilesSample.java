package com.nc.ocp.nio.files;

import static com.nc.ocp.nio.NioConstants.DATA_FOLDER;
import static com.nc.ocp.nio.exceptions.OcpNioUtils.produceOcpNioException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/**
 * Files
 */
@Slf4j
class FilesSample {

    boolean checkFile(String resource) {
        Path cpData = Paths.get(DATA_FOLDER + "/" + resource);
        return Files.exists(cpData);
    }

    boolean isSameFiles(String fileOne, String fileTwo) {
        try {
            Path first = Paths.get(fileOne);
            Path second = Paths.get(fileTwo);

            return Files.isSameFile(first, second);
        } catch (IOException ex) {
            throw produceOcpNioException("Error while comparing files: ", ex);
        }
    }

    void createDirectory(String dirName) {
        try {
            Files.createDirectory(Paths.get(dirName));
        } catch (IOException ex) {
            throw produceOcpNioException("Error while creation directory: ", ex);
        }
    }

    void createDirectories(String path) {
        try {
            Files.createDirectories(Paths.get(path));
        } catch (IOException ex) {
            throw produceOcpNioException("Error while creation directory: ", ex);
        }
    }

    void copyWithBufferedStreams() {
        Path source = Paths.get("test-data/horse/a");
        Path dest = Paths.get("test-data/horse/b");

        try (BufferedReader reader = Files.newBufferedReader(source, Charset.forName("US-ASCII"));
             BufferedWriter writer = Files.newBufferedWriter(dest, Charset.forName("UTF-16"))) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null)
                writer.write(currentLine + "\n");

        } catch (IOException ex) {
            throw produceOcpNioException("Error while copy directories: ", ex);
        }
    }

    List<String> readFile(String fileName) {
        Path path = Paths.get(fileName);

        try {
            // Be aware that entire file is read when readAllLines() is called(),
            // with the resulting String array storing all of the contents of the
            // file in memory at once. Therefore, if the file is significantly large,
            // you may encounter an OutOfMemoryError trying to load all of it into
            // memory.
            return Files.readAllLines(path, Charset.forName("UTF-16"));
        } catch (IOException ex) {
            throw produceOcpNioException("Error while reading file: ", ex);
        }
    }

    void setLastModified(String fileName, long epochMillis) {
        try {
            Path path = Paths.get(fileName);
            Files.setLastModifiedTime(path, FileTime.fromMillis(epochMillis));
        } catch (IOException ex) {
            throw produceOcpNioException("Error while setting last modified time: ", ex);
        }
    }

    Long getLastModified(String fileName) {
        try {
            Path path = Paths.get(fileName);
            return Files.getLastModifiedTime(path).toMillis();
        } catch (IOException ex) {
            throw produceOcpNioException("Error while getting last modified time: ", ex);
        }
    }

    void setOwner(String fileName, String ownerName) {
        try {
            Path path = Paths.get(fileName);
            Files.createFile(path);
            log.info(fileName + " created (owner: " + Files.getOwner(path).getName() + ")");

            Set<PosixFilePermission> perms = Files.readAttributes(path, PosixFileAttributes.class).permissions();
            perms.add(PosixFilePermission.GROUP_WRITE);
            perms.add(PosixFilePermission.OTHERS_WRITE);
            Files.setPosixFilePermissions(path, perms);

            UserPrincipal owner = path.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName(ownerName);
            Files.setOwner(path, owner);
            log.info(fileName + " changed owner (owner: " + Files.getOwner(path).getName() + ")");
        } catch (IOException ex) {
            throw produceOcpNioException("Error while setting owner: ", ex);
        }
    }

    BasicFileAttributes getBasicFileAttributes(String filename) {
        try {
            Path path = Paths.get(filename);

            BasicFileAttributes data = Files.readAttributes(path, BasicFileAttributes.class);
            log.info("Attributes of {}", path.getFileName());
            log.info("Is path a directory {}", data.isDirectory());
            log.info("Is path a regular file {}", data.isRegularFile());
            log.info("Is path a symbolic link {}", data.isSymbolicLink());
            log.info("Path not file, directory, nor symbolic link {}", data.isOther());

            log.info("Size {}", data.size());
            log.info("Creation date/time {}", data.creationTime());
            log.info("Last modified date/time {}", data.lastModifiedTime());
            log.info("Last accessed date/time {}", data.lastAccessTime());
            log.info("Unique file identifier (if available) {}", data.fileKey());

            return data;
        } catch (IOException ex) {
            throw produceOcpNioException("Error while getting basic file attributes: ", ex);
        }
    }

    long modifyLastModyfiedTime(String filename, long timeMillis) {
        try {
            Path path = Paths.get(filename);

            BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);
            BasicFileAttributes data = view.readAttributes();

            FileTime lastModifiedTime = data.lastModifiedTime();
            log.info("{} last modified time: {}", path.getFileName(), lastModifiedTime);

            FileTime newLastModifiedTime = FileTime.fromMillis(lastModifiedTime.toMillis() + timeMillis);
            log.info("{} new last modified time: {}", path.getFileName(), newLastModifiedTime);

            view.setTimes(newLastModifiedTime, null, null);

            return lastModifiedTime.toMillis();
        } catch (IOException ex) {
            throw produceOcpNioException("Error while modifying basic file attributes: ", ex);
        }
    }
}
