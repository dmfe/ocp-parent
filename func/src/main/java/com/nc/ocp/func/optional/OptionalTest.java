package com.nc.ocp.func.optional;

import org.apache.log4j.Logger;

import java.util.Optional;

public class OptionalTest {
    private static final Logger log = Logger.getLogger(OptionalTest.class);

    public void run() {
        average().ifPresent(log::info);
        average(90).ifPresent(log::info);
        average(90, 100).ifPresent(log::info);
    }

    private Optional<Double> average(int... scores) {
        if(scores.length == 0) return Optional.empty();
        int sum = 0;
        for(int score : scores) sum += score;
        return Optional.of((double) sum / scores.length);
    }
}