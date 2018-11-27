package com.tripco.t03.planner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestSearch {
    private Search search;
    private String match;
    private int limit;
    private Filter[] filter;
    
    @Before
    public void setup() {
        match = "poo";
        limit = 13;
        String name1 = "Type";
        String[] values1 = new String[]{"green", "brown", "yellow"};
        String name2 = "continents";
        String[] values2 = new String[]{"Large", "Small", "Massive"};
        filter = new Filter[]{new Filter(name1, values1),
                              new Filter(name2, values2)};
    }
    
    @Test
    public void testDefaultSearch() {
        search = new Search();
        
        assertNull(search.match);
    }
    
    @Test
    public void testFalseDefault() {
        search = new Search();
        
        assertNotEquals(match, search.match);
    }
    
    @Test
    public void testSearch() {
        search = new Search(match);
        
        assertEquals(match, search.match);
    }
    
    @Test
    public void testSearchMatchLimit() {
        search = new Search(match, limit);
        
        Assert.assertTrue((search.match != null)
                          && (search.limit > 0));
    }
    
    @Test
    public void testSearchMatchFilter() {
        search = new Search(match, filter);
        
        Assert.assertTrue((search.match.equals("poo"))
                          && (search.filters != null));
    }
    
    @Test
    public void testSearchAll() {
        search = new Search(match, limit, filter);
        
        Assert.assertTrue((search.match != null)
                          && (search.limit > 0)
                          && (search.filters != null));
    }
    
    @Test
    public void testGetQuery() {
        search = new Search(match, limit, filter);
        String expected = "AND Type IN (\"green\", \"brown\", \"yellow\")\n"
                          + "AND continents.name IN (\"Large\", \"Small\", \"Massive\")\n";
        assertEquals(expected, search.getQuery());
    }
}
