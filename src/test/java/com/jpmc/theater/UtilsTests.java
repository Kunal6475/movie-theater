package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTests {

    @Test
   void humanReadableFormatWhenMoreThan60MinutesDuration(){
        assertEquals("1 hour 10 minutes" , Utils.humanReadableFormat(Duration.ofMinutes(70)));
    }

    @Test
   void humanReadableFormatWhen60MinutesDuration(){
        assertEquals("1 hour 0 minute" , Utils.humanReadableFormat(Duration.ofMinutes(60)));
    }

    @Test
    void humanReadableFormatWhenLessThan60MinutesDuration(){
        assertEquals("0 hour 50 minutes" , Utils.humanReadableFormat(Duration.ofMinutes(50)));
    }

    @Test
    void handlePluralWhenZero(){
        assertEquals("", Utils.handlePlural(0L));
    }

    @Test
    void handlePluralWhenOne(){
        assertEquals("", Utils.handlePlural(1L));
    }

    @Test
    void handlePluralWhenMoreThanOne(){
        assertEquals("s", Utils.handlePlural(2L));
    }

    @Test
    void surroundWithBraces(){
        String s = "John";
        assertEquals("(" + s + ")" , Utils.surroundWithBraces(s));
    }

    @Test
    void getJsonString(){
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        String expectedJsonString = "{\"title\":\"Spider-Man: No Way Home\",\"duration\":\"1 hour 30 minutes\",\"ticketPrice\":12.5}";
        assertEquals(expectedJsonString, Utils.getJsonString(spiderMan));
    }
}
