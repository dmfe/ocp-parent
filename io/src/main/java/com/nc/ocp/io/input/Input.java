package com.nc.ocp.io.input;

import lombok.extern.log4j.Log4j;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Log4j
final class Input {

    private Input() {
    }

    static String oldWayInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    static String modernInput() {
        Console console = System.console();
        String userInput = "";

        if (console != null) {
            userInput = console.readLine();
        }

        return userInput;
    }

    static void passwordInput() {
        Console console = System.console();

        char[] password;
        if (console == null) {
            throw new RuntimeException("Console not available");
        } else {
            password = console.readPassword("Enter your password: ");
        }

        console.format("Enter your password again: ");
        console.flush();
        char[] verify = console.readPassword();

        boolean match = Arrays.equals(password, verify);

        // Immediately clear password from memory
        for (int i = 0; i < password.length; i++) {
            password[i] = 'x';
        }

        for (int i = 0; i < verify.length; i++) {
            verify[i] = 'x';
        }

        log.info("Your password was " + (match ? "correct" : "incorrect"));
    }
}
