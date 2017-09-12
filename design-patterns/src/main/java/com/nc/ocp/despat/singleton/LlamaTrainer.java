package com.nc.ocp.despat.singleton;

import org.apache.log4j.Logger;

public class LlamaTrainer {
    private static final Logger log = Logger.getLogger(LlamaTrainer.class);

    public boolean feedLlamas(int numberOfLlamas) {
        int amountNeeded = 5 * numberOfLlamas;
        HayStorage hayStorage = HayStorage.getInstance();
        if(hayStorage.getHayQuantity() < amountNeeded) {
            hayStorage.addHay(amountNeeded + 10);
        }
        boolean fed = hayStorage.removeHay(amountNeeded);
        if(fed) log.info("Llamas have been fed");
        return fed;
    }
}