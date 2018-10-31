package com.tripco.t03.server;

import com.tripco.t03.planner.Place;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Driver {
    // db configuration information
    private static final String myDriver = "com.mysql.jdbc.Driver";
    private static final  String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314";
    private static final String user = "cs314-db";
    private static final String pass = "eiK5liet1uej";
    // fill in SQL queries to count the number of records and to retrieve the data
    private static String count = "";
    private static String search = "";
    private static String limitQuery = "";
    public static ArrayList<Place> places;

    /**
     * The find method is meant to get access to the database and execute queries.
     *
     */
    public static int find(String match, int limit){
        int found = 0;

        if(limit == 0)
            limitQuery = ""; // no limit
        else
            limitQuery = "limit " + Integer.toString(limit);

        search = "SELECT world_airports.name, world_airports.municipality, region.name, country.name, continents.name, " +
                "world_airports.id, world_airports.type, world_airports.longtitude, world_airports.latitude, " +
                "world_airports.elevation " +
                "FROM continents \n" +
                "INNER JOIN country ON continents.id = country.continent \n" +
                "INNER JOIN region ON country.id = region.iso_country \n" +
                "INNER JOIN world_airports ON region.id = world_airports.iso_region \n" +
                "WHERE continents.name LIKE \"%" + match + "%\"  \n" +
                "OR country.name LIKE \"%" + match + "%\"  \n" +
                "OR region.name LIKE \"%" + match + "%\"  \n" +
                "OR world_airports.municipality LIKE \"%" + match + "%\" \n" +
                "OR world_airports.name LIKE \"%" + match + "%\" \n" +
                "ORDER BY continents.name, country.name, region.name, world_airports.municipality, world_airports.name ASC \n" +
                limitQuery;

        count = "SELECT count(*) " +
                "FROM continents \n" +
                "INNER JOIN country ON continents.id = country.continent \n" +
                "INNER JOIN region ON country.id = region.iso_country \n" +
                "INNER JOIN world_airports ON region.id = world_airports.iso_region \n" +
                "WHERE continents.name LIKE \"%" + match + "%\"  \n" +
                "OR country.name LIKE \"%" + match + "%\"  \n" +
                "OR region.name LIKE \"%" + match + "%\"  \n" +
                "OR world_airports.municipality LIKE \"%" + match + "%\" \n" +
                "OR world_airports.name LIKE \"%" + match + "%\" \n" +
                "ORDER BY continents.name, country.name, region.name, world_airports.municipality, world_airports.name ASC";

        try {
            Class.forName(myDriver);
            try (Connection conn = DriverManager.getConnection(myUrl, user, pass);
                 Statement stCount = conn.createStatement();
                 Statement stQuery = conn.createStatement();
                 ResultSet rsCount = stCount.executeQuery(count);
                 ResultSet rsQuery = stQuery.executeQuery(search)
            ) {
                found = printJson(rsCount, rsQuery, match, limit);
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return found;
    }

    /**
     * This function is meant to print the JSON on the terminal/ console to log.
     *
     */
    private static int printJson(ResultSet count, ResultSet query, String match, int limit) throws SQLException {

        System.out.printf("\n{\n");
        System.out.printf("\"type\": \"find\",\n");
        System.out.printf("\"title\": \"%s\",\n", match);
        System.out.printf("\"places\": [\n");
        places = new ArrayList<Place>();

        count.next();
        int results = count.getInt(1);
        System.out.printf("%d results found.\n", results);

        if(limit == 0)
            System.out.println("The number of results displayed is not limited.\n");
        else
            System.out.printf("The number of results displayed is limited to %d.\n", limit);

        while (query.next()) {
            final Place place = new Place(query.getString("id"),
                                    query.getString("name"),
                                    Double.parseDouble(query.getString("latitude")),
                                    Double.parseDouble(query.getString("longitude")));
            System.out.printf(" {\"id\":\"%s\", ", query.getString("id"));
            System.out.printf("\"name\":\"%s\", ", query.getString("name"));
            System.out.printf("\"latitude\":\"%s\", ", query.getString("latitude"));
            System.out.printf("\"longitude\":\"%s\"}", query.getString("longitude"));

            if (--results == 0)
                {System.out.printf("\n");}
            else
                {System.out.printf(",\n");}
            places.add(place);
        }

        System.out.printf(" ]\n}\n");
        return results;
    }
}
