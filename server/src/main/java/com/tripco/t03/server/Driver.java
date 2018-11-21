package com.tripco.t03.server;

import com.tripco.t03.planner.Place;

import java.sql.*;
import java.util.ArrayList;

public class Driver {
    // db configuration information
    String isTravis = System.getenv("TRAVIS");
    String isDevelopment = System.getenv("CS314_ENV");
    String dburl;
    String username;
    public String password;
    // fill in SQL queries to count the number of records and to retrieve the data
    public String count;
    public String search;
    String limitQuery;
    public ArrayList<Place> places;
    public int found;
    
    /**
     * Constructor.
     */
    public Driver() {count = "";
        search = "";
        limitQuery = "";
        found = 0;
    }
    
    /**
     * The find method is meant to get access to the database and execute queries.
     * @param match String phrase to match.
     * @param limit integer number of mx results to be shown.
     */
    //Method `find` has a Cognitive Complexity of 7 (exceeds 5 allowed). Consider
    // refactoring.  …
    //Method `find` has 44 lines of code (exceeds 25 allowed). Consider refactoring.
    public void find(String match, int limit, String filter) throws SQLException,
                                                                    ClassNotFoundException {
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
        setLimit(limit);
        setSearch(match, filter);
        setCount(match, filter);
        Connection conn = getConnection();
        ResultSet rsCount = runQuery(conn ,count);
        ResultSet rsQuery = runQuery(conn, search);
        rsCount.next();
        found = rsCount.getInt(1);
        places = new ArrayList<>();
        parseResults(rsQuery);
    }
    
    /**
     * Helper method to establish connection.
     * @return Connection object.
     * @throws SQLException if connection is not made.
     * @throws ClassNotFoundException if forName fails.
     */
    Connection getConnection() throws SQLException, ClassNotFoundException {
        String myDriver = "com.mysql.jdbc.Driver";
        Class.forName(myDriver);
        return DriverManager.getConnection(dburl, username, password);
    }
    
    /**
     * Helper method to run the query.
     * @param conn Connection object.
     * @param query Query string.
     * @return ResultSet from Database query.
     * @throws SQLException when query fails.
     */
    private ResultSet runQuery(Connection conn, String query) throws SQLException {
        Statement statement = conn.createStatement();
        return statement.executeQuery(query);
    }
    
    /**
     * Helper method to parse query results.
     * @param rsQuery ResultSet.
     * @throws SQLException upon failure.
     */
    private void parseResults(ResultSet rsQuery) throws SQLException {
        if (rsQuery.next()) {
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
            parseResults(rsQuery);
        }
    }
    
    /**
     * Setter for search.
     * @param limit int.
     */
    void setLimit(int limit){
        if (limit == 0) {
            this.limitQuery = ""; // no limit
        } else {
            this.limitQuery = "limit " + Integer.toString(limit);
        }
    }
    
    /**
     * Setter for search.
     * @param match String.
     * @param filter String.
     */
    void setSearch(String match, String filter) {
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
                 + this.limitQuery;
    }
    
    /**
     * Setter: Sets count variable.
     * @param match String.
     * @param filter String.
     */
    void setCount(String match, String filter) {
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