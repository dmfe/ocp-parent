package com.nc.ocp.despat;

import com.nc.ocp.despat.builder.AnimalBuilder;
import com.nc.ocp.despat.factory.ZooKeeper;
import com.nc.ocp.despat.immutable.Animal;
import com.nc.ocp.despat.singleton.LlamaTrainer;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Arrays;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main( String[] args ) {
        // Configure logging properties.
        PropertyConfigurator.configure(Main.class.getClassLoader().getResourceAsStream("log4j.properties"));

        new Main().start();
    }

    private void start() {
        log.info("Starting Main");
        log.info("Singleton test:");
        new LlamaTrainer().feedLlamas(5);

        log.info("Immutable test:");
        Animal animal = new Animal("duck", 4, Arrays.asList("grass", "fish"));
        log.info("Animal: " + animal.getSpecies()
                + ", Age: " + animal.getAge()
                + ", First favorite food: " + animal.getFavoriteFood(0));

        log.info("Builder test:");
        Animal animal1 = new AnimalBuilder()
                .setAge(6).setSpecies("camel")
                .setFavoriteFoods(Arrays.asList("cactus")).build();
        log.info("Animal: " + animal1.getSpecies()
                + ", Age: " + animal1.getAge()
                + ", First favorite food: " + animal1.getFavoriteFood(0));

        log.info("Factory test:");
        ZooKeeper.feedAnimal("zebra");
        ZooKeeper.feedAnimal("polar bear");
        ZooKeeper.feedAnimal("rabbit");
    }
}