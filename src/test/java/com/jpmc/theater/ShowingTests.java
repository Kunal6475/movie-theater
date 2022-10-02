package com.jpmc.theater;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowingTests {

    @Test
    void getMovieFee(){
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1),
                2,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(9,0))
        );
        assertEquals(8,showing.getMovieFee());
    }
}
