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
            throw produceOcpNioException("Error while walking path: ", ex);
        }
    }

    List<String> searchInDirectory(String directory, long lastModifiedTime) {
        Path path = Paths.get(DATA_FOLDER + "/" + directory);

        try {
            return Files.find(path, 10, (p, attrs) ->
                    p.toString().endsWith(".java") && attrs.lastModifiedTime().toMillis() > lastModifiedTime)
                    .peek(p -> log.info("{}", p))
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            throw produceOcpNioException("Error while searching in directory: ", ex);
        }
    }

    List<String> listDirectoryFiles(String directory) {
        Path path = Paths.get(DATA_FOLDER + "/" + directory);

        try {
            return Files.list(path)
                    .filter(p -> !Files.isDirectory(p))
                    .map(Path::toString)
                    .peek(log::info)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            throw produceOcpNioException("Error while listing directory: ", ex);
        }
    }

    List<String> filterFileContent(String directory, String filter) {
        Path path = Paths.get(DATA_FOLDER + "/" + directory);

        try {
            return Files.lines(path)
                    .filter(s -> s.contains(filter))
                    .peek(log::info)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            throw produceOcpNioException("Error while reading content of the file " + path, ex);
        }
    }
}
