package com.tripco.t03.planner;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestOptimize {
    private Optimize opt;
    private Trip trip;

    @Before
    public void setUp() {
        Option opt = new Option("miles");
        ArrayList<Place> places = new ArrayList<>();
        places.add(new Place("P1", "three", 18, -104));
        places.add(new Place("P2", "one", 41.000155556, -109.05));
        places.add(new Place("P3", "four", 39, 116));
        places.add(new Place("P4", "two", 41.00055556, -102.05166667));
        trip = new Trip(opt, places);
        trip.plan();
    }

    @Test
    public void testOptimize(){
        opt = new Optimize(trip);

        assertNotNull(opt);
    }

    @Test
    public void testGetOptimalTrip(){
        opt = new Optimize(trip);
        Distance[] optTrip = opt.getOptimalTrip();

        assertEquals(optTrip.length, trip.places.size());
    }
}
