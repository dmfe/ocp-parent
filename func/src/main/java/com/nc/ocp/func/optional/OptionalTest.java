package com.nc.ocp.func.optional;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OptionalTest {

    public void run() {
        average().ifPresent(avg -> log.info("{}", avg));
        average(90).ifPresent(avg -> log.info("{}", avg));
        average(90, 100).ifPresent(avg -> log.info("{}", avg));
    }

    private Optional<Double> average(int... scores) {
        if (scores.length == 0) return Optional.empty();
        int sum = 0;
        for (int score : scores) sum += score;
        return Optional.of((double) sum / scores.length);
    }
}