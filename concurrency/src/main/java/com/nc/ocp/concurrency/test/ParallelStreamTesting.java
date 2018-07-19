package com.nc.ocp.concurrency.test;

import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Log4j
public class ParallelStreamTesting {

    public void run() {
        creatingTest();
        parallelStreamTest();
        whaleDataCalculationTest();
    }

    private void creatingTest() {
        Stream<Integer> stream = Arrays.asList(1, 2, 3).stream();
        Stream<Integer> parallelStream = stream.parallel();

        Stream<Integer> parallelStream1 = Arrays.asList(4, 5, 6).parallelStream();
    }

    private void parallelStreamTest() {
        Arrays.asList(1, 2, 3, 4, 5, 6).stream().forEach(log::info);

        log.info("----------------------");

        Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().forEach(log::info);

        log.info("----------------------");

        Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().forEachOrdered(log::info);
    }

    private void whaleDataCalculationTest() {
        WhaleDataCalculator dataCalculator = new WhaleDataCalculator();
        List<Integer> data = new ArrayList<>();
        for(int i = 0; i < 4000; i++) data.add(i);

        long startTime = System.currentTimeMillis();
        long result = dataCalculator.processAllData(data);
        double time = (System.currentTimeMillis() - startTime) / 1000.0;
        log.info("Result: " + result);
        log.info("Whole data calculation task completed in: " + time + " seconds.");

        startTime = System.currentTimeMillis();
        result = dataCalculator.parallelProcessAllData(data);
        time = (System.currentTimeMillis() - startTime) / 1000.0;
        log.info("Result: " + result);
        log.info("Whole data calculation task completed in: " + time + " seconds.");

    }

    private static class WhaleDataCalculator {

        private int processRecord(int input) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                log.error("Whale data calculator was interrupted.");
                Thread.currentThread().interrupt();
            }

            return ++input;
        }

        private long processAllData(List<Integer> data) {
            return data.stream().map(this::processRecord).count();
        }

        private long parallelProcessAllData(List<Integer> data) {
            return data.parallelStream().map(this::processRecord).count();
        }
    }
}