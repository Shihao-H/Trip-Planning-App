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
    public void match() {
        String query = "";
        if ((this.filters != null) && (this.filters.length != 0)) {
            int filterSize = this.filters.length;
            for (int i = 0; i < filterSize; i++) {
                int valueSize = this.filters[i].values.length;
                if (valueSize != 0) {
                    query += "AND ";
                    if (this.filters[i].name.equalsIgnoreCase("continents")) {
                        query += this.filters[i].name + ".name IN (";
                    } else {
                        query += this.filters[i].name + " IN (";
                    }
                    for (int j = 0; j < valueSize-1; j++) {
                        query += "\"" + this.filters[i].values[j] + "\", ";
                    }
                    query += "\"" + this.filters[i].values[valueSize -1] + "\")\n";
                }
            }
        }
        System.out.println("This is the filter query:\n" + query);
        Driver driver = new Driver();
        driver.find(this.match, this.limit, query);
        this.found = driver.found;
        this.places = (ArrayList<Place>) driver.places.clone();
    }
}