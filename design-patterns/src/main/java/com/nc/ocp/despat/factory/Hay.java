package com.nc.ocp.despat.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hay extends Food {

    Hay(int quantity) {
        super(quantity);
    }

    @Override
    public void consumed() {
        log.info("Hay eaten: " + getQuantity());
    }
}
