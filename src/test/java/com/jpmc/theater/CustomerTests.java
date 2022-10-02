package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTests {

    @Test
    void toStringTest(){
        Customer john = new Customer("John", "john-id");
        assertEquals("name: John", john.toString());
    }
}
