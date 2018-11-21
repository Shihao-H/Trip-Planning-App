package com.tripco.t03.server;

import com.tripco.t03.planner.Place;

import java.sql.*;
import java.util.ArrayList;

public class Driver {
    // db configuration information
    String isTravis;
    String isDevelopment;
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
    public Driver() {
        limitQuery = "";
        search = "";
        count = "";
        found = 0;
        isTravis = System.getenv("TRAVIS");
        isDevelopment = System.getenv("CS314_ENV");
    }
    
    /**
     * The find method is meant to get access to the database and execute queries.
     * @param match String phrase to match.
     * @param limit integer number of mx results to be shown.
     */
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
        Connection connCount = getConnection();
        Connection connQuery = getConnection();
        ResultSet rsCount = runQuery(connCount, count);
        ResultSet rsQuery = runQuery(connQuery, search);
        rsCount.next();
        found = rsCount.getInt(1);
        parseQuery(rsQuery);
    }
    
    /**
     * Helper method to parse query results.
     * @param query ResultSet.
     * @throws SQLException upon failure.
     */
    private void parseQuery(ResultSet query) throws SQLException {
        places = new ArrayList<>();
        if (query.next()) {
            final Place place = new Place(
                    query.getString("id"),
                    query.getString(1),//name
                    Double.parseDouble(query.getString("latitude")),
                    Double.parseDouble(query.getString("longitude")));
            place.setAttributeType(query.getString("type"));
            place.setAttributeElevation(query.getString("elevation"));
            place.setAttributeContinent(query.getString(5));//continent
            place.setAttributeCountry(query.getString(4));//country
            place.setAttributeRegion(query.getString(3));//region
            place.setAttributeMunicipality(query.getString("municipality"));
            places.add(place);
            parseQuery(query);
        }
    }
    
    /**
     * Helper method to run query.
     * @param conn Connection object.
     * @param query String.
     * @return ResultSet.
     * @throws SQLException upon failure.
     */
    private ResultSet runQuery(Connection conn, String query) throws SQLException {
        Statement statement = conn.createStatement();
        return statement.executeQuery(query);
    }
    
    /**
     * Helper method to get connection.
     * @return Connection object.
     * @throws SQLException upon failure.
     * @throws ClassNotFoundException upon failure.
     */
    Connection getConnection() throws SQLException, ClassNotFoundException {
        String myDriver = "com.mysql.jdbc.Driver";
        Class.forName(myDriver);
        return DriverManager.getConnection(dburl, username, password);
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
