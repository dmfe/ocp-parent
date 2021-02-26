package com.nc.ocp.nio.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
}
