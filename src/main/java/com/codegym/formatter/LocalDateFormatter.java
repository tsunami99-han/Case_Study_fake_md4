package com.codegym.formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;


public class LocalDateFormatter implements Formatter<LocalDate> {
    private DateTimeFormatter formatter;
    private String datePattern;

    public LocalDateFormatter(String datePattern) {
        this.datePattern = datePattern;
        this.formatter = DateTimeFormatter.ofPattern(datePattern);
    }
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        try {
            return LocalDate.parse(text,
                    DateTimeFormatter.ofPattern(datePattern));
        } catch (DateTimeParseException e) {
            // the error message will be displayed in <form:errors>
            throw new IllegalArgumentException("invalid date format. Please use this pattern\""
                    + datePattern + "\"");
        }
    }


    @Override
    public String print(LocalDate object, Locale locale) {
        return object.format(formatter);
    }
}