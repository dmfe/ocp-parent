
package com.nc.ocp.nio.paths;

import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.extern.log4j.Log4j;

/**
 * PathsTest
 */
@Log4j
public class PathsTest {

    public void run() {
        countAndNameTest();
    }

    private void countAndNameTest() {
        log.info("Test Get Count And Get Name...");

        Path path = Paths.get("./test-data/land/hippo/harry.happy");
        log.info("The Path Name is: " + path);

        for (int i = 0; i < path.getNameCount(); i++) {
            log.info("  Element " + i + " is: " + path.getName(i));
        }
    }
}

