package com.tripco.t03.server;

import com.tripco.t03.planner.Place;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestDriver {
    private Driver testDriver;
    private String match;
    private int limit;
    private String filterQuery;
    private String limitQuery;
    private ArrayList<Place> expected;
    
    @Before
    public void setup(){
        match = "alf";
        filterQuery = "AND continents.name IN (\"North America\")\n"
                      + "AND type IN (\"heliport\")\n";
        limitQuery = "limit 20";
        limit = 20;
        expected = new ArrayList<>();
        expected.add(new Place("0FL4", "Jackson Heliport", 30.705, -85.378304));
        expected.add(new Place("98TX", "Cig 402 Heliport", 27.256701, -98.0942));
        expected.add(new Place("US-VA38", "Lz Alfa Heliport", 36.793201, -75.964699));
    }
    
    @Test
    public void testFind() throws Exception{
        testDriver = new Driver();
        int found = 3;
        testDriver.find(match, limit, filterQuery);
        assertEquals(expected.size(), testDriver.places.size());
        assertEquals(found, testDriver.found);
        assertEquals(limitQuery, testDriver.limitQuery);
        if(testDriver.isTravis != null
           && testDriver.isTravis.equals("true")) {
            assertEquals("jdbc:mysql://127.0.0.1/cs314", testDriver.dburl);
            assertEquals("travis", testDriver.username);
        } else if(testDriver.isDevelopment != null
                  && testDriver.isDevelopment.equals("development")) {
            assertEquals("jdbc:mysql://127.0.0.1:8098/cs314", testDriver.dburl);
            assertEquals("cs314-db", testDriver.username);
        } else {
            assertEquals("jdbc:mysql://faure.cs.colostate.edu/cs314", testDriver.dburl);
            assertEquals("cs314-db", testDriver.username);
        }
    }
    
    @Test
    public void testFindWithZeroLimit() throws Exception{
        limit = 0;
        limitQuery = "";
        testDriver = new Driver();
        int found = 3;
        testDriver.find(match, limit, filterQuery);
        assertEquals(expected.size(),testDriver.places.size()) ;
        assertEquals(found, testDriver.found);
        assertEquals(limitQuery, testDriver.limitQuery);
    }
    
    @Test
    public void testFindException() throws Exception{
        testDriver = new Driver();
        String except = "";
        try{
            filterQuery = "AND continents IN (\"North America\")\n"
                          + "AND type IN (\"heliport\")\n";
            testDriver.find(match, limit, filterQuery);
            } catch(Exception e){
                except = e.toString();
            }
            String expected = "com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: "
                              + "Unknown column \'continents\' in \'where clause\'";
            assertEquals(except, expected);
    }
 
     @Test
    public void testSetLimit2(){
        testDriver = new Driver();
        testDriver.setLimit(0);
        
        assertEquals(testDriver.limitQuery, "");
    }
    
    @Test
    public void testSetPassword(){
        testDriver = new Driver();
        testDriver.setIsTravis(null);
        testDriver.setPassword();
        
        assertEquals(testDriver.password, "eiK5liet1uej");
    }
    
    @Test
    public void testSetPassword2(){
        testDriver = new Driver();
        testDriver.setIsTravis("true");
        testDriver.setPassword();
        
        assertEquals(testDriver.password, null);
    }
    
    @Test
    public void testSetDburlUserName3(){
        testDriver = new Driver();
        testDriver.setIsTravis(null);
        testDriver.setIsDevelopment(null);
        testDriver.setDburlUserName();
        
        assertTrue(testDriver.dburl.equalsIgnoreCase("jdbc:mysql://faure.cs.colostate.edu/cs314"));
    }
    
    @Test
    public void testSetDburlUserName2(){
        testDriver = new Driver();
        testDriver.setIsTravis("true");
        testDriver.setDburlUserName();
        
        assertTrue(testDriver.username.equalsIgnoreCase("travis"));
    }
    
    @Test
    public void testsetDburlUserName1(){
        testDriver = new Driver();
        testDriver.setIsDevelopment("development");
        testDriver.setIsTravis(null);
        testDriver.setDburlUserName();
        
        assertTrue(testDriver.username.equalsIgnoreCase("cs314-db"));
    }
    
    @Test
    public void testIsDevelopment(){
        testDriver = new Driver();
        testDriver.setIsDevelopment("development");
        
        assertTrue(testDriver.isDevelopment.equalsIgnoreCase("development"));
    }
    
    @Test
    public void testSetTravis(){
        testDriver = new Driver();
        testDriver.setIsTravis("true");
        
        assertTrue(testDriver.isTravis.equalsIgnoreCase("true"));
    }
    
    @Test
    public void testSetSearch(){
        String expected = "SELECT world_airports.name, world_airports.municipality, region.name, "
                          + "country.name, continents.name, "
                          + "world_airports.id, world_airports.type, world_airports.longitude, "
                          + "world_airports.latitude, "
                          + "world_airports.elevation "
                          + "FROM continents \n"
                          + "INNER JOIN country ON continents.id = country.continent \n"
                          + "INNER JOIN region ON country.id = region.iso_country \n"
                          + "INNER JOIN world_airports ON region.id = world_airports.iso_region \n"
                          + "WHERE (continents.name LIKE \"%" + match + "%\"  \n"
                          + "OR country.name LIKE \"%" + match + "%\"  \n"
                          + "OR region.name LIKE \"%" + match + "%\"  \n"
                          + "OR world_airports.municipality LIKE \"%" + match + "%\" \n"
                          + "OR world_airports.id LIKE \"%" + match + "%\" \n"
                          + "OR world_airports.name LIKE \"%" + match + "%\") \n"
                          + filterQuery
                          + "ORDER BY continents.name, country.name, region.name, "
                          + "world_airports.municipality, world_airports.name ASC "
                          + limitQuery;
        testDriver = new Driver();
        testDriver.setLimit(limit);
        testDriver.setSearch(match, filterQuery);
        
        assertEquals(expected, testDriver.search);
    }
    
    @Test
    public void testSetCount(){
        String expected = "SELECT count(*) "
                          + "FROM continents \n"
                          + "INNER JOIN country ON continents.id = country.continent \n"
                          + "INNER JOIN region ON country.id = region.iso_country \n"
                          + "INNER JOIN world_airports ON region.id = world_airports.iso_region \n"
                          + "WHERE (continents.name LIKE \"%" + match + "%\"  \n"
                          + "OR country.name LIKE \"%" + match + "%\"  \n"
                          + "OR region.name LIKE \"%" + match + "%\"  \n"
                          + "OR world_airports.municipality LIKE \"%" + match + "%\" \n"
                          + "OR world_airports.id LIKE \"%" + match + "%\" \n"
                          + "OR world_airports.name LIKE \"%" + match + "%\") \n"
                          + filterQuery
                          + "ORDER BY continents.name, country.name, region.name, "
                          + "world_airports.municipality, world_airports.name ASC";
        testDriver = new Driver();
        testDriver.setCount(match, filterQuery);
        assertEquals(expected, testDriver.count);
    }
    
    @Test
    public void testSetLimit(){
        testDriver = new Driver();
        testDriver.setLimit(3);
        
        assertEquals(testDriver.limitQuery, "limit 3");
    }
    
}