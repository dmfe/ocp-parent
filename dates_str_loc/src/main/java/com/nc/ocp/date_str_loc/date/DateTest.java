package com.nc.ocp.date_str_loc.date;

import org.apache.log4j.Logger;

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
}