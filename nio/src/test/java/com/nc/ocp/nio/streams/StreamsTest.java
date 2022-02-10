package com.nc.ocp.nio.streams;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
class StreamsTest {

    private StreamsSample ss = new StreamsSample();

    @DisplayName("Path walk test")
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
}
