package com.tripco.t03.planner;
import java.util.ArrayList;

import com.tripco.t03.server.Driver;

public class Search {

    public String type = "search";
    public int version = 3;
    public String match = "";
    public int limit = 0;
    public ArrayList<Place> places;

    public Search(){
        this.match = null;
    }

    public Search(String match){
        this.match = match;
    }

    public void match(){
        //this.places = new ArrayList<Place>(Arrays.asList(new Place("dnvr", "Denver", 39.7392, -104.9903),new Place("bldr", "Boulder", 40.01499, -105.27055)));
        Driver driver = new Driver();
        driver.find(this.match);
    }


}