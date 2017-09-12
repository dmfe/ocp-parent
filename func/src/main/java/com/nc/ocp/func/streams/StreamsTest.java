package com.nc.ocp.func.streams;

import org.apache.log4j.Logger;

import java.util.Optional;
import java.util.stream.Stream;

public class StreamsTest {
    private static final Logger log = Logger.getLogger(StreamsTest.class);

    public void run() {
//        Stream<Double> randoms = Stream.generate(Math::random);
//        randoms.forEach(System.out::println);

//        Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);
//        oddNumbers.forEach(x -> {
//            System.out.println(x);
//            try {
//                Thread.currentThread().sleep(2000);
//            } catch(InterruptedException e) {}
//        });

        Stream<String> s1 = Stream.of("monkey", "gorilla", "bonobo");
        Optional<String> min = s1.min((str1, str2) -> str1.compareTo(str2));
        min.ifPresent(x -> log.info("Minimum value is " + x));
        //log.info(s.count());

        Stream<String> s2 = Stream.of("Monkey", "Gorilla", "Bonobo");
        s2.forEach(log::info);

        Stream<String> stream = Stream.of("w", "o", "l", "f");
        String word = stream.reduce("", (s, c) -> s + c);
        log.info(word);
    }
}