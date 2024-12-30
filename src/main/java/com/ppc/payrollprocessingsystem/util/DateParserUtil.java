package com.ppc.payrollprocessingsystem.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DateParserUtil {
    private static final List<DateTimeFormatter> EVENT_DATE_FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("d-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("dd-M-yyyy")
    );

    private static final List<DateTimeFormatter> VALUE_DATE_FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("MM-d-yyyy"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy"),
            DateTimeFormatter.ofPattern("M-d-yyyy"),
            DateTimeFormatter.ofPattern("M-dd-yyyy")
    );

    public static LocalDate parseEventDate(String dateStr) {
        for (DateTimeFormatter formatter : EVENT_DATE_FORMATTERS) {
            try {
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException ignored) {
            }
        }
        throw new DateTimeParseException("Unable to parse event date", dateStr, 0);
    }

    public static LocalDate parseValueDate(String dateStr) {
        for (DateTimeFormatter formatter : VALUE_DATE_FORMATTERS) {
            try {
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException ignored) {
            }
        }
        throw new DateTimeParseException("Unable to parse value date", dateStr, 0);
    }
}
