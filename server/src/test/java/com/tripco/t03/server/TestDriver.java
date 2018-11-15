package com.tripco.t03.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;

public class TestDriver {
    private Driver driver;
    private Driver testDriver;
    private String match;
    private int limit;
    private String filterQuery;
    private String limitQuery;

    @Before
    public void setup(){
        match = "alf";
        limit = 20;
        filterQuery = "AND continents.name IN (\"Asia\", \"North America\")\n"
                + "AND type IN (\"heliport\", \"large_airport\")\n";
        limitQuery = "limit 20";
        Driver driver = new Driver();
        driver.find(match, limit, filterQuery);
    }

    @Test
    public void testFind(){
        ResultSet
        testDriver = new Driver();
        driver.setSearch(match, filterQuery);
        assertEquals(testDriver.search, expected);

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
        driver.setSearch(match, filterQuery);
        assertEquals(testDriver.search, expected);
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
        driver.setCount(match, filterQuery);
        assertEquals(testDriver.count, expected);
    }

    @Test
    public void testAddPlaces(){

    }



}
