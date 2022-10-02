package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {

    @Test
    void calculateTicketPriceWhenNotSpecialCode(){
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(5,0)));
        assertEquals(12.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void calculateTicketPriceWhenSpecialCode(){
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(5,0)));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void calculateTicketPriceWhen1stOfDay(){
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(5,0)));
        assertEquals(9.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void calculateTicketPriceWhen2ndOfDay(){
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(5,0)));
        assertEquals(10.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void calculateTicketPriceWhen7thOfDay(){
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(5,0)));
        assertEquals(11.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void calculateTicketPriceWhen5thOfDay(){
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(5,0)));
        assertEquals(12.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void calculateTicketPriceWhenShowStartTimeBefore11AM(){
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(10,0)));
        assertEquals(12.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void calculateTicketPriceWhenShowStartTimeAt11AM(){
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(11,0)));
        assertEquals(7.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void calculateTicketPriceWhenShowStartTimeAfter11AMBefore4PM(){
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(12,0)));
        assertEquals(7.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void calculateTicketPriceWhenShowStartTimeAt4PM(){
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(16,0)));
        assertEquals(7.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void calculateTicketPriceWhenShowStartTimeAfter4PM(){
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(17,0)));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void calculateTicketPriceWhenMultipleDiscounts(){
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(12,0)));
        assertEquals(7, spiderMan.calculateTicketPrice(showing));
    }
}
