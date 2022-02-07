package com.nc.ocp.despat.singleton;

class HayStorage {

    private static final HayStorage instance = new HayStorage();

    private int quantity;

    private HayStorage() {}

    static HayStorage getInstance() {
        return instance;
    }

    synchronized void addHay(int amount) {
        quantity += amount;
    }

    synchronized boolean removeHay(int amount) {
        if(quantity < amount)return false;
        quantity -= amount;
        return true;
    }

    synchronized int getHayQuantity() {
        return quantity;
    }
}
