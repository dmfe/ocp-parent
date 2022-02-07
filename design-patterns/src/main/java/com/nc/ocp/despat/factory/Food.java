package com.nc.ocp.despat.factory;

public abstract class Food {
    private int quantity;

    Food(int quantity) {
        this.quantity = quantity;
    }

    int getQuantity() {
        return quantity;
    }

    public abstract void consumed();
}
