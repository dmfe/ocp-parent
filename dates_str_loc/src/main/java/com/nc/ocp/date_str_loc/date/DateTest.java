package com.nc.ocp.date_str_loc.date;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateTest {

    public void run() {
        instantiatingTest();
        modificationTest();
        periodTest();
        durationTest();
        instantTest();
        daylightSavingTest();
    }

    private void instantiatingTest() {
        log.info("{}", LocalDate.now());
        log.info("{}", LocalTime.now());
        log.info("{}", LocalDateTime.now());
        log.info("{}", ZonedDateTime.now());

        LocalDate date1 = LocalDate.of(2015, Month.JANUARY, 20);
        LocalDate date2 = LocalDate.of(2015, 1, 20);
        log.info("{}", date1);
        log.info("{}", date2);

        LocalTime time1 = LocalTime.of(6, 15);
        LocalTime time2 = LocalTime.of(6, 15, 30);
        LocalTime time3 = LocalTime.of(6, 15, 30, 200);
        log.info("{}", time1);
        log.info("{}", time2);
        log.info("{}", time3);

        LocalDateTime dateTime1 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);
        LocalDateTime dateTime2 = LocalDateTime.of(date1, time1);
        log.info("{}", dateTime1);
        log.info("{}", dateTime2);

        ZoneId zone = ZoneId.of("Europe/Moscow");
        ZonedDateTime zoned1 = ZonedDateTime.of(2015, 1, 20, 6, 15, 30, 200, zone);
        log.info("{}", zoned1);

        log.info("{}", ZoneId.systemDefault());
//        ZoneId.getAvailableZoneIds().stream().filter(z -> z.contains("US") || z.contains("America"))
//                .sorted().forEach(log::info);
//        ZoneId.getAvailableZoneIds().stream().filter(z -> z.contains("Europe"))
//                .sorted().forEach(log::info);

    }

    private void modificationTest() {
        LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);
        log.info("{}", date);
        date = date.plusDays(2);
        log.info("{}", date);
        date = date.plusWeeks(2);
        log.info("{}", date);
        date = date.plusMonths(2);
        log.info("{}", date);
        date = date.plusYears(1);
        log.info("{}", date);

        LocalDate date1 = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime time1 = LocalTime.of(5, 15);
        LocalDateTime dateTime1 = LocalDateTime.of(date1, time1);
        log.info("{}", dateTime1);
        dateTime1 = dateTime1.minusDays(2);
        log.info("{}", dateTime1);
        dateTime1 = dateTime1.minusHours(3);
        log.info("{}", dateTime1);
        dateTime1 = dateTime1.minusSeconds(30);
        log.info("{}", dateTime1);
    }

    private void periodTest() {
        LocalDate start = LocalDate.of(2015, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2015, Month.MARCH, 30);

        performEnimalEnrichment(start, end, Period.ofMonths(1));

        log.info("{}", Period.ofMonths(3));

        LocalDate date = LocalDate.of(2015, 1, 20);
        LocalTime time = LocalTime.of(6, 15);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        Period period = Period.ofMonths(1);

        log.info("{}", date.plus(period));
        log.info("{}", dateTime.plus(period));
        //log.info(time.plus(period));
    }

    private void performEnimalEnrichment(LocalDate start, LocalDate end, Period period) {
        LocalDate upTo = start;
        while (upTo.isBefore(end)) {
            log.info("give new toy: " + upTo);
            upTo = upTo.plus(period);
        }
    }

    private void durationTest() {
        Duration daily = Duration.ofDays(1);
        log.info("{}", daily);
        Duration hourly = Duration.ofHours(1);
        log.info("{}", hourly);
        Duration everyMinute = Duration.ofMinutes(1);
        log.info("{}", everyMinute);
        Duration everyTenSeconds = Duration.ofSeconds(10);
        log.info("{}", everyTenSeconds);
        Duration everyMilli = Duration.ofMillis(1);
        log.info("{}", everyMilli);
        Duration everyNano = Duration.ofNanos(1);
        log.info("{}", everyNano);
    }

    private void instantTest() {
        Instant now = Instant.now();
        //some time consuming operations
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (Exception ex) {
                log.info("Thread with id " + Thread.currentThread().getId() + " was interrupted.");
            }
        }
        Instant later = Instant.now();

        Duration duration = Duration.between(now, later);
        log.info("{}", duration.toMillis());
    }

    private void daylightSavingTest() {
        LocalDate date = LocalDate.of(2016, Month.MARCH, 13);
        LocalTime time = LocalTime.of(1, 30);
        ZoneId zoneId = ZoneId.of("US/Eastern");
        ZonedDateTime dateTime = ZonedDateTime.of(date, time, zoneId);

        log.info("{}", dateTime);
        dateTime = dateTime.plusHours(1);
        log.info("{}", dateTime);

        LocalDate date1 = LocalDate.of(2016, Month.NOVEMBER, 6);
        LocalTime time1 = LocalTime.of(1, 30);
        ZonedDateTime dateTime1 = ZonedDateTime.of(date1, time1, zoneId);

        log.info("{}", dateTime1);
        dateTime1 = dateTime1.plusHours(1);
        log.info("{}", dateTime1);
    }
}
