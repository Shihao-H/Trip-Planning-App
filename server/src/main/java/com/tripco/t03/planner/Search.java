package com.tripco.t03.planner;

import com.tripco.t03.server.Driver;
import java.util.ArrayList;

/**
 * The Search class supports TFFI so it can easily be converted to/from Json by Gson.
 */
public class Search {

    public String type = "search";
    public int version = 3;
    public String match = "";
    public int limit = 0;
    public ArrayList<Place> places;

    /**
     * This is a default constructor.
     */
    public Search(){
        this.match = null;
    }
    
    /**
     * This is a constructor.
     * @param match String compare match with places' names in the database.
     */
    public Search(String match)
    {
        this.match = match;
    }

    public Search(String match, int limit)
    {
        this.match = match;
        this.limit = limit;
    }

    /**
     * The top level method that does searching.
     */
    public void match(){
        Driver driver = new Driver();
        driver.find(this.match, this.limit);
        this.places = (ArrayList<Place>) driver.places.clone();
    }


}
