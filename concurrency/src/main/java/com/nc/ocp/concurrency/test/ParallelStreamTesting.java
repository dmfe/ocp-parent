package com.nc.ocp.concurrency.test;

import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j
public class ParallelStreamTesting {

    public void run() {
        creatingTest();
        parallelStreamTest();
        whaleDataCalculationTest();
        statefulTest();
        parallelReductionsTest();
        parallelReduceTest();
        parallelCollectTest();
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

    private void statefulTest() {

        List<Integer> data = Collections.synchronizedList(new ArrayList<>());
        Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream()
                .map(i -> {data.add(i); return i;})
                .forEachOrdered(log::info);

        log.info("------");

        data.forEach(log::info);
    }

    private void parallelReductionsTest() {
        log.info("Find any for serial stream: " + Stream.of(1, 2, 3, 4, 5, 6).findAny().get());
        log.info("Find any for parallel stream: " + Stream.of(1, 2, 3, 4, 5, 6).parallel().findAny().get());
    }

    private void parallelReduceTest() {
        log.info("Serial: " + Arrays.asList("w", "o", "l", "f").stream().reduce("", (c, s1) -> c + s1, (s2, s3) -> s2 + s3));
        log.info("Parallel: " + Arrays.asList("w", "o", "l", "f").stream().reduce("", (c, s1) -> c + s1, (s2, s3) -> s2 + s3));

        log.info("-----");

        log.info("- op Serial: " + Arrays.asList(1, 2, 3, 4, 5, 6).stream().reduce(0, (a,b) -> a-b));
        log.info("- op Parallel: " + Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().reduce(0, (a,b) -> a-b));

        log.info("+ op Serial: " + Arrays.asList(1, 2, 3, 4, 5, 6).stream().reduce(0, (a,b) -> a+b));
        log.info("+ op Parallel: " + Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().reduce(0, (a,b) -> a+b));
    }

    private void parallelCollectTest() {
        Stream<String> ohMy = Stream.of("lions", "tigers", "bears").parallel();

        ConcurrentMap<Integer, String> map = ohMy
                .collect(Collectors.toConcurrentMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2));

        log.info(map);
        log.info(map.getClass());

        ohMy = Stream.of("lions", "tigers", "bears").parallel();

        ConcurrentMap<Integer, List<String>> groupingMap =
                ohMy.collect(Collectors.groupingByConcurrent(String::length));

        log.info(groupingMap);
        log.info(groupingMap.getClass());
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