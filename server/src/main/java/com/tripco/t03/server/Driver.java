package com.tripco.t03.server;

import com.tripco.t03.planner.Place;

import java.sql.*;
import java.util.ArrayList;

public class Driver {
    // db configuration information
    public static String isTravis = System.getenv("TRAVIS");
    public static String isDevelopment = System.getenv("CS314_ENV");
    public static String dburl;
    public static String username;
    public static String password;
    private static final String myDriver = "com.mysql.jdbc.Driver";
    // fill in SQL queries to count the number of records and to retrieve the data
    public static String count = "";
    public static String search = "";
    public static String limitQuery = "";
    public static ArrayList<Place> places;
    public static int found = 0;

    /**
     * The find method is meant to get access to the database and execute queries.
     * @param match String phrase to match.
     * @param limit integer number of mx results to be shown.
     */
    public static void find(String match, int limit, String filter) {
        if(isTravis != null && isTravis.equals("true")) {
            dburl = "jdbc:mysql://127.0.0.1/cs314";
            username = "travis";
            password = null;
        } else if(isDevelopment != null && isDevelopment.equals("development")) {
            dburl = "jdbc:mysql://127.0.0.1:some-port/cs314";
            username = "cs314-db";
            password = "eiK5liet1uej";
        } else {
            dburl = "jdbc:mysql://faure.cs.colostate.edu/cs314";
            username = "cs314-db";
            password =  "eiK5liet1uej";
        }

        if (limit == 0) {
            limitQuery = ""; // no limit
        } else {
            limitQuery = "limit " + Integer.toString(limit);
        }
        setSearch(match, filter);
        setCount(match, filter);
        try {
            Class.forName(myDriver);
            try (Connection conn = DriverManager.getConnection(dburl, username, password);
                 Statement stCount = conn.createStatement();
                 Statement stQuery = conn.createStatement();
                 ResultSet rsCount = stCount.executeQuery(count);
                 ResultSet rsQuery = stQuery.executeQuery(search)) {
                 rsCount.next();
                 found = rsCount.getInt(1);
                 places = new ArrayList<>();
                 while (rsQuery.next()) {
                    final Place place = new Place(
                            rsQuery.getString("id"),
                            rsQuery.getString(1),//name
                            Double.parseDouble(rsQuery.getString("latitude")),
                            Double.parseDouble(rsQuery.getString("longitude")));
                    place.setAttributeType(rsQuery.getString("type"));
                    place.setAttributeElevation(rsQuery.getString("elevation"));
                    place.setAttributeContinent(rsQuery.getString(5));//continent
                    place.setAttributeCountry(rsQuery.getString(4));//country
                    place.setAttributeRegion(rsQuery.getString(3));//region
                    place.setAttributeMunicipality(rsQuery.getString("municipality"));
                    places.add(place);
                }
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    /**
     * Setter for search.
     * @param match String.
     * @param filter String.
     */
    public static void setSearch(String match, String filter) {
        search = "SELECT world_airports.name, world_airports.municipality, region.name, "
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
                + filter
                + "ORDER BY continents.name, country.name, region.name, "
                + "world_airports.municipality, world_airports.name ASC "
                + limitQuery;
    }

    /**
     * Setter: Sets count variable.
     * @param match String.
     * @param filter String.
     */
    public static void setCount(String match, String filter) {
        count = "SELECT count(*) "
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
                + filter
                + "ORDER BY continents.name, country.name, region.name, "
                + "world_airports.municipality, world_airports.name ASC";
    }
}
