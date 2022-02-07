package com.nc.ocp.despat.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Fish extends Food {

    public Fish(int quantity) {
        super(quantity);
    }

    @Override
    public void consumed() {
        log.info("Fish eaten: " + getQuantity());
    }
}
