package com.nc.ocp.io.stream;

import lombok.extern.log4j.Log4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Log4j
public class StreamTest {

    private static final String TEST_FILE_NAME = "/home/dima/dev/java/ocp-parent/io/test-dir/test.txt";

    public void run() {
        bufferedReaderTest();
    }

    private void bufferedReaderTest() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(TEST_FILE_NAME))) {
            log.info("First line red from file: " + bufferedReader.readLine());
        } catch (IOException ioe) {
            log.error("IO Error occurred during reading file " + TEST_FILE_NAME + ":" + ioe.getLocalizedMessage(), ioe);
        }
    }
}
