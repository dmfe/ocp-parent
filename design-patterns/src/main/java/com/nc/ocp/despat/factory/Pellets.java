package com.nc.ocp.despat.factory;

import org.apache.log4j.Logger;

public class Pellets extends Food {
    private static final Logger log = Logger.getLogger(Pellets.class);

    public Pellets(int quantity) {
        super(quantity);
    }

    @Override
    public void consumed() {
        log.info("Pellets eaten: " + getQuantity());
    }
}