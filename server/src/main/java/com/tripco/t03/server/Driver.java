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
    public static void find(String match, int limit){
        if(limit == 0)
            limitQuery = "";
        else
            limitQuery = "limit " + Integer.toString(limit);
        search = "select id,name,municipality,latitude,longitude from airports " +
                 "where name like '%" + match + "%' " +
                 "or municipality like '%" + match + "%' " +
                 "or id like '%" + match + "%' " +
                 "order by name " + limitQuery;
        count = "select count(*) from airports where name like '%"
                + match + "%'or municipality like '%" + match + "%'order by name";

        try {
            Class.forName(myDriver);
            // connect to the database and query
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
     *
     */
    private static void printJson(ResultSet count, ResultSet query, String match, int limit)
            throws SQLException {
        System.out.printf("\n{\n");
        System.out.printf("\"type\": \"find\",\n");
        System.out.printf("\"title\": \"%s\",\n", match);
        System.out.printf("\"places\": [\n");
        places = new ArrayList<Place>();
        // determine the number of results that match the query
        count.next();
        int results = count.getInt(1);
        System.out.printf("%d results found.\n", results);
        if(limit == 0)
            System.out.println("The number of results displayed is limited to 10.\n");
        else
            System.out.printf("The number of results displayed is limited to %d.\n", limit);
        // iterate through query results and print out the airport codes
        while (query.next()) {
            final Place place = new Place(query.getString("id"),
                                    query.getString("name"),
                                    query.getString("municipality"),
                                    Double.parseDouble(query.getString("latitude")),
                                    Double.parseDouble(query.getString("longitude")));
            System.out.printf(" {\"id\":\"%s\", ", query.getString("id"));
            System.out.printf("\"name\":\"%s\", ", query.getString("name"));
            System.out.printf("\"municipality\":\"%s\", ", query.getString("municipality"));
            System.out.printf("\"latitude\":\"%s\", ", query.getString("latitude"));
            System.out.printf("\"longitude\":\"%s\"}", query.getString("longitude"));
            if (--results == 0)
                {System.out.printf("\n");}
            else
                {System.out.printf(",\n");}
            places.add(place);
//            for(Place sth: places){
//                System.out.println("driver: " + sth.userDefined);
//            }
        }
        System.out.printf(" ]\n}\n");
    }
}
