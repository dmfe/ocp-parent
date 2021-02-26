package com.nc.ocp.nio.files;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

/**
 * FilesTest
 */
@RunWith(JUnitPlatform.class)
public class FilesTest {

    private FilesSample fs = new FilesSample();

    @DisplayName("Directory exists")
    @Test
    void directoryExistisTest() {
        assertTrue(fs.checkFile("horse"));
    }

    @DisplayName("File exists")
    @Test
    void fileExistisTest() {
        assertTrue(fs.checkFile("horse/food.txt"));
    }

    @DisplayName("File doesn't exist")
    @Test
    void fileDoesntExistisTest() {
        assertFalse(fs.checkFile("horse/somefood.txt"));
    }

    @DisplayName("Directories are the same")
    @Test
    void DirectoriesAreTheSameTest() {
        assertTrue(fs.isSameFiles("test-data/horse", "test-data/steed"));
    }

    @DisplayName("Files are the same")
    @Test
    void FilesAreTheSameTest() {
        assertTrue(fs.isSameFiles("test-data/horse/food.txt", "test-data/zebra/food.source"));
    }
}

