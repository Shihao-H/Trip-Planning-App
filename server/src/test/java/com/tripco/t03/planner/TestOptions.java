package com.tripco.t03.planner;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestOptions {

    private Option emptyOpt = new Option();

    @Test
    public void testEmptyOpt() {
        assertNull(emptyOpt.units = null);
        assertNull(emptyOpt.unitName = null);
    }

    private Option opt;
    private Gson gson;

    private String buildJsonRequest(String units){
        return String.format("{\"units\": \"%s\"}", units);
    }

    private String buildJsonRequest2(String units, String unitName, double radius){
        return String.format("{\"units\": \"%s\", " + "\"unitName\": \"%s\", " + "\"unitRadius\":%f}", units, unitName, radius);
    }

    private String buildJsonRequest3(String units, String optimization){
        return String.format("{\"units\": \"%s\", " + "\"optimization\": \"%s\"}", units, optimization);
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

    @Test
    public void testUnitOptimization(){
        String json = buildJsonRequest3("miles", "none");
        opt = gson.fromJson(json, Option.class);

        assertNotNull(opt.optimization);
    }

    Option testOpt;

    @Test
    public void testOptWithOpt() {
        testOpt = new Option("miles", "none");

        assertEquals(testOpt.units, "miles");
        assertEquals(testOpt.optimization, "none");
    }

    @Test
    public void testToString() {
        testOpt = new Option("miles", "none");
        assertEquals(testOpt.toString(), "Option: units: miles");
        testOpt = new Option("user defined", "meters", 1000.0);
        assertEquals(testOpt.toString(), "Option: units: user defined, Unit name: meters, Unit Radius of Earth: 1000.0\n");
    }

    @Test
    public void testEquals() {
        Option actualOption = new Option("user defined", "meters", 1000.0);
        testOpt = new Option("user defined", "meters", 1000.0);
        assertTrue(actualOption.equals(testOpt));
        actualOption = new Option("kilometers");
        testOpt = new Option("kilometers");
        assertTrue(actualOption.equals(testOpt));
    }
}
