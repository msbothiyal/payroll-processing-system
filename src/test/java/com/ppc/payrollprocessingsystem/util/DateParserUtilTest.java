package com.ppc.payrollprocessingsystem.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateParserUtilTest {

    @Test
    public void testParseEventDate_Valid() {
        LocalDate date = DateParserUtil.parseEventDate("12-05-2023");
        assertEquals(LocalDate.of(2023, 5, 12), date);
    }

    @Test
    public void testParseEventDate_Invalid() {
        assertThrows(DateTimeParseException.class, () -> {
            DateParserUtil.parseEventDate("05-13-2022");
        });
    }

    @Test
    public void testParseValueDate_Valid() {
        LocalDate date = DateParserUtil.parseValueDate("05-12-2023");
        assertEquals(LocalDate.of(2023, 5, 12), date);
    }

    @Test
    public void testParseValueDate_Invalid() {
        assertThrows(DateTimeParseException.class, () -> {
            DateParserUtil.parseValueDate("13-05-2022");
        });
    }
}