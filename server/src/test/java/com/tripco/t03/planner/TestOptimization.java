package com.tripco.t03.planner;

import com.tripco.t03.server.Optimization;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestOptimization {

    Optimization opt1 = new Optimization("none", "This trip is not optimized.");
    Optimization opt2 = new Optimization("short", "Nearest neighbor.");

    @Test
    public void testOpt1EmptyLabel() {
        assertNotNull(opt1.label);
    }

    @Test
    public void testOpt1EmptyDes() {
        assertNotNull(opt1.description);
    }

    @Test
    public void testOpt1Label() {
        assertEquals(opt1.label, "none");
    }

    @Test
    public void testOpt1Description() {
        assertEquals(opt1.description, "This trip is not optimized.");
    }

    @Test
    public void testOpt2Label() {
        assertEquals(opt2.label, "short");
    }

    @Test
    public void testOpt2Description() {
        assertEquals(opt2.description, "Nearest neighbor.");
    }

}
