package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LocalDateProviderTests {
    @Test
    void makeSureCurrentDate() {
        assertEquals(LocalDate.now(), LocalDateProvider.singleton().currentDate());
    }

    @Test
    void makeSureNonNullInstanceReturned() {
        assertNotNull(LocalDateProvider.singleton());
    }
}
