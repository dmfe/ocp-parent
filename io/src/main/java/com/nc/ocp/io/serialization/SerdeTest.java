package com.nc.ocp.io.serialization;

import com.google.common.collect.ImmutableList;
import com.nc.ocp.io.serialization.data.Animal;
import lombok.extern.log4j.Log4j;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Log4j
public class SerdeTest {

    private static final String DATA_FILE_NAME = "./test-dir/serialized-objects";

    public void run() {
       serializeAndDeserializeAnimals();
    }

    private void serializeAndDeserializeAnimals() {
        List<Animal> animals = ImmutableList.of(
                new Animal("Tommy Tiger", 5, 'T'),
                new Animal("Peter Penguin", 8, 'P')
        );

        File dataFile = new File(DATA_FILE_NAME);

        try {
            Serde<Animal> serde = new Serde<>();

            log.info("serializing animals objects...");
            serde.serialize(animals, dataFile);

            log.info("deserializing animals objects...");
            List<Animal> deserializedAnimals = serde.deserialize(dataFile, Animal.class);

            log.info("Deserialized animals:");
            deserializedAnimals.forEach(log::info);
        } catch (IOException | ClassNotFoundException ex) {
            log.error("Error during serialization/deserialization of animals objects: " + ex.getLocalizedMessage());
        }
    }
}
