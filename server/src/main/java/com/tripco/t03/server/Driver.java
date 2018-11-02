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
    public static int found = 0;

    /**
     * The find method is meant to get access to the database and execute queries.
     * @param match String phrase to match.
     * @param limit integer number of mx results to be shown.
     */
    public static void find(String match, int limit){
        if(limit == 0)
            limitQuery = ""; // no limit
        else
            limitQuery = "limit " + Integer.toString(limit);

        search = "SELECT world_airports.name, world_airports.municipality, region.name, "
                + "country.name, continents.name, "
                + "world_airports.id, world_airports.type, world_airports.longitude, "
                + "world_airports.latitude, "
                + "world_airports.elevation "
                + "FROM continents \n"
                + "INNER JOIN country ON continents.id = country.continent \n"
                + "INNER JOIN region ON country.id = region.iso_country \n"
                + "INNER JOIN world_airports ON region.id = world_airports.iso_region \n"
                + "WHERE continents.name LIKE \"%" + match + "%\"  \n"
                + "OR country.name LIKE \"%" + match + "%\"  \n"
                + "OR region.name LIKE \"%" + match + "%\"  \n"
                + "OR world_airports.municipality LIKE \"%" + match + "%\" \n"
                + "OR world_airports.name LIKE \"%" + match + "%\" \n"
                + "ORDER BY continents.name, country.name, region.name, world_airports.municipality, "
                + "world_airports.name ASC "
                + limitQuery;

        count = "SELECT count(*) "
                + "FROM continents \n"
                + "INNER JOIN country ON continents.id = country.continent \n"
                + "INNER JOIN region ON country.id = region.iso_country \n"
                + "INNER JOIN world_airports ON region.id = world_airports.iso_region \n"
                + "WHERE continents.name LIKE \"%" + match + "%\"  \n"
                + "OR country.name LIKE \"%" + match + "%\"  \n"
                + "OR region.name LIKE \"%" + match + "%\"  \n"
                + "OR world_airports.municipality LIKE \"%" + match + "%\" \n"
                + "OR world_airports.name LIKE \"%" + match + "%\" \n"
                + "ORDER BY continents.name, country.name, region.name, world_airports.municipality, "
                + "world_airports.name ASC";

        try {
            Class.forName(myDriver);
            try (Connection conn = DriverManager.getConnection(myUrl, user, pass);
                 Statement stCount = conn.createStatement();
                 Statement stQuery = conn.createStatement();
                 ResultSet rsCount = stCount.executeQuery(count);
                 ResultSet rsQuery = stQuery.executeQuery(search)
            ) {
                printJson(rsCount, rsQuery, match, limit);
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    /**
     * This function is meant to print the JSON on the terminal/ console to log.
     * @param count
     * @param query
     * @param match
     * @param limit
     */
    private static void printJson(ResultSet count, ResultSet query, String match, int limit) 
        throws SQLException {

        System.out.print("\n{\n");
        System.out.print("\"version\": 4,\n");
        System.out.print("\"type\": \"search\",\n");
        System.out.printf("\"match\": \"%s\",\n", match);
        System.out.print("\"places\": [\n");
        places = new ArrayList<Place>();

        count.next();
        int result  = count.getInt(1);
        found = result;
        System.out.printf("%d results found.\n", found);

        if(limit == 0)
            System.out.print("No limit.\n");
        else
            System.out.printf("The limit is %d.\n", limit);

        while (query.next()) {
            final Place place = new Place(
                                    query.getString("id"),
                                    query.getString(1),//name
                                    Double.parseDouble(query.getString("latitude")),
                                    Double.parseDouble(query.getString("longitude")));


            place.setAttributeType(query.getString("type"));
            place.setAttributeElevation(query.getString("elevation"));
            place.setAttributeContinent(query.getString(5));//continent
            place.setAttributeCounty(query.getString(4));//country
            place.setAttributeRegion(query.getString(3));//region
            place.setAttributeMunicipality(query.getString("municipality"));


            System.out.printf(" {\"id\":\"%s\", ", query.getString("id"));
            System.out.printf("\"name\":\"%s\", ", query.getString(1));
            System.out.printf("\"latitude\":\"%s\", ", query.getString("latitude"));
            System.out.printf("\"longitude\":\"%s\", ", query.getString("longitude"));

            System.out.printf("\"type\":\"%s\", ", query.getString("type"));
            System.out.printf("\"elevation\":\"%s\", ", query.getString("elevation"));
            System.out.printf("\"continent\":\"%s\", ", query.getString(5));
            System.out.printf("\"country\":\"%s\", ", query.getString(4));
            System.out.printf("\"region\":\"%s\", ", query.getString(3));
            System.out.printf("\"municipality\":\"%s\"}", query.getString("municipality"));

            if (--result == 0)
                {System.out.print("\n");}
            else
                {System.out.print(",\n");}
            places.add(place);
        }

        System.out.print(" ]\n}\n");
    }
}
