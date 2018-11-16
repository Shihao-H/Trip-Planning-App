package com.tripco.t03.planner;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestOptions {
    
    private Option emptyOpt;
    private Option opt;
    private Gson gson;
    private String json;
    private Option testOpt;
    
    @Before
    public void setup(){
        emptyOpt = new Option();
        gson = new Gson();
    }
    
    private String buildJsonRequest(){
        return String.format("{\"units\": \"%s\"}", "nautical miles");
    }
    
    private String buildJsonRequest2(){
        return String.format("{\"units\": \"%s\", " + "\"unitName\": \"%s\", "
                             + "\"unitRadius\":%f}", "user defined", "nonsense", 70.0);
    }
    
    private String buildJsonRequest3(){
        return String.format("{\"units\": \"%s\", " + "\"optimization\": \"%s\"}", "miles",
                             "none");
    }
    
    @Test
    public void testEmptyOpt() {
        assertTrue((emptyOpt.units == null) && (emptyOpt.unitName == null));
    }
    
    @Test
    public void testConstructorOptimization(){
        opt = new Option("user defined", "metal meters", 666.666, "short");
        
        assertNotNull(opt.optimization);
    }
    
    @Test
    public void testUnits(){
        json = buildJsonRequest();
        opt = gson.fromJson(json, Option.class);

        assertEquals(opt.units, "nautical miles");
    }

    @Test
    public void testUnitName(){
        json = buildJsonRequest2();
        opt = gson.fromJson(json, Option.class);

        assertNotNull(opt.unitName);
    }

    @Test
    public void testUnitRadius(){
        json = buildJsonRequest2();
        opt = gson.fromJson(json, Option.class);

        assertNotNull(opt.unitRadius);
    }

    @Test
    public void testUnitOptimization(){
        json = buildJsonRequest3();
        opt = gson.fromJson(json, Option.class);

        assertNotNull(opt.optimization);
    }

    @Test
    public void testUnitMap(){
        testOpt = new Option("miles", "none", "svg");
        assertNotNull(testOpt.map);
    }

    @Test
    public void testUserDefinedUnitMap(){
        testOpt = new Option("user defined", "meters", 1000.0, "none" , "svg");
        assertNotNull(testOpt.map);
    }

    @Test
    public void testOptWithOpt() {
        testOpt = new Option("miles", "none");

        assertTrue((testOpt.units.equalsIgnoreCase("miles"))
                   && (testOpt.optimization.equalsIgnoreCase("none")));
    }

    @Test
    public void testToStringMiles() {
        testOpt = new Option("miles", "none");
        assertEquals(testOpt.toString(), "Option: units: miles");
    }
    
    @Test
    public void testToStringUserDefined() {
        testOpt = new Option("user defined", "meters", 1000.0);
        assertEquals(testOpt.toString(), "Option: units: user defined, Unit name: meters, Unit Radius of Earth: 1000.0\n");
    }

    @Test
    public void testEqualsUserDefined() {
        Option actualOption = new Option("user defined", "meters", 1000.0);
        testOpt = new Option("user defined", "meters", 1000.0);
        assertTrue(actualOption.equals(testOpt));
    }
    
    @Test
    public void testEqualsKilo() {
        Option actualOption = new Option("kilometers");
        testOpt = new Option("kilometers");
        assertTrue(actualOption.equals(testOpt));
    }
}
