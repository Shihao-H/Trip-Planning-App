package com.tripco.t03.server;

import com.google.gson.Gson;
import com.tripco.t03.planner.Filter;

import java.util.ArrayList;
import java.util.Arrays;

public class Config {
    private String type = "config";
    private short version = 5;
    private ArrayList<String> units;
    private ArrayList<Optimization> optimization;
    private ArrayList<String> attributes;
    private ArrayList<Filter> filters;
    private ArrayList<String> maps;

    /**
     * Constructor.
     */
    public Config() {
        units = new ArrayList<>(Arrays.asList("miles", "kilometers", "nautical miles", 
                                              "user defined"));
        optimization = new ArrayList<>(
                Arrays.asList(new Optimization("none", "The trip is not optimized."),
                        new Optimization("short", "Nearest neighbor."),
                        new Optimization("shorter", "2-opt."),
                        new Optimization("shortest", "3-opt.")));
        attributes = new ArrayList<>(Arrays.asList("name", "id", "latitude", "longitude", "type",
                "elevation", "continent", "country", "region", "municipality"));
        String[] stringContinents = {"Africa", "Antarctica", "Asia", "Europe", "North America",
                "Oceania", "South America"};
        Filter continents = new Filter("continents", stringContinents);
        String[] stringType = {"heliport", "small_airport", "seaplane_base", "closed", 
                               "balloonport", "medium_airport", "large_airport"};
        Filter type = new Filter("type", stringType);
        filters = new ArrayList<>(Arrays.asList(continents, type));
        maps = new ArrayList<>(Arrays.asList("svg", "kml"));
    }

    /**
     * Gets the config object.
     *
     * @return String.
     */
    static String getConfig() {
        Config conf = new Config();
        Gson gson = new Gson();
        return gson.toJson(conf);
    }
}
