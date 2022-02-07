package com.nc.ocp.despat.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Pellets extends Food {

    public Pellets(int quantity) {
        super(quantity);
    }

    @Override
    public void consumed() {
        log.info("Pellets eaten: " + getQuantity());
    }
}
