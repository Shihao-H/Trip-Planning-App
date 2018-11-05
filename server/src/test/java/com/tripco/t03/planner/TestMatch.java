package com.tripco.t03.planner;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMatch {
    private Search search;
    private Match match;
    
    @Before
    public void setup(){
        search = new Search("poo");
    }
    
    @Test
    public void testMatch(){
        match = new Match(search);
        String json = match.getMatch();
    
        Gson gson = new Gson();
        Search result = gson.fromJson(json, Search.class);
    
        Assert.assertEquals(result.match, search.match);
    }
}
