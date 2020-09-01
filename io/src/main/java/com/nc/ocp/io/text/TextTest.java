package com.nc.ocp.io.text;

import com.nc.ocp.io.text.rw.BufferedTextCopier;
import lombok.extern.log4j.Log4j;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Log4j
public class TextTest {
    private static final String SOURCE_FILE = "./test-dir/test.txt";
    private static final String DESTINATION_FILE = "./test-dir/test_copy.txt";

    public void run() {
        readAndCopyText();
    }

    private void readAndCopyText() {
        try {
            List<String> data = BufferedTextCopier.readFile(new File(SOURCE_FILE));
            data.forEach(log::info);
            BufferedTextCopier.writeFile(data, new File(DESTINATION_FILE));
        } catch (IOException ex) {
            log.error("Error occurred while reading or writing text data: " + ex.getLocalizedMessage());
        }
    }
}
