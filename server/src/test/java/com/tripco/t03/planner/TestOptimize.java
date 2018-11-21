package com.tripco.t03.planner;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class TestOptimize {
    private Optimize optimal;
    private Trip userDef;
    private Trip shorter;
    private Trip trip;
    private long totalDistance;

    @Before
    public void setUp() throws Exception {
        Option opt = new Option("miles");
        Option optShorter = new Option("miles", "shorter");
        Option optUserDef = new Option("user defined","metal meters", 666.666);
        ArrayList<Place> places = new ArrayList<>();
        places.add(new Place("P1", "three", 18.0, -104.0));
        places.add(new Place("P2", "one", 41.000155556, -109.05));
        places.add(new Place("P3", "four", 39.0, 116.0));
        places.add(new Place("P4", "two", 41.00055556, -102.05166667));
        places.add(new Place("P1" , "Brighton", 39.87, -104.33));
        places.add(new Place("P2" , "Alamosa", 37.57, -105.79));
        places.add(new Place("P3", "Littleton", 39.64, -104.33));
        places.add(new Place("P4", "Pagosa Springs", 37.2, -107.05));
        places.add(new Place("P5", "Springfield", 37.3, -102.54));
        places.add(new Place("P6", "Las Animas", 37.93, -103.08));
        places.add(new Place("P7", "Boulder", 40.09, -105.4));
        places.add(new Place("P8", "Broomfield", 39.95, -105.05));
        places.add(new Place("P9", "Salida", 38.74, -106.32));
        places.add(new Place("P10", "Cheyenne Wells", 38.84, -102.6));
        places.add(new Place("P11", "Georgetown", 39.69, -105.67));
        places.add(new Place("P12", "Conejos", 37.21, -106.18));
        places.add(new Place("P13", "San Luis", 37.28, -105.43));
        places.add(new Place("P14", "Ordway", 38.32, -103.79));
        trip = new Trip(opt, places);
        trip.plan();
        Long[] i = new Long[trip.distances.size()];
        trip.distances.toArray(i);
        Stream<Long> ss = Arrays.stream(i);
        totalDistance = ss.reduce(0L, (a, b) -> a + b);
        userDef = new Trip(optUserDef, places);
        userDef.plan();
        shorter = new Trip(optShorter, places);
        shorter.plan();
    }

    @Test
    public void testOptimize() throws Exception {
        optimal = new Optimize(trip);

        assertNotNull(optimal);
    }

    @Test
    public void testGetOptimalTripDistance() throws Exception {
        optimal = new Optimize(trip);
        optimal.getOptimalTrip();
        long distance = optimal.getOptimalTripDistance();

        Assert.assertTrue(distance != totalDistance);
    }
    
    @Test
    public void testOptimizeUserDefined() throws Exception {
        optimal = new Optimize(userDef);
        optimal.getOptimalTrip();
        long dist = optimal.getOptimalTripDistance();
        
        Assert.assertNotEquals(dist, totalDistance);
    }

    @Test
    public void testOptimizeShorter() throws Exception {
        optimal = new Optimize(shorter);
        optimal.getOptimalTrip();
        long dist = optimal.getOptimalTripDistance();
        
        Assert.assertNotEquals(dist, totalDistance);
    }
    
    @Test
    public void testGetOptimalTrip() throws Exception {
        optimal = new Optimize(trip);
        Trip optTrip = optimal.getOptimalTrip();

        Assert.assertNotEquals(trip, optTrip);
    }

}
