package com.nc.ocp.concurrency.forkjoin;

import java.util.Random;
import java.util.concurrent.RecursiveTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WeightAnimalTask extends RecursiveTask<Double> {
    private static final int MINIMAL_ANIMAL_NUMBER = 3;

    private int start;
    private int end;
    private Double[] weights;

    public WeightAnimalTask(int start, int end, Double[] weights) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }

    @Override
    protected Double compute() {
        if (end - start <= MINIMAL_ANIMAL_NUMBER) {
            double sum = 0;
            for (int i = start; i < end; i++) {
                weights[i] = (double) new Random().nextInt(100);
                log.info("Animal " + i + " weighted: " + weights[i]);
                sum += weights[i];
            }

            return sum;
        } else {
            int middle = start + ((end - start) / 2);
            log.info("[start = " + start + ", middle = " + middle + ", end = " + end + "]");
            RecursiveTask<Double> otherTask = new WeightAnimalTask(start, middle, weights);
            otherTask.fork();

            return new WeightAnimalTask(middle, end, weights).compute() + otherTask.join();
        }
    }
}
