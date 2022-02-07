package com.nc.ocp.func.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StreamsTest {

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
        s.map(String::length).forEach(str -> log.info("{}", str));

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
        funcInterfacesTestForPrimitives();
        threeDigit(Optional.of(567));
        threeDigitFunc(Optional.of(569));
        collectorsTest();
        collectingIntoMapsTest();
    }

    private void peekChangingStateTest() {
        List<Integer> numbers = new ArrayList<>();
        List<Character> letters = new ArrayList<>();
        Collections.addAll(numbers, 1, 20, 50);
        Collections.addAll(letters, 'a', 'd');
        Stream<List<?>> stream = Stream.of(numbers, letters);
        stream.peek(l -> l.remove(0)).map(List::size).forEach(size -> log.info("{}", size));
    }

    // This is the comparasion of how we can implement the same functional in j7 and j8.
    // We need to get first two names alphabetically that are four characters long.
    private void java7example(List<String> names) {
        List<String> filtered = new ArrayList<>();
        for (String name : names) {
            if (name.length() == 4) filtered.add(name);
        }
        Collections.sort(filtered);
        Iterator<String> iter = filtered.iterator();
        if (iter.hasNext()) log.info(iter.next());
        if (iter.hasNext()) log.info(iter.next());
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
        log.info("{}", stream.reduce(0, (s, n) -> s += n));

        IntStream intStream = IntStream.of(1, 2, 6);
        OptionalDouble avg = intStream.average();
        log.info("average: " + avg.getAsDouble());

        LongStream longStream = LongStream.of(5, 10);
        long sum = longStream.sum();
        log.info("Long sum: " + sum);

        DoubleStream doubleStream = DoubleStream.generate(() -> Math.PI);
        OptionalDouble min = doubleStream.limit(5).min();
        log.info("Double min: " + min.getAsDouble());
    }

    private void funcInterfacesTestForPrimitives() {
        BooleanSupplier b1 = () -> true;
        BooleanSupplier b2 = () -> Math.random() > .5;
        log.info("Boolean supplier 1: " + b1.getAsBoolean());
        log.info("Boolean supplier 2: " + b2.getAsBoolean());
    }

    private void threeDigit(Optional<Integer> optional) {
        if (optional.isPresent()) {
            Integer num = optional.get();
            String str = "" + num;
            if (str.length() == 3) {
                log.info("three digit number: " + str);
            }
        }
    }

    private void threeDigitFunc(Optional<Integer> optional) {
        optional.map(n -> n + "")
                .filter(s -> s.length() == 3)
                .ifPresent(v -> log.info("three digit number: " + v));
    }

    private void collectorsTest() {
        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
        String result = ohMy.collect(Collectors.joining(", "));
        log.info("Oh my collection: " + result);

        Stream<String> ohMy1 = Stream.of("lions", "tigers", "bears");
        Double result1 = ohMy1.collect(Collectors.averagingInt(String::length));
        log.info("Oh my collection: " + result1);

        Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears");
        TreeSet<String> result2 = ohMy2.filter(s -> s.startsWith("t"))
                .collect(Collectors.toCollection(TreeSet::new));
        log.info("Collect ot tree set: " + result2);
    }

    private void collectingIntoMapsTest() {
        Stream<String> ohMy1 = Stream.of("lions", "tigers", "bears");
        Map<String, Integer> map1 = ohMy1.collect(
                Collectors.toMap(s -> s, String::length)
        );
        log.info("Stream into map example 1: " + map1);

        Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears");
        Map<Integer, String> map2 = ohMy2.collect(
                Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + ", " + s2, TreeMap::new)
        );
        log.info("Stream into map example 2: " + map2 + ", map type: " + map2.getClass());

        Stream<String> ohMy3 = Stream.of("lions", "tigers", "bears");
        Map<Integer, List<String>> map3 = ohMy3.collect(
                Collectors.groupingBy(String::length/*, TreeMap::new, Collectors.toSet()*/)
        );
        log.info("Stream into map example 3: " + map3 + ", map type: " + map3.getClass());

        Stream<String> ohMy4 = Stream.of("lions", "tigers", "bears");
        Map<Boolean, List<String>> map4 = ohMy4.collect(
                Collectors.partitioningBy(s -> s.length() <= 5)
        );
        log.info("Stream into map example 4: " + map4);

//        Stream<String> ohMy5 = Stream.of("lions", "tigers", "bears");
//        Map<?, ?> map5 = ohMy5.collect(
//                Collectors.groupingBy(
//                        String::length,
//                        Collectors.mapping(s -> s.charAt(0),
//                                Collectors.minBy(Comparator.naturalOrder()))
//                )
//        );
//        log.info("Stream into map example 5: " + map5);
    }
}