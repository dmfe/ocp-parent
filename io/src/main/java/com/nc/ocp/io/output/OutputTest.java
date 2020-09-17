package com.nc.ocp.io.output;

import java.io.Console;

public class OutputTest {

    private static final double ZOO_SQUARE = 128.91;

    public void run() {
        testConsoleOutput();
    }

    private void testConsoleOutput() {
        Console console = System.console();

        if (console == null) {
            throw new RuntimeException("Console is not available.");
        }

        console.writer().println("Welcome to out zoo!");
        console.format("Our zoo has 391 animals and employs 25 people.");
        console.writer().println();
        console.printf("The zoo spans %.2f acres.", ZOO_SQUARE);
        console.writer().println();
    }
}
