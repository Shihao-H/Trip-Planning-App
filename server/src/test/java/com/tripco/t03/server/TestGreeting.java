package com.tripco.t03.server;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestGreeting {

    @Test
    public void testHtml(){
        assertFalse(Greeting.html("poo") == null);
    }

    @Test
    public void testHtmlWithString(){
        String html = "<html><head></head><body><h1>Hello poo!</h1></body></html>";
        assertEquals(Greeting.html("poo"), html);
    }
}
