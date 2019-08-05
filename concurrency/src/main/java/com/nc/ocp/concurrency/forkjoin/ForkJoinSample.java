package com.nc.ocp.concurrency.forkjoin;

import lombok.extern.log4j.Log4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j
public class ForkJoinSample {
    private static final int ANIMALS_NUMBER = 10;

    public void run() {
        Double[] weights = new Double[ANIMALS_NUMBER];

        ForkJoinTask<?> task = new WeightAnimalAction(0, weights.length, weights);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);

        log.info("Weights:");
        log.info(Stream.of(weights)
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]")));
    }
}
