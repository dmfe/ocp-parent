package com.nc.ocp.nio.streams;

import static com.nc.ocp.nio.NioConstants.DATA_FOLDER;
import static com.nc.ocp.nio.exceptions.OcpNioUtils.produceOcpNioException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class StreamsSample {

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

}
