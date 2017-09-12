package com.nc.ocp.func.interfaces;

import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class InterfacesTest {
    private static final Logger log = Logger.getLogger(InterfacesTest.class);

    public void run() {
        Supplier<LocalDate> s1 = LocalDate::now;
        Supplier<LocalDate> s2 = () -> LocalDate.now();
        LocalDate d1 = s1.get();
        LocalDate d2 = s2.get();
        log.info(d1);
        log.info(d2);

        Supplier<List<String>> s3 = ArrayList<String>::new;
        List<String> lst = s3.get();
        Collections.addAll(lst, "a", "b", "c");
        log.info("lambda: " + s3 + ", list: " + lst);

        Consumer<String> c1 = log::info;
        Consumer<String> c2 = x -> log.info(x);
        c1.accept("Annie method ref...");
        c2.accept("Annie lambda...");

        Map<String, Integer> map = new HashMap<>();
        BiConsumer<String, Integer> b1 = map::put;
        BiConsumer<String, Integer> b2 = (k, v) -> map.put(k, v);
        b1.accept("chicken", 7);
        b2.accept("chick", 1);
        log.info(map);

        BiPredicate<String, String> bp1 = String::startsWith;
        BiPredicate<String, String> bp2 = (str, pref) -> str.startsWith(pref);

        log.info(bp1.test("chicken", "chick"));
        log.info(bp2.test("cockroach", "cock"));

        Predicate<String> egg = s -> s.contains("egg");
        Predicate<String> brown = s -> s.contains("brown");
        Predicate<String> brownEgg = egg.and(brown);
        Predicate<String> otherEgg = egg.and(brown.negate());

        log.info(brownEgg.test("egg hgjdsgbrown"));
        log.info(otherEgg.test("egg hgjdsgown"));
    }
}
