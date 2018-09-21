package com.tripco.t03.planner;

import com.google.gson.Gson;
import com.tripco.t03.planner.Place;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestDistance {
    private Distance dist;
    private Gson gson;
    
    //Code in this file is from in class example of TestDistance by Dave Matthews

    private String buildJsonRequest(double origin[], double dest[], String units){
        String orig = String.format("\"origin\": {\"latitude\":%f, \"longitude\":%f, \"name\": \"orig\"}", origin[0], origin[1]);
        String destin = String.format("\"destination\": {\"latitude\":%f, \"longitude\":%f, \"name\": \"dest\"}", dest[0], dest[1]);
        return String.format("{" + "\"version\": 2," + "\"type\": \"distance\"," + "%s," + "%s,"
                +"\"distance\": 0," + "\"units\": \"%s\"}", orig, destin, units);
    }

    @Before
    public void setup(){
        gson = new Gson();
    }


    @Test
    public void testStaticCalculate(){
        double lat1 = 41.00055556, lon1 = -109.05;
        double lat2 = 41.00055556, lon2 = -102.05166667;

        assertEquals(366, Distance.calcDistance(lat1, lon1, lat2, lon2, "miles"), 1);
    }

    @Test
    public void testDistance(){
        String id = "dnvr", name = "Denver", units = "miles";
        double[] origin = {41.000155556, -109.05};
        double[] destination = {41.00055556, -102.05166667};
        String testRequest = buildJsonRequest(origin, destination, "miles");

        dist = gson.fromJson(testRequest, Distance.class);

        dist.setDistance();
        assertEquals(366, dist.distance, 1);
    }

    public void testKilo(){
        double[] origin = {18, -104};
        double[] destin = {39, 116};
        String testRequest = buildJsonRequest(origin, destin, "kilometers");

        dist = gson.fromJson(testRequest, Distance.class);
        dist.setDistance();

        assertEquals(12434, dist.distance, 1);
    }

    public void testNM(){
        double[] origin = {18, -104};
        double[] destin = {39, 116};
        String testRequest = buildJsonRequest(origin, destin, "nautical miles");

        dist = gson.fromJson(testRequest, Distance.class);
        dist.setDistance();

        assertEquals(6713, dist.distance, 1);
    }

}
