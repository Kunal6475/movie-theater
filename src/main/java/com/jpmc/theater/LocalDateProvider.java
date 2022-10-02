package com.jpmc.theater;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LocalDateProvider {
    private static LocalDateProvider instance = null;

    // To ensure it can not instantiated from outside
    private LocalDateProvider(){
    }

    /**
     * @return make sure to return singleton instance
     */
    public static LocalDateProvider singleton() {
        if (instance == null) {
            instance = new LocalDateProvider();
        }
            return instance;
        }

    public LocalDate currentDate() {
            return LocalDate.now();
    }
}
