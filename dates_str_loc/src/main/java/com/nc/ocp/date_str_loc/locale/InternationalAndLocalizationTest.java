package com.nc.ocp.date_str_loc.locale;

import org.apache.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class InternationalAndLocalizationTest {
    private static final Logger log = Logger.getLogger(InternationalAndLocalizationTest.class);

    public void run() {
        localeTest();
        resourceBundleTest();
    }

    private void localeTest() {
        Locale currentLocale = Locale.getDefault();
        log.info("Current locale: " + currentLocale);

        log.info(Locale.GERMAN);
        log.info(Locale.GERMANY);
        log.info(new Locale("fr"));
        log.info(new Locale("hi", "IN"));

        // Builder pattern
        Locale l1 = new Locale.Builder()
                .setLanguage("en")
                .setRegion("US").build();
        log.info(l1);

        Locale l2 = new Locale.Builder()
                .setLanguage("hi")
                .setRegion("IN").build();
        Locale.setDefault(l2);
        log.info("Default locale has been changed to " + Locale.getDefault());
    }

    private void resourceBundleTest() {
        Locale en = new Locale.Builder().setLanguage("en").setRegion("US").build();
        Locale fr = new Locale.Builder().setLanguage("fr").setRegion("FR").build();
        Locale ru = new Locale.Builder().setLanguage("ru").setRegion("RU").build();
        printProperties(en);
        printProperties(fr);
        printProperties(ru);
        resourceBundlePrintAllRecords(en);
        resourceBundlePrintAllRecords(fr);
        resourceBundlePrintAllRecords(ru);
    }

    private void resourceBundlePrintAllRecords(Locale locale) {
        ResourceBundle rb = ResourceBundle.getBundle("Zoo", locale);
        rb.keySet().stream().map(k -> k + " " + rb.getString(k)).forEach(log::info);
    }

    private void printProperties(Locale locale) {
        ResourceBundle rb = ResourceBundle.getBundle("Zoo", locale);

        log.info(rb.getString("hello"));
        log.info(rb.getString("open"));
    }
}
