package com.tripco.t03.planner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)

// This class contains tests for the Trip class.
public class TestOption {

    Option option;

    // Setup to be done before every test in TestPlan
    @Before
    public void initialize() {
        option = new Option();
        option.units = "miles";
    }

    @Test
    public void testOptionDef() {
        String na = "miles";
        assertEquals(option.units, na);
    }

    @Test
    public void testTrue() {
        // assertTrue checks if a statement is true
        assertTrue(true);
    }

    Option options;
    @Before
    public void initializeReg() {
        options = new Option();
        options.units = "user defined";
        options.unitName = "accurate miles";
        options.unitRadius = 3958.7613;
    }

    @Test
    public void testOptionUser() {
        String na = "user defined";
        String un = "accurate miles";
        double rad = 3958.7613;
        assertEquals(options.units, na);
        assertEquals(options.unitName, un);
        assertEquals(options.unitRadius, rad, 1);
    }
}
