package org.ranjangeorge.mystash.service.impl.support.lang;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class DateStringConverter {

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    @NotNull
    public Date toDate(
            @NotNull final String dateString)
            throws ParseException {

        return new SimpleDateFormat(DATE_PATTERN)
                .parse(dateString);
    }

    @NotNull
    public Instant toInstant(
            @NotNull final String dateString)
            throws ParseException {

        return new SimpleDateFormat(DATE_PATTERN)
                .parse(dateString)
                .toInstant();
    }

    @NotNull
    public String toString(
            @NotNull final Instant instant) {

        return new SimpleDateFormat(DATE_PATTERN)
                .format(new Date(instant.toEpochMilli()));
    }
}
