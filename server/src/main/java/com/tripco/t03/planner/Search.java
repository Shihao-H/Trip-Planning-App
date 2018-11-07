package com.tripco.t03.planner;

import com.tripco.t03.server.Driver;
import java.util.ArrayList;

/**
 * The Search class supports TFFI so it can easily be converted to/from Json by Gson.
 */
public class Search {

    public int version = 4;
    public String type = "search";
    public String match = null;
    public Filter[] filters;
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
    public void match() {
        String query = "";
        if ((this.filters != null) && (this.filters.length != 0)) {
            query = getQuery();
        }
        
        Driver.find(this.match, this.limit, query);
        this.found = Driver.found;
        this.places.addAll(Driver.places);
    }
    
    /**
     * Parses Filter objects into strings for query.
     * @return Sting.
     */
    public String getQuery(){
        String query = "";
        for (int i = 0; i < this.filters.length; i++) {
            query += "AND (";
            for (int j = 0; j < this.filters[i].values.length; j++) {
                query += addFilters(i, j);
            }
        }
        return query;
    }
    
    /**
     * Helper method for getting the Filter elements in sting.
     * @param filter int.
     * @param value int.
     * @return String.
     */
    private String addFilters(int filter, int value){
        String substring = "";
        if (this.filters[filter].name.equalsIgnoreCase("continents")) {
            substring += this.filters[filter].name + ".name = \'";
        }
        if (this.filters[filter].name.equalsIgnoreCase("type")){
         substring+=this.filters[filter].name+" = \'";
        }
         substring += this.filters[filter].values[value] + "\' ";
        if (value == this.filters[filter].values.length - 1) {
            substring += ")\n";
        } else {
            substring += "OR\n";
        }
        
        return substring;
    }
}