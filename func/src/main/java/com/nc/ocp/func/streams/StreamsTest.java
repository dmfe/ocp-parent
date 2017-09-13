package com.nc.ocp.func.streams;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
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

        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        s.map(String::length).forEach(log::info);

        List<String> one = Arrays.asList();
        List<String> two = Arrays.asList("Bonobo");
        List<String> three = Arrays.asList("Mama Gorilla", "Baby Gorilla");
        Stream<List<String>> animals = Stream.of(one, two, three);
        animals.flatMap(List::stream).forEach(log::info);

        Stream<String> sortEx = Stream.of("brown-", "bear-");
        sortEx.sorted().forEach(log::info);
        Stream<String> revSortEx = Stream.of("brown bear-", "grizzly-");
        revSortEx.sorted(Comparator.reverseOrder()).forEach(log::info);

        Stream<String> peekingTestStream = Stream.of("black bear", "brown bear", "grizzly");
        long count = peekingTestStream.filter(str -> str.startsWith("g"))
                .peek(log::info).count();
        log.info("***Peeking test*** stream count: " + count);

        peekChangingStateTest();
        java7example(Arrays.asList("Toby", "Andrew", "Goose", "Alex", "mike"));
        java8example(Arrays.asList("Toby", "Andrew", "Goose", "Alex", "mike"));
        primitiveTest();
    }

    private void peekChangingStateTest() {
        List<Integer> numbers = new ArrayList<>();
        List<Character> letters = new ArrayList<>();
        Collections.addAll(numbers, 1, 20, 50);
        Collections.addAll(letters, 'a', 'd');
        Stream<List<?>> stream = Stream.of(numbers, letters);
        stream.peek(l -> l.remove(0)).map(List::size).forEach(log::info);
    }

    // This is the comparasion of how we can implement the same functional in j7 and j8.
    // We need to get first two names alphabetically that are four characters long.
    private void java7example(List<String> names) {
        List<String> filtered = new ArrayList<>();
        for(String name : names) {
            if(name.length() == 4) filtered.add(name);
        }
        Collections.sort(filtered);
        Iterator<String> iter = filtered.iterator();
        if(iter.hasNext()) log.info(iter.next());
        if(iter.hasNext()) log.info(iter.next());
    }

    private void java8example(List<String> names) {
        names.stream()
                .filter(n -> n.length() == 4)
                .sorted()
                .limit(2)
                .forEach(log::info);
    }

    private void primitiveTest() {
        Stream<Integer> stream = Stream.of(1, 2, 3);
        log.info(stream.reduce(0, (s, n) -> s += n));

        IntStream intStream = IntStream.of(1, 2, 6);
        OptionalDouble avg = intStream.average();
        log.info("average: " + avg.getAsDouble());

        
    }
}