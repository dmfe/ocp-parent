package com.nc.ocp.despat.factory;

import org.apache.log4j.Logger;

public class Hay extends Food {
    private static final Logger log = Logger.getLogger(Hay.class);

    public Hay(int quantity) {
        super(quantity);
    }

    @Override
    public void consumed() {
        log.info("Hay eaten: " + getQuantity());
    }
}