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
    public static ArrayList<Place> places;

    /**
     * The find method is meant to get access to the database and execute queries.
     *
     */
    public static void find(String match){
        count = "select count(*) from airports";
        search =  "select id,name,municipality,latitude,longitude from airports where name like '%"
                    + match + "%'or municipality like '%" + match + "%'order by name limit 20";
        try {
            Class.forName(myDriver);
            // connect to the database and query
            try (Connection conn = DriverManager.getConnection(myUrl, user, pass);
                 Statement stCount = conn.createStatement();
                 Statement stQuery = conn.createStatement();
                 ResultSet rsCount = stCount.executeQuery(count);
                 ResultSet rsQuery = stQuery.executeQuery(search)
            ) {
                printJson(rsCount, rsQuery, match);
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    /**
     * This function is meant to print the JSON on the terminal/ console to log.
     *
     */
    private static void printJson(ResultSet count, ResultSet query, String match)
            throws SQLException {
        System.out.printf("\n{\n");
        System.out.printf("\"type\": \"find\",\n");
        System.out.printf("\"title\": \"%s\",\n", match);
        System.out.printf("\"places\": [\n");
        places = new ArrayList<Place>();
        // determine the number of results that match the query
        count.next();
        int results = count.getInt(1);
        // iterate through query results and print out the airport codes
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
    }
}
