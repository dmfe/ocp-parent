package com.nc.ocp.exception.client;

import com.nc.ocp.exception.data.Dolphin;
import com.nc.ocp.exception.data.Turkey;
import com.nc.ocp.exception.data.TurkeyCage;
import com.nc.ocp.exception.exceptions.CannotSwimException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {

    public void runClientCode() {
        Dolphin dolphin = new Dolphin();
        log.info("Dolphin created.");
        try {
            dolphin.swim();
        } catch (CannotSwimException ex) {
            log.error("Error during client work.", ex);
        }
    }

    public void parseDateFromFile() {
        exceptionIllustrationJava7Approach();
    }

    public void copyFileContent(String sourceFileName, String destFileName) {
        try {
            newApproachResourcesRelease(Paths.get(sourceFileName), Paths.get(destFileName));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void turkeyAutoCloseableTest() {
        try (Turkey turkey = new Turkey("Judith")) {
            log.info("Turkey " + turkey.getName() + " is ready form processing...");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void supressionTest() {
        try (TurkeyCage cage = new TurkeyCage()) {
            throw new IllegalStateException("Exception during supression test");
        } catch (IllegalStateException ex) {
            log.error("Exception caught: " + ex.getMessage());
            for (Throwable t : ex.getSuppressed()) {
                log.error(t.getMessage());
            }
        }
    }

    // Code duplication example.
    private void exceptionIllustrationCodeDuplication() {
        try {
            Path path = Paths.get("dolphinsBorn.txt");
            String text = new String(Files.readAllBytes(path));
            LocalDate date = LocalDate.parse(text);
            log.info("{}", date);
        } catch (DateTimeParseException ex) {
            log.error("error during parsing datetime: {}", ex.getLocalizedMessage(), ex);
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            log.error("Input/Output exception: {}", ex.getLocalizedMessage(), ex);
            throw new RuntimeException("Input/Output exception: " + ex.getLocalizedMessage(), ex);
        }
    }

    // Before java 7 there were 2 ways to avoid code duplication during exception handling using multi-catch blocks.
    // No code duplication, but bad approach
    private void exceptionIllustrationBadApproad() {
        try {
            Path path = Paths.get("dolphinsBorn.txt");
            String text = new String(Files.readAllBytes(path));
            LocalDate date = LocalDate.parse(text);
            log.info("{}", date);
        } catch (Exception ex) { // BAD Approach!
            log.error("{}", ex.getLocalizedMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    // Using helper method to handle exception.
    private void exceptionIllustrationUsingHelper() {
        try {
            Path path = Paths.get("dolphinsBorn.txt");
            String text = new String(Files.readAllBytes(path));
            LocalDate date = LocalDate.parse(text);
            log.info("{}", date);
        } catch (DateTimeParseException ex) {
            handleException(ex);
        } catch (IOException ex) {
            handleException(ex);
        }
    }

    private static void handleException(Exception e) {
        log.error("{}", e.getLocalizedMessage(), e);
        throw new RuntimeException(e);
    }

    // Java 7 elegant solution.
    private void exceptionIllustrationJava7Approach() {
        try {
            Path path = Paths.get("dolphinsBorn.txt");
            String text = new String(Files.readAllBytes(path));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(text, dtf);
            log.info("{}", date);
        } catch (DateTimeParseException | IOException ex) {
            log.error("", ex.getLocalizedMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    private void oldApproachResourcesRelease(Path path1, Path path2) throws IOException {
        BufferedReader in = null;
        BufferedWriter out = null;
        try {
            in = Files.newBufferedReader(path1);
            out = Files.newBufferedWriter(path2);
            out.write(in.readLine());
        } finally {
            if (in != null) in.close();   // If IOException will be thrown here,
            if (out != null) out.close(); // then out.close() will never be executed.
        }
    }

    private void newApproachResourcesRelease(Path path1, Path path2) throws IOException {
        try (BufferedReader in = Files.newBufferedReader(path1);
             BufferedWriter out = Files.newBufferedWriter(path2)) {
            out.write(in.readLine());
        }
    }
}
