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
        this.filters = filters;
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
        this.filters = filters;
    }

    /**
     * The top level method that does searching.
     */
    public void match() {
        String query = "";
        if ((this.filters != null) && (this.filters.length != 0)) {
            for (int i = 0; i < this.filters.length; i++) {
                if (this.filters[i].values.length != 0) {
                    query += "AND (";
                    for (int j = 0; j < this.filters[i].values.length; j++) {
                        if (this.filters[i].name.equalsIgnoreCase("continents")) {
                            query += this.filters[i].name +
                                    ".name = \'" + this.filters[i].values[j] + "\' ";
                            if (j == this.filters[i].values.length - 1) {
                                query += ")\n";
                            } else {
                                query += "OR\n";
                            }
                        }
                        if (this.filters[i].name.equalsIgnoreCase("type")) {
                            query += this.filters[i].name + " = \'" +
                                    this.filters[i].values[j] + "\' ";
                            if (j == this.filters[i].values.length - 1) {
                                query += ")\n";
                            } else {
                                query += "OR\n";
                            }
                        }
                    }
                }
            }
        }
        System.out.println("This is the filter query:\n " + query);
        Driver driver = new Driver();
        driver.find(this.match, this.limit, query);
        this.found = driver.found;
        this.places = (ArrayList<Place>) driver.places.clone();
    }
}