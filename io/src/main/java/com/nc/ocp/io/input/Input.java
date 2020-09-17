package com.nc.ocp.io.input;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

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
}
