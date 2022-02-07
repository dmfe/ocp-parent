package com.nc.ocp.io.stream.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class PrintWriterLogger {

    private static final String LOG_FILE_NAME = "./test-dir/zoo.log";

    private PrintWriterLogger() {
    }

    public static void writeLogs() {
        File source = new File(LOG_FILE_NAME);

        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(source))
        )) {
            out.print("Today's weather is: ");
            out.println("Sunny");
            out.print("Today's temperature at the zoo is: ");
            out.print(1 / 3.0);
            out.println('C');
            out.format("It has rained 10.12 inches this year");
            out.println();
            out.printf("It may rain 21.2 more inches this year");
        } catch (IOException ex) {
            log.error("Error occurred while writing logs: {}" + ex.getLocalizedMessage(), ex);
        }
    }
}
