package com.tripco.t03.planner;

import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCalculate {

    private Distance dist;

    @Test
    public void testOptDistance(){
        Place origin = new Place("orig", "origin",40.582778, 105.055);
        Place destination = new Place("dest", "destination", 40.618056, 103.2125);
        double radius = 3959.0;

        assertEquals(Calculate.optDistance(origin, destination, radius), 97, 1);
    }

    @Test
    public void testMiles(){
        Place origin = new Place("orig", "origin",40.582778, 105.055);
        Place destination = new Place("dest", "destination", 40.618056, 103.2125);
        dist = new Distance(origin, destination, "miles");

        dist.setDistance();
        assertEquals(97, dist.distance, 1);
    }
    
    @Test
    public void testPlacesMiles(){
        Place origin = new Place("orig", "origin",40.582778, 105.055);
        Place destination = new Place("dest", "destination", 40.618056, 103.2125);
        
        assertEquals(97, Calculate.calcDistance(origin, destination, "miles"), 1);
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
    public void testKiloPlace(){
        Place origin = new Place("orig", "origin",40.582778, 105.055);
        Place destination = new Place("dest", "destination", 36.169722, 115.140278);
        long distance = Calculate.calcDistance(origin, destination, "kilometers");
    
        assertEquals(1005, distance, 1);
    }

    @Test
    public void testNautical(){
        Place origin = new Place("orig", "origin",40.582778, 105.055);
        Place destination = new Place("dest", "destination", 36.169722, 115.140278);
        dist = new Distance(origin, destination, "nautical miles");
    
        dist.setDistance();
    
        assertEquals(543.0, dist.distance, 1);
    }
    
    @Test
    public void testNauticalPlace(){
        Place origin = new Place("orig", "origin",40.582778, 105.055);
        Place destination = new Place("dest", "destination", 36.169722, 115.140278);
        long distance = Calculate.calcDistance(origin, destination, "nautical miles");
        
        assertEquals(543, distance, 1);
    }
    
    @Test
    public void testUD(){
        Place origin = new Place("orig", "origin",40.582778, 105.055);
        Place destination = new Place("dest", "destination", 36.169722, 115.140278);
        dist = new Distance(origin, destination, "user defined", "feet", 20925721.784777);

        dist.setDistance();

        assertEquals(3303889, dist.distance, 3);
    }
    
    @Test
    public void testGetDistance(){
        Place origin = new Place("orig", "origin",40.582778, 105.055);
        Place destination = new Place("dest", "destination", 36.169722, 115.140278);
        dist = new Distance(origin, destination, "nautical miles");
    
        Calculate calc = new Calculate(dist);
    
        String json = calc.getDistance();
        Gson gson = new Gson();
        
        Distance newDist = gson.fromJson(json, Distance.class);
        
        assertEquals(newDist.origin.name, dist.origin.name);
    }
}
