package com.nc.ocp.io.file;

import lombok.extern.log4j.Log4j;

import java.io.File;
import java.util.Optional;
import java.util.stream.Stream;

@Log4j
public class FileTest {

    public void run() {
        fileCreationTest();
        fileInfoTest();
    }

    private void fileCreationTest() {
        File file = new File("/home/dima/dev/java/ocp-parent/io/test-dir/test.txt");
        log.info("File exists: " + file.exists());
    }

    private void fileInfoTest() {
        File file = new File("/home/dima/dev/java/ocp-parent/io/test-dir/subdir");
        log.info("File exists: " + file.exists());

        if (file.exists()) {
            log.info("Absolute path: " + file.getAbsoluteFile());
            log.info("Is directory: : " + file.isDirectory());
            log.info("Parent path: : " + file.getParent());

            if (file.isFile()) {
                log.info("File size: " + file.length());
                log.info("File last modified: " + file.lastModified());
            } else {
                Optional.ofNullable(file.listFiles())
                        .map(files -> {
                            Stream.of(files).forEach(f -> log.info("\t" + f.getName()));
                            return null;
                        }
                        ).orElse(null);
            }
        }
    }
}
