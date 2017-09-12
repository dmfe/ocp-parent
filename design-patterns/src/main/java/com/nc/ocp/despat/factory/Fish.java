package com.nc.ocp.despat.factory;

import org.apache.log4j.Logger;

public class Fish extends Food {
    private static final Logger log = Logger.getLogger(Fish.class);

    public Fish(int quantity) {
        super(quantity);
    }

    @Override
    public void consumed() {
        log.info("Fish eaten: " + getQuantity());
    }
}