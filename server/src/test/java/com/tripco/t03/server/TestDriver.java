package com.tripco.t03.server;

import com.tripco.t03.planner.Place;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

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
    public void testFind(){
        testDriver = new Driver();
        int found = 3;
        testDriver.find(match, limit, filterQuery);
        assertEquals(testDriver.places.size(), expected.size());
        assertEquals(testDriver.found, found);
        assertEquals(testDriver.limitQuery, limitQuery);
    }

    @Test
    public void testFindWithZeroLimit(){
        limit = 0;
        limitQuery = "";
        testDriver = new Driver();
        int found = 3;
        testDriver.find(match, limit, filterQuery);
        assertEquals(testDriver.places.size(), expected.size());
        assertEquals(testDriver.found, found);
        assertEquals(testDriver.limitQuery, limitQuery);
    }

    @Rule
    public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();

    @Test
    public void testFindException(){
        testDriver = new Driver();
        boolean thrown = false;
        filterQuery = "AND continents IN (\"North America\")\n"
                + "AND type IN (\"heliport\")\n";
        System.err.println("Exception: Unknown column 'continents' in 'where clause'");

        testDriver.find(match, limit, filterQuery);
        assertEquals("Exception: Unknown column 'continents' in 'where clause'", systemErrRule.getLog());

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
        testDriver.setSearch(match, filterQuery);
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
        testDriver.setCount(match, filterQuery);
        assertEquals(testDriver.count, expected);
    }
}
