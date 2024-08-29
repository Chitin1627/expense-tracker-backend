package com.backend.expensetracker.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public static String formatStartOfMonth() {
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDateTime startOfMonth = firstDayOfMonth.atStartOfDay();
        return formatDate(startOfMonth);
    }

    public static String formatEndOfMonth() {
        LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        LocalDateTime endOfMonth = lastDayOfMonth.atStartOfDay().plusDays(1);
        return formatDate(endOfMonth);
    }

    private static String formatDate(LocalDateTime dateTime) {
        return dateTime.atOffset(ZoneOffset.UTC).format(formatter);
    }
}
