package com.nc.ocp.date_str_loc;

import com.nc.ocp.date_str_loc.date.DateTest;
import com.nc.ocp.date_str_loc.locale.InternationalAndLocalizationTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        log.info("Date, Strings, Localization tests...");
        new DateTest().run();
        new InternationalAndLocalizationTest().run();
    }
}
