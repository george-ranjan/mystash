package org.ranjangeorge.mystash.service.impl.support.lang;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

public class DateStringConverter {

    @NotNull
    public Date toDate(
            @NotNull final String dateString)
            throws ParseException {

        return DateFormat
                .getInstance()
                .parse(dateString);
    }

    @NotNull
    public Instant toInstant(
            @NotNull final String dateString)
            throws ParseException {

        return DateFormat
                .getInstance()
                .parse(dateString)
                .toInstant();
    }

    @NotNull
    public String toString(
            @NotNull final Instant instant) {

        return DateFormat
                .getInstance()
                .format(new Date(instant.toEpochMilli()));
    }
}
