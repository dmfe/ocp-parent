package com.nc.ocp.exception.client;

import com.nc.ocp.exception.data.Dolphin;
import com.nc.ocp.exception.exceptions.CannotSwimException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Client {
    private static final Logger log = Logger.getLogger(Client.class);

    public void runClientCode() {
        Dolphin dolphin = new Dolphin();
        log.info("Dolphin created.");
        try {
            dolphin.swim();
        } catch(CannotSwimException ex) {
            log.error("Error during client work.", ex);
        }
    }

    // Code duplication example.
    public void exceptionIllustrationCodeDuplication() {
        try {
            Path path = Paths.get("dolphinsBorn.txt");
            String text = new String(Files.readAllBytes(path));
            LocalDate date = LocalDate.parse(text);
            log.info(date);
        } catch (DateTimeParseException ex) {
            log.error(ex);
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            log.error(ex);
            throw new RuntimeException(ex);
        }
    }

    // Before java 7 there were 2 ways to avoid code duplication during exception handling using multi-catch blocks.
    // No code duplication, but bad approach
    public void exceptionIllustrationBadApproad() {
        try {
            Path path = Paths.get("dolphinsBorn.txt");
            String text = new String(Files.readAllBytes(path));
            LocalDate date = LocalDate.parse(text);
            log.info(date);
        } catch (Exception ex) { // BAD Approach!
            log.error(ex);
            throw new RuntimeException(ex);
        }
    }

    // Using helper method to handle exception.
    public void exceptionIllustrationUsingHelper() {
        try {
            Path path = Paths.get("dolphinsBorn.txt");
            String text = new String(Files.readAllBytes(path));
            LocalDate date = LocalDate.parse(text);
            log.info(date);
        } catch (DateTimeParseException ex) {
            handleException(ex);
        } catch (IOException ex) {
            handleException(ex);
        }
    }

    private static void handleException(Exception e) {
        log.error(e);
        throw new RuntimeException(e);
    }

    // Java 7 elegant solution.
    public void exceptionIllustrationJava7Approach() {
        try {
            Path path = Paths.get("dolphinsBorn.txt");
            String text = new String(Files.readAllBytes(path));
            LocalDate date = LocalDate.parse(text);
            log.info(date);
        } catch (DateTimeParseException | IOException ex) {
            log.error(ex);
            throw new RuntimeException(ex);
        }
    }
}