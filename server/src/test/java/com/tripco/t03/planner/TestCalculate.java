package com.tripco.t03.planner;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestCalculate {

    private Distance dist;

    @Test
    public void testMiles(){
        Place origin = new Place("orig", "origin",40.582778, 105.055);
        Place destination = new Place("dest", "destination", 40.618056, 103.2125);
        dist = new Distance(origin, destination, "miles");

        dist.setDistance();
        assertEquals(97, dist.distance, 1);
    }

    @Test
    public void testMiles2(){
        Place origin = new Place("orig", "origin",40.582778, 105.055);
        Place destination = new Place("dest", "destination", 36.169722, 115.140278);
        dist = new Distance(origin, destination, "miles");

        dist.setDistance();
        assertEquals(625, dist.distance, 1);
    }

    @Test
    public void testKilo(){
        Place origin = new Place("orig", "origin",40.582778, 105.055);
        Place destination = new Place("dest", "destination", 36.169722, 115.140278);
        dist = new Distance(origin, destination, "kilometers");

        dist.setDistance();

        assertEquals(1005, dist.distance, 1);
    }

    @Test
    public void testUD(){
        Place origin = new Place("orig", "origin",40.582778, 105.055);
        Place destination = new Place("dest", "destination", 36.169722, 115.140278);
        dist = new Distance(origin, destination, "user defined", "feet", 20925721.784777);

        dist.setDistance();

        assertEquals(3303889, dist.distance, 3);
    }
}
