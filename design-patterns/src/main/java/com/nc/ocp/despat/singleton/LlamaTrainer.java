package com.nc.ocp.despat.singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LlamaTrainer {

    public boolean feedLlamas(int numberOfLlamas) {
        int amountNeeded = 5 * numberOfLlamas;
        HayStorage hayStorage = HayStorage.getInstance();
        if (hayStorage.getHayQuantity() < amountNeeded) {
            hayStorage.addHay(amountNeeded + 10);
        }
        boolean fed = hayStorage.removeHay(amountNeeded);
        if (fed) log.info("Llamas have been fed");
        return fed;
    }
}