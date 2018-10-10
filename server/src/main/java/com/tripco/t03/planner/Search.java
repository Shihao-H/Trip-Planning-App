package com.tripco.t03.planner;

import com.tripco.t03.server.Driver;
import java.util.ArrayList;

/**
 * The Search class supports TFFI so it can easily be converted to/from Json by Gson.
 *
 */
public class Search {

    public String type = "search";
    public int version = 3;
    public String match = "";
    public int limit = 0;
    public ArrayList<Place> places;

    public Search(){
        this.match = null;
    }

    /**
     * @param match String compare match with places' names in the database.
     *
     */
    public Search(String match){
        this.match = match;
    }

    /**
     * The top level method that does searching.
     */
    public void match(){
        Driver driver = new Driver();
        driver.find(this.match);
        this.places = (ArrayList<Place>) driver.places.clone();
    }


}