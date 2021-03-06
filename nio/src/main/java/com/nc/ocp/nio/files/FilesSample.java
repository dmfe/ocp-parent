package com.nc.ocp.nio.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.List;

import com.nc.ocp.nio.exceptions.OcpNioException;

import lombok.extern.log4j.Log4j;

/**
 * Files
 */
@Log4j
public class FilesSample {

    private static final String DATA_FOLDER = "./test-data";

    public boolean checkFile(String resource) {
        Path cpData = Paths.get(DATA_FOLDER + "/" + resource);
        return Files.exists(cpData);
    }

    public boolean isSameFiles(String fileOne, String fileTwo) {
        try {
            Path first = Paths.get(fileOne);
            Path second = Paths.get(fileTwo);

            return Files.isSameFile(first, second);
        } catch (IOException ex) {
            String msg = "Error while comparing files: " + ex.getLocalizedMessage();
            log.error(msg, ex);
            throw new OcpNioException(msg, ex);
        }
    }

    public void createDirectory(String dirName) {
        try {
            Files.createDirectory(Paths.get(dirName));
        } catch(IOException ex) {
            String msg = "Error while creation directory: " + ex.getLocalizedMessage();
            log.error(msg, ex);
            throw new OcpNioException(msg, ex);
        }
    }

    public void createDirectories(String path) {
        try {
            Files.createDirectories(Paths.get(path));
        } catch(IOException ex) {
            String msg = "Error while creation directory: " + ex.getLocalizedMessage();
            log.error(msg, ex);
            throw new OcpNioException(msg, ex);
        }
    }

    public void copyWithBufferedStreams() {
        Path source = Paths.get("test-data/horse/a");
        Path dest = Paths.get("test-data/horse/b");

        try (BufferedReader reader = Files.newBufferedReader(source, Charset.forName("US-ASCII"));
                BufferedWriter writer = Files.newBufferedWriter(dest, Charset.forName("UTF-16"))) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null)
                writer.write(currentLine + "\n");

        } catch (IOException ex) {
            String msg = "Error while copy directories: " + ex.getLocalizedMessage();
            log.error(ex);
            throw new OcpNioException(msg, ex);
        }
    }

    public List<String> readFile(String fileName) {
        Path path = Paths.get(fileName);

        try {
            // Be aware that entire file is read when readAllLines() is called(),
            // with the resulting String array storing all of the contents of the
            // file in memory at once. Therefore, if the file is significantly large,
            // you may encounter an OutOfMemoryError trying to load all of it into
            // memory.
            return Files.readAllLines(path, Charset.forName("UTF-16"));
        } catch (IOException ex) {
            String msg = "Error while reading file: " + ex.getLocalizedMessage();
            log.error(ex);
            throw new OcpNioException(msg, ex);
        }
    }

    public void setLastModified(String fileName, long epochMillis) {
        try {
            Path path = Paths.get(fileName);
            Files.setLastModifiedTime(path, FileTime.fromMillis(epochMillis));
        } catch (IOException ex) {
            String msg = "Error while setting last modified time: " + ex.getLocalizedMessage();
            log.error(ex);
            throw new OcpNioException(msg, ex);
        }
    }

    public Long getLastModified(String fileName) {
        try {
            Path path = Paths.get(fileName);
            return Files.getLastModifiedTime(path).toMillis();
        } catch (IOException ex) {
            String msg = "Error while getting last modified time: " + ex.getLocalizedMessage();
            log.error(ex);
            throw new OcpNioException(msg, ex);
        }
    }
}

