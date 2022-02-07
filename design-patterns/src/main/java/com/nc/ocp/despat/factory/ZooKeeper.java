package com.nc.ocp.despat.factory;

public class ZooKeeper {
    public static void feedAnimal(String animal) {
        Food food = FoodFactory.getFood(animal);
        food.consumed();
    }
}
