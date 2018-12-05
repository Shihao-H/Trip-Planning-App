package com.tripco.t03.server;

import com.tripco.t03.server.Optimization;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestOptimization {
    Optimization opt1 = new Optimization("none", "This trip is not optimized.");
    Optimization opt2 = new Optimization("short", "Nearest neighbor.");
    Optimization opt3 = new Optimization();
    @Test
    public void testOpt1EmptyLabel() {
        assertNotNull(opt1.label);
    }
    @Test
    public void testOpt1EmptyDes() {
        assertNotNull(opt1.description);
    }
    @Test
    public void testOpt1() {
        assertEquals(opt1.label, "none");
        assertEquals(opt1.description, "This trip is not optimized.");
    }
    @Test
    public void testOpt2() {
        assertEquals(opt2.label, "short");
        assertEquals(opt2.description, "Nearest neighbor.");
    }
    
    @Test
    public void testNullOpt() {
        assertNull(opt3.label);
        assertNull(opt3.description);
    }
}
