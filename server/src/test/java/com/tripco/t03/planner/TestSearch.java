package com.tripco.t03.planner;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestSearch {
    private Search search;
    private String match;

    @Before
    public void setup(){
        match = "poo";
    }

    @Test
    public void testDefaultSearch(){
        search = new Search();

        assertNull(search.match);
    }

    @Test
    public void testFalseDefault(){
        search = new Search();

        assertNotEquals(match, search.match);
    }

    @Test
    public void testSearch(){
        search = new Search(match);

        assertEquals(match, search.match);
    }

   /* @Test
    public void testMatch(){
        search = new Search(match);
        search.match();
        assertNotNull(search.places);
    }*/
}
