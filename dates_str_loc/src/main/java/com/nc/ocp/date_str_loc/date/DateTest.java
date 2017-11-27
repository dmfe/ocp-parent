package com.nc.ocp.date_str_loc.date;

import org.apache.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTest {
    private static final Logger log = Logger.getLogger(DateTest.class);

    public void run() {
        instantiatingTest();
        modificationTest();
        periodTest();
        instantTest();
        daylighSavingTest();
    }

    private void instantiatingTest() {
        log.info(LocalDate.now());
        log.info(LocalTime.now());
        log.info(LocalDateTime.now());
        log.info(ZonedDateTime.now());

        LocalDate date1 = LocalDate.of(2015, Month.JANUARY, 20);
        LocalDate date2 = LocalDate.of(2015, 1, 20);
        log.info(date1);
        log.info(date2);

        LocalTime time1 = LocalTime.of(6, 15);
        LocalTime time2 = LocalTime.of(6, 15, 30);
        LocalTime time3 = LocalTime.of(6, 15, 30, 200);
        log.info(time1);
        log.info(time2);
        log.info(time3);

        LocalDateTime dateTime1 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);
        LocalDateTime dateTime2 = LocalDateTime.of(date1, time1);
        log.info(dateTime1);
        log.info(dateTime2);

        ZoneId zone = ZoneId.of("Europe/Moscow");
        ZonedDateTime zoned1 = ZonedDateTime.of(2015, 1, 20, 6, 15, 30, 200, zone);
        log.info(zoned1);

        log.info(ZoneId.systemDefault());
//        ZoneId.getAvailableZoneIds().stream().filter(z -> z.contains("US") || z.contains("America"))
//                .sorted().forEach(log::info);
//        ZoneId.getAvailableZoneIds().stream().filter(z -> z.contains("Europe"))
//                .sorted().forEach(log::info);

    }

    private void modificationTest() {
        LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);
        log.info(date);
        date = date.plusDays(2);
        log.info(date);
        date = date.plusWeeks(2);
        log.info(date);
        date = date.plusMonths(2);
        log.info(date);
        date = date.plusYears(1);
        log.info(date);

        LocalDate date1 = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime time1 = LocalTime.of(5, 15);
        LocalDateTime dateTime1 = LocalDateTime.of(date1, time1);
        log.info(dateTime1);
        dateTime1 = dateTime1.minusDays(2);
        log.info(dateTime1);
        dateTime1 = dateTime1.minusHours(3);
        log.info(dateTime1);
        dateTime1 = dateTime1.minusSeconds(30);
        log.info(dateTime1);
    }

    private void periodTest() {
        LocalDate start = LocalDate.of(2015, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2015, Month.MARCH, 30);

        LocalDate upTo = start;
        while (upTo.isBefore(end)) {
            log.info("give new toy: " + upTo);
            upTo = upTo.plusMonths(1);
        }
    }

    private void instantTest() {
        Instant now = Instant.now();
        //some time consuming operations
        for(int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (Exception ex) {
                log.info("Thread with id " + Thread.currentThread().getId() + " was interrupted.");
            }
        }
        Instant later = Instant.now();

        Duration duration = Duration.between(now, later);
        log.info(duration.toMillis());
    }

    private void daylighSavingTest() {
        LocalDate date = LocalDate.of(2016, Month.MARCH, 13);
        LocalTime time = LocalTime.of(1, 30);
        ZoneId zoneId = ZoneId.of("US/Eastern");
        ZonedDateTime dateTime = ZonedDateTime.of(date, time, zoneId);

        log.info(dateTime);
        dateTime = dateTime.plusHours(1);
        log.info(dateTime);

        LocalDate date1 = LocalDate.of(2016, Month.NOVEMBER, 6);
        LocalTime time1 = LocalTime.of(1, 30);
        ZonedDateTime dateTime1 = ZonedDateTime.of(date1, time1, zoneId);

        log.info(dateTime1);
        dateTime1 = dateTime1.plusHours(1);
        log.info(dateTime1);
    }
}