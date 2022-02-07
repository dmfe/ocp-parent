package com.nc.ocp.io.serialization;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Serde<T> {

    void serialize(List<T> objects, File dataFile) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(dataFile))
        )) {
            for (T object : objects) {
                out.writeObject(object);
            }
        }
    }

    List<T> deserialize(File dataFile, Class<T> clazz) throws IOException, ClassNotFoundException {
        List<T> objects = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(dataFile))
        )) {
            while (true) {
                Object object = in.readObject();
                if (clazz.isInstance(object)) {
                    objects.add(clazz.cast(object));
                }
            }
        } catch (EOFException e) {
            log.info("File end reached");
        }

        return objects;
    }
}
