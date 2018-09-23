package com.tripco.t03.planner;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestOptions {

    private Option opt;
    private Gson gson;

    private String buildJsonRequest(String units){
        return String.format("{\"units\": \"%s\"}", units);
    }

    private String buildJsonRequest2(String units, String unitName, double radius){
        return String.format("{\"units\": \"%s\", " + "\"unitName\": \"%s\", " + "\"unitRadius\":%f}", units, unitName, radius);
    }

    @Before
    public void setup(){
        gson = new Gson();
    }

    @Test
    public void testUnits(){
        String json = buildJsonRequest("nautical miles");
        opt = gson.fromJson(json, Option.class);

        assertEquals(opt.units, "nautical miles");
    }

    @Test
    public void testUnitName(){
        String json = buildJsonRequest2("user defined", "nonsense", 70.0);
        opt = gson.fromJson(json, Option.class);

        assertNotNull(opt.unitName);
    }

    @Test
    public void testUnitRadius(){
        String json = buildJsonRequest2("user defined", "nonsense", 70.0);
        opt = gson.fromJson(json, Option.class);

        assertNotNull(opt.unitRadius);
    }
}
