package com.tripco.t03.planner;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestDistance {
    private Distance dist;

    //Code in this file is from in class example of TestDistance by Dave Matthews

    @Test
    public void testDistance4(){
        Place origin = new Place("orig", "origin",18, -104);
        Place destination = new Place("dest", "destination", 39, 116);
        dist = new Distance(origin, destination, "kilometers", 12434);

        assertEquals(dist.distance, 12434);
    }

    @Test
    public void testDistanceMiles(){
        Place origin = new Place("orig", "origin",41.000155556, -109.05);
        Place destination = new Place("dest", "destination", 41.00055556, -102.05166667);
        dist = new Distance(origin, destination, "miles");

        assertTrue(dist.origin.equals(origin) && dist.destination.equals(destination) && dist.units.equalsIgnoreCase("miles"));
    }

    @Test
    public void testDistanceKM(){
        Place origin = new Place("orig", "origin",41.000155556, -109.05);
        Place destination = new Place("dest", "destination", 41.00055556, -102.05166667);
        dist = new Distance(origin, destination, "kilometers");

        assertTrue(dist.origin.equals(origin) && dist.destination.equals(destination) && dist.units.equalsIgnoreCase("kilometers"));
    }

    @Test
    public void testDistanceNM(){
        Place origin = new Place("orig", "origin",41.000155556, -109.05);
        Place destination = new Place("dest", "destination", 41.00055556, -102.05166667);
        dist = new Distance(origin, destination, "nautical miles");

        assertTrue(dist.origin.equals(origin) && dist.destination.equals(destination) && dist.units.equalsIgnoreCase("nautical miles"));
    }

    @Test
    public void testDistanceUD(){
        Place origin = new Place("orig", "origin",41.000155556, -109.05);
        Place destination = new Place("dest", "destination", 41.00055556, -102.05166667);
        dist = new Distance(origin, destination, "user defined", "some", 70.0);

        assertTrue(dist.origin.equals(origin) && dist.destination.equals(destination) && dist.units.equalsIgnoreCase("user defined")
                && dist.unitName.equalsIgnoreCase("some") && dist.unitRadius == 70.0);
    }

    @Test
    public void testMiles(){
        Place origin = new Place("orig", "origin",41.000155556, -109.05);
        Place destination = new Place("dest", "destination", 41.00055556, -102.05166667);
        dist = new Distance(origin, destination, "miles");

        dist.setDistance();
        assertEquals(366, dist.distance, 1);

    }

    @Test
    public void testMiles2(){
        Place origin = new Place("orig", "origin",18, -104);
        Place destination = new Place("dest", "destination", 39, 116);
        dist = new Distance(origin, destination, "miles");

        dist.setDistance();
        assertEquals(7726, dist.distance, 1);

    }

    @Test
    public void testKilo(){
        Place origin = new Place("orig", "origin",18, -104);
        Place destination = new Place("dest", "destination", 39, 116);
        dist = new Distance(origin, destination, "kilometers");

        dist.setDistance();

        assertEquals(12434, dist.distance, 1);
    }

    @Test
    public void testNM(){
        Place origin = new Place("orig", "origin",18, -104);
        Place destination = new Place("dest", "destination", 39, 116);
        dist = new Distance(origin, destination, "nautical miles");

        dist.setDistance();

        assertEquals(6713, dist.distance, 1);
    }

    @Test
    public void testUD(){
        Place origin = new Place("orig", "origin",18, 104);
        Place destination = new Place("dest", "destination", 25, -105);
        dist = new Distance(origin, destination, "user defined", "some", 42.0);

        dist.setDistance();

        assertEquals(94, dist.distance, 3);
    }

}
