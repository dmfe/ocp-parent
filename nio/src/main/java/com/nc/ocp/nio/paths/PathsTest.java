package com.nc.ocp.nio.paths;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.extern.slf4j.Slf4j;

/**
 * PathsTest
 */
@Slf4j
public class PathsTest {

    public void run() {
        countAndNameTest();
        pathInfoTest();
        isAbsolutePathTest();
        subpathTest();
        relativizeTest();
        resolveTest();
        normalizeTest();
        toRealTest();
    }

    private void countAndNameTest() {
        log.info("Test Get Count And Get Name...");

        Path path = Paths.get("./test-data/land/hippo/harry.happy");
        log.info("The Path Name is: " + path);

        for (int i = 0; i < path.getNameCount(); i++) {
            log.info("  Element " + i + " is: " + path.getName(i));
        }
    }

    private void printPathInfo(Path path) {
        log.info("Filename is: " + path.getFileName());
        log.info("Root is: " + path.getRoot());

        Path curParent = path;
        while ((curParent = curParent.getParent()) != null) {
            log.info("  Current parent is: " + curParent);
        }
    }

    private void pathInfoTest() {
        printPathInfo(Paths.get("/zoo/armadillo/shells.txt"));
        printPathInfo(Paths.get("armadillo/shells.txt"));
    }

    private void isAbsolutePathTest() {
        Path path1 = Paths.get("C:\\birds\\egret.txt");
        log.info("Path1 is Absolute? " + path1.isAbsolute());
        log.info("Absolute path1: " + path1.toAbsolutePath());

        Path path2 = Paths.get("birds/condor.txt");
        log.info("Path2 is Absolute? " + path2.isAbsolute());
        log.info("Absolute path2: " + path2.toAbsolutePath());

        Path path3 = Paths.get("/birds/condor.txt");
        log.info("Path3 is Absolute? " + path3.isAbsolute());
        log.info("Absolute path3: " + path3.toAbsolutePath());
    }

    private void subpathTest() {
        Path path = Paths.get("/mammal/carnivore/raccon.image");

        log.info("Path is: " + path);
        log.info("Subpath from 0 to 3 is: " + path.subpath(0, 3));
        log.info("Subpath from 1 to 3 is: " + path.subpath(1, 3));
        log.info("Subpath from 1 to 2 is: " + path.subpath(1, 2));
        //path.subpath(1, 1); // IllegalArgumentException
    }

    private void relativizeTest() {
        Path path1 = Paths.get("fish.txt");
        Path path2 = Paths.get("birds.txt");
        log.info("rel path1={} path2={} result={}", path1, path2, path1.relativize(path2));
        log.info("rel path2={} path1={} result={}", path2, path1, path2.relativize(path1));

        Path path3 = Paths.get("/habitat");
        Path path4 = Paths.get("/sanctuary/raven");
        log.info("rel path3={} path4={} result={}", path3, path4, path3.relativize(path4));
        log.info("rel path4={} path3={} result={}", path4, path3, path4.relativize(path3));
    }

    private void resolveTest() {
        Path path1 = Paths.get("/cats/../panther");
        Path path2 = Paths.get("food");
        log.info("resolve path1={} path2={} result={}", path1, path2, path1.resolve(path2));

        Path path3 = Paths.get("/cats/../panther");
        Path path4 = Paths.get("/food");
        log.info("resolve path3={} path4={}, result={}", path3, path4, path3.resolve(path4));
    }

    private void normalizeTest() {
        Path path1 = Paths.get("/data");
        Path path2 = Paths.get("/home/user");
        Path relativePath = path1.relativize(path2);
        log.info("Not normalized path: " + path1.resolve(relativePath));
        log.info("Normalized path: " + path1.resolve(relativePath).normalize());
    }

    private void toRealTest() {
        try {
            log.info("To real path: {}", Paths.get("test-data/zebra/food.source").toRealPath());
            log.info("To real path: {}", Paths.get("test-data/horse/food.txt").toRealPath());
        } catch (IOException ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
    }
}

