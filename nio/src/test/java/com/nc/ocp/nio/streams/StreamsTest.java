package com.nc.ocp.nio.streams;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sun.jdi.connect.Connector;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
class StreamsTest {

    private StreamsSample ss = new StreamsSample();

    @DisplayName("Path walk.")
    @Test
    void pathWalkTest() {
        List<String> expectedPaths = List.of(
                "./test-data/bigcats/Lion.java",
                "./test-data/bigcats/version1/Lion.java",
                "./test-data/bigcats/version1/Tiger.java",
                "./test-data/bigcats/version1/backup/Lion.java"
        );

        List<String> actualPaths = ss.pathWalk("bigcats");

        assertEquals(expectedPaths, actualPaths);
    }

    @DisplayName("Path search.")
    @Test
    void pathSearchTest() {
        long lastModifiedTimeFilter = 1_644_524_682_016L;

        List<String> expectedPaths = List.of(
                "./test-data/bigcats/Lion.java",
                "./test-data/bigcats/version1/Tiger.java"
        );

        List<String> actualPaths = ss.searchInDirectory("bigcats", lastModifiedTimeFilter);

        assertEquals(expectedPaths, actualPaths);
    }

    @DisplayName("Directory files listing.")
    @Test
    void directoryListingTest() {
        List<String> expectedFiles = List.of(
                "./test-data/duck/weight.txt",
                "./test-data/duck/food-backup.txt",
                "./test-data/duck/food.txt"
        );

        List<String> actualFiles = ss.listDirectoryFiles("duck");

        assertEquals(expectedFiles, actualFiles);
    }

    @DisplayName("File content filtering.")
    @Test
    void filterFileContentTest() {
        List<String> expectedLines = List.of(
                "WARN No database could be detected",
                "WARN Performing manual recovery"
        );

        List<String> actualLines = ss.filterFileContent("fish/sharks.log", "WARN");

        assertEquals(expectedLines, actualLines);
    }
}
