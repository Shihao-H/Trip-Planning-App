package com.tripco.t03.planner;

import com.tripco.t03.server.Driver;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The Search class supports TFFI so it can easily be converted to/from Json by Gson.
 */
public class Search {

    public int version = 4;
    public String type = "search";
    public String match = null;
    public Filter[] filters = null;
    public int limit = 0;
    public int found = 0;
    public ArrayList<Place> places;

    /**
     * This is a default constructor.
     */
    public Search(){}
    
    /**
     * This is a constructor.
     * @param match String compare match with places' names in the database.
     */
    public Search(String match) {
        this.match = match;
    }

    /**
     * Constructor.
     * @param match String.
     * @param limit int.
     */
    public Search(String match, int limit)
    {
        this.match = match;
        this.limit = limit;
    }

    /**
     * Constructor.
     * @param match String.
     * @param filters Array of Filter objects.
     */
    public Search(String match, Filter[] filters)
    {
        this.match = match;
        this.filters = new Filter[filters.length];
        System.arraycopy(filters, 0, this.filters, 0, filters.length);
    }

    /**
     * Constructor.
     * @param match String.
     * @param limit int.
     * @param filters Array of Filter Objects.
     */
    public Search(String match, int limit, Filter[] filters)
    {
        this.match = match;
        this.limit = limit;
        this.filters = new Filter[filters.length];
        System.arraycopy(filters, 0, this.filters, 0, filters.length);
    }

    /**
     * The top level method that does searching.
     */
    public String getQuery() {
        StringBuilder query = new StringBuilder();
        int index = 0;
        while (hasFilter(index)) {
            if(this.filters[index].values.length != 0){
                query.append(buildQuery(this.filters[index]));
            }
            index+=1;
        }
        return query.toString();
    }
    
    /**
     * Helper method.
     * @param index int.
     * @return true if there is another filter.
     */
    private boolean hasFilter(int index){
         return ((this.filters != null)
                 && (this.filters.length != 0)
                 && (index != this.filters.length));
    }
    
    /**
     * Helper method to build query.
     * @param filter Filter Object.
     * @return String.
     */
    private String buildQuery(Filter filter){
        StringBuilder query = new StringBuilder();
        int valueSize = filter.values.length;
        query.append("AND ");
        if (filter.name.equalsIgnoreCase("continents")) {
            query.append(filter.name).append(".name IN (");
        } else {
            query.append(filter.name).append(" IN (");
        }
        for (int j = 0; j < valueSize - 1; j++) {
            query.append("\"").append(filter.values[j]).append("\", ");
        }
        query.append("\"").append(filter.values[valueSize - 1]).append("\")\n");
        return query.toString();
    }
    
    /**
     * Helper method that accesses database.
     * @throws SQLException DB Errors.
     * @throws ClassNotFoundException find failure.
     */
    void findMatch() throws SQLException, ClassNotFoundException {
        
        Driver driver = new Driver();
        driver.find(this.match, this.limit, getQuery());
        this.found = driver.found;
        this.places = (ArrayList<Place>) driver.places.clone();
    }
}
