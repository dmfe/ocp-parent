package com.nc.ocp.gencol.generics;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenericTest {

    public void run() {
        List<String> names = new ArrayList<>();
        names.add(new StringBuilder("Webby").toString());
        names.add("Fluffy");
        names.add(new Date().toString());
        printNames(names);
        printList(names);
        //oldDaysPrintNames(names);

//        List unicorns = new ArrayList();
//        unicorns.add(new Unicorn());
//        printDragons(unicorns);
        /////

        HashSet<? super ClassCastException> set = new HashSet<Exception>();
        Map<String, Integer> mp1 = new HashMap<>();
        mp1.put("1", 1);
        Map<String, Double> mp2 = new HashMap<>();
        mp2.put("1.0", 1.0);
        Map<String, BigInteger> mp3 = new HashMap<>();
        mp3.put("1.0", new BigInteger("24235365734573785687548"));

        Map<String, ? extends Number> hm = mp1;
        hm.forEach((v, k) -> log.info("(" + k + " -> " + v + ")"));
        hm = mp2;
        hm.forEach((v, k) -> log.info("(" + k + " -> " + v + ")"));
        hm = mp3;
        hm.forEach((v, k) -> log.info("(" + k + " -> " + v + ")"));
    }

    private void printNames(List<String> list) {
        list.forEach(log::info);
    }

    private void oldDaysPrintNames(List list) {
        for (int i = 0; i < list.size(); i++) {
            String name = (String) list.get(i);
            log.info(name);
        }
    }

    private void printDragons(List<Dragon> dragons) {
        for (Dragon dragon : dragons) {
            log.info("{}", dragon);
        }
    }

    private static void printList(List<?> list) {
        for (Object x : list) log.info("{}", x);
    }
}

class Dragon {
}

class Unicorn {
}
