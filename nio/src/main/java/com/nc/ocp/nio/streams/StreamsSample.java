package com.nc.ocp.nio.streams;

import com.nc.ocp.nio.exceptions.OcpNioException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class StreamsSample {

    private static final String DATA_FOLDER = "./test-data";

    List<String> pathWalk(String directory) {
        Path path = Paths.get(DATA_FOLDER + "/" + directory);

        try {
            return Files.walk(path)
                    .filter(p -> p.toString().endsWith(".java"))
                    .peek(p -> log.info("{}", p))
                    .map(Path::toString)
                    .collect(Collectors.toList());

        } catch (IOException ex) {
            throw produceOcpNioException("Exception while walking path: ", ex);
        }
    }

    private OcpNioException produceOcpNioException(String msg, Exception e) {
        String message = msg + e.getLocalizedMessage();
        log.error(msg, e);
        return new OcpNioException(message, e);
    }
}
