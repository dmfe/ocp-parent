package com.nc.ocp.gencol;

import com.nc.ocp.gencol.add.Addition8Test;
import com.nc.ocp.gencol.generics.GenericTest;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure(Main.class.getClassLoader().getResourceAsStream("log4j.properties"));
        new Main().start();
    }

    private void start() {
        log.info("Generic And Collections Test Started...");
        new Addition8Test().run();
        new GenericTest().run();

    }
}

class Hello<T> {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("one");
        list.add("two");
        list.add(7);
        for(Object s : list)
            System.out.println(s);
    }

    public void f() {
        Hello<T> g = new Hello<>();
    }
}
