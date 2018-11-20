package com.tripco.t03.planner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestFilter {

    private Filter filter;
    private String name;
    private String[] values;

    @Before
    public void setUp(){
        name = "country";
        values = new String[]{"Asia", "Europe"};
    }

    @Test
    public void testDefault(){
        filter = new Filter();

        Assert.assertNull(filter.name);
    }

    @Test
    public void testFilter(){
        filter = new Filter(name, values);

        Assert.assertNotNull(filter.values);
    }
}

