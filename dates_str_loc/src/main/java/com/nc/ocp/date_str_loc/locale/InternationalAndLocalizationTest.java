package com.nc.ocp.date_str_loc.locale;

import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class InternationalAndLocalizationTest {
    private static final Logger log = Logger.getLogger(InternationalAndLocalizationTest.class);

    public void run() {
        localeTest();
        resourceBundleTest();
        classResourceBundleTest();
        formatTest();
        numberFormatTest();
        parsingTest();
        localDateTimeTest();
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

    private void classResourceBundleTest() {
        ResourceBundle rb = ResourceBundle.getBundle(
                "com.nc.ocp.date_str_loc.locale.resourcebundles.Tax",
                Locale.US);
        log.info(rb.getObject("tax").toString());
        log.info(((UsTaxCode)rb.getObject("tax")).getCode());
    }

    private void formatTest() {
        ResourceBundle rb = ResourceBundle.getBundle("Zoo", new Locale("en"));
        log.info(MessageFormat.format(rb.getString("named hello"), "Moby", "Dick"));
    }

    private void numberFormatTest() {
        int attendeesPerYear = 3_200_000;
        int attendeesPerMonth = attendeesPerYear / 12;
        double price = 48;
        NumberFormat us = NumberFormat.getInstance(Locale.US);
        NumberFormat usc = NumberFormat.getCurrencyInstance(Locale.US);
        log.info(us.format(attendeesPerMonth));
        log.info(usc.format(price));
        NumberFormat gr = NumberFormat.getInstance(Locale.GERMANY);
        NumberFormat grc = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        log.info(gr.format(attendeesPerMonth));
        log.info(grc.format(price));
        NumberFormat fr = NumberFormat.getInstance(Locale.CANADA_FRENCH);
        NumberFormat frc = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);
        log.info(fr.format(attendeesPerMonth));
        log.info(frc.format(price));
    }

    private void parsingTest() {
        String amt = "$92,807.99";
        NumberFormat cf = NumberFormat.getCurrencyInstance(Locale.US);
        try {
            double value = (Double) cf.parse(amt);
            log.info("currency value: " + value);
        } catch (ParseException ex) {
            log.error("Currency parse exception.", ex);
        }

        //DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse("");
        //LocalDate date = LocalDate.of(2018, Calendar.JUNE, 40); // DateTimeException
    }

    private void localDateTimeTest() {
        LocalDate localDate = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime localTime = LocalTime.of(11, 12, 34);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        log.info(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        log.info(localTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
        log.info(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        DateTimeFormatter shortDateTime = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        log.info(shortDateTime.format(localDate));
        //log.info(shortDateTime.format(localTime)); java.time.temporal.UnsupportedTemporalTypeException
        log.info(shortDateTime.format(localDateTime));

        log.info(localDate.format(shortDateTime));
        //log.info(localTime.format(shortDateTime));
        log.info(localDateTime.format(shortDateTime));
    }
}
