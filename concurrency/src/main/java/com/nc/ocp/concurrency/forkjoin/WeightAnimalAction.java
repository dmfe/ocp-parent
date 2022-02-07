package com.nc.ocp.concurrency.forkjoin;

import java.util.Random;
import java.util.concurrent.RecursiveAction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WeightAnimalAction extends RecursiveAction {
    private static final int MINIMAL_ANIMAL_NUMBER = 3;

    private int start;
    private int end;
    private Double[] weights;

    public WeightAnimalAction(int start, int end, Double[] weights) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }

    @Override
    protected void compute() {
        if (end - start <= MINIMAL_ANIMAL_NUMBER) {
            for (int i = start; i < end; i++) {
                weights[i] = (double) new Random().nextInt(100);
                log.info("Animal " + i + " weighted: " + weights[i]);
            }
        } else {
            int middle = start + ((end - start) / 2);
            log.info("[start = " + start + ", middle = " + middle + ", end = " + end + "]");
            invokeAll(new WeightAnimalAction(start, middle, weights), new WeightAnimalAction(middle, end, weights));
        }
    }
}
