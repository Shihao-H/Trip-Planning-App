package com.tripco.t03.server;

import com.tripco.t03.planner.Place;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestDriver {
    private String match;
    private int limit;
    private String filterQuery;
    private String limitQuery;
    private ArrayList<Place> expected;
    private String filter;
    
    @Before
    public void setup(){
        match = "alf";
        filterQuery = "AND continents.name IN (\"North America\")\n"
                + "AND type IN (\"heliport\")\n";
        
        limit = 20;
        expected = new ArrayList<>();
        expected.add(new Place("0FL4", "Jackson Heliport", 30.705, -85.378304));
        expected.add(new Place("98TX", "Cig 402 Heliport", 27.256701, -98.0942));
        expected.add(new Place("US-VA38", "Lz Alfa Heliport", 36.793201, -75.964699));
        match = "death";
        limit = 1;
        filter = "closed";
    }

    @Test
    public void testFind(){
        limitQuery = "";     //no limit
        Driver.find(match, limit, filter);
        ArrayList<Place> placeList = Driver.places;
        
        Assert.assertNotNull(placeList);
    }

    @Test
    public void testFind1(){
        limitQuery = "limit 20";
        int found = 3;
        Driver.find(match, limit, filterQuery);
        assertEquals(expected.size(), Driver.places.size());
        assertEquals(found, Driver.found);
        assertEquals(limitQuery, Driver.limitQuery);
        if(Driver.isTravis != null
           && Driver.isTravis.equals("true")) {
            assertEquals("jdbc:mysql://127.0.0.1/cs314", Driver.dburl);
            assertEquals("travis", Driver.username);
        } else if(Driver.isDevelopment != null
                  && Driver.isDevelopment.equals("development")) {
            assertEquals("jdbc:mysql://127.0.0.1:some-port/cs314", Driver.dburl);
            assertEquals("cs314-db", Driver.username);
        } else {
            assertEquals("jdbc:mysql://faure.cs.colostate.edu/cs314", Driver.dburl);
            assertEquals("cs314-db", Driver.username);
        }
    }

    @Test
    public void testFindWithZeroLimit(){
        limit = 0;
        limitQuery = "";
        int found = 3;
        Driver.find(match, limit, filterQuery);
        assertEquals(expected.size(), Driver.places.size()) ;
        assertEquals(found, Driver.found);
        assertEquals(limitQuery, Driver.limitQuery);
    }

    @Test
    public void testFindException(){
        limitQuery = "limit 20";
        filterQuery = "AND continents IN (\"North America\")\n"
                + "AND type IN (\"heliport\")\n";
        Driver.find(match, limit, filterQuery);
    }

    @Test
    public void testSetSearch(){
        limitQuery = "";     //no limit
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
                + " ORDER BY continents.name, country.name, region.name, "
                + "world_airports.municipality, world_airports.name ASC "
                + limitQuery;
        Driver.setSearch(match, filterQuery);
        assertEquals(expected, Driver.search);
    }

    @Test
    public void testSetCount(){
        limitQuery = "";     //no limit
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
        Driver.setCount(match, filterQuery);
        assertEquals(expected, Driver.count);
    }
}