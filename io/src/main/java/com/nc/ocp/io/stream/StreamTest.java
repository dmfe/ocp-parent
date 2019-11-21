package com.nc.ocp.io.stream;

import com.nc.ocp.io.stream.copy.CopierType;
import com.nc.ocp.io.stream.copy.FileCopier;
import lombok.extern.log4j.Log4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Log4j
public class StreamTest {

    private static final String TEST_FILE_NAME = "./test-dir/test.txt";
    private static final String FILE_TO_COPY_NAME = "./test-dir/file-to-copy.txt";

    public void run() {
        bufferedReaderTest();
        byteCopyFileTest();
        bufferedCopyFileTest();
    }

    private void bufferedReaderTest() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(TEST_FILE_NAME))) {
            log.info("First line red from file: " + bufferedReader.readLine());
        } catch (IOException ioe) {
            log.error("IO Error occurred during reading file " + TEST_FILE_NAME + ":" + ioe.getLocalizedMessage(), ioe);
        }
    }

    private void byteCopyFileTest() {
        FileCopier.builder(CopierType.BYTE)
                .source(FILE_TO_COPY_NAME)
                .build().copy();
        log.info("File " + FILE_TO_COPY_NAME + " has been copied by using byte stream.");
    }

    private void bufferedCopyFileTest() {
        FileCopier.builder(CopierType.BUFFERED)
                .source(FILE_TO_COPY_NAME)
                .build().copy();
        log.info("File " + FILE_TO_COPY_NAME + " has been copied by using buffered stream.");
    }
}
