package com.nc.ocp.gencol.add;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class Addition8Test {
    private static final Logger log = Logger.getLogger(Addition8Test.class);

    public void run() {
        List<String> lst = new ArrayList<>();
        Collections.addAll(lst, "Assistant", "Merchant", "Ape");
        log.info("Before remove: " + lst);
        lst.removeIf(s -> s.startsWith("A"));
        log.info("After remove: " + lst);

        List<Integer> intLst = new ArrayList<>();
        Collections.addAll(intLst, 1, 4, 3, 8);
        log.info("Before update: " + intLst);
        intLst.replaceAll(x -> x*2);
        log.info("After update: " + intLst);

        List<String> cats = Arrays.asList("Vaska", "Muska");
        cats.forEach(log::info);

        Map<String, String> favourites = Maps.newHashMap(ImmutableMap.of(
                "Jenny", "Bus Tour",
                "Tom", "Tram"));
        log.info("Before merge:");
        favourites.forEach((k, v) -> log.info("(" + k + " -> " + v + ")"));

        BiFunction<String, String, String> mapper = (v1, v2)
                -> v1.length() > v2.length() ? v1 : v2;
        favourites.merge("Jenny", "Skyride", mapper);
        favourites.merge("Tom", "Skyride", mapper);
        favourites.merge("Jeff", "Skyride", mapper);
        log.info("After merge:");
        favourites.forEach((k, v) -> log.info("(" + k + " -> " + v + ")"));
    }
}