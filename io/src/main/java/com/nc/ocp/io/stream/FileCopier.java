package com.nc.ocp.io.stream;

import com.nc.ocp.io.excepions.CopyFileExceptions;
import lombok.extern.log4j.Log4j;
import util.UUIDGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Log4j
class FileCopier {

    private static final String DEFAULT_COPY_PREFIX = "COPY";
    private static final String COPY_DELIMITER = ".";

    private final File source;
    private File destination;

    private static String generateDefaultDestinationName(String sourceName) {
        return sourceName + COPY_DELIMITER + DEFAULT_COPY_PREFIX + COPY_DELIMITER + UUIDGenerator.generateUUID();
    }

    FileCopier(String source) {
        this(source, generateDefaultDestinationName(source));
    }

    FileCopier(String sourceName, String destinationName) {
        this.source = new File(sourceName);
        this.destination = new File(destinationName);
    }

    void copy() {
        try(InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(destination)) {
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
        } catch (IOException ex) {
            log.error("Unable to copy files: " + ex.getLocalizedMessage(), ex);
            throw new CopyFileExceptions("Unable to copy files: " + ex.getLocalizedMessage(), ex);
        }
    }
}
