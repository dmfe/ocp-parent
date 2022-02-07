package com.nc.ocp.gencol;

import com.nc.ocp.gencol.add.Addition8Test;
import com.nc.ocp.gencol.generics.GenericTest;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
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
        for (Object s : list)
            System.out.println(s);
    }

    public void f() {
        Hello<T> g = new Hello<>();
    }
}
