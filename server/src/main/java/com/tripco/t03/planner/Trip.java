package com.tripco.t03.planner;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Trip class supports TFFI so it can easily be converted to/from Json by Gson.
 *
 */

public class Trip {

    // The variables in this class should reflect TFFI.
    public int version = 4;
    public String type = "trip";
    public String title;
    public Option options;
    public ArrayList<Place> places;
    public ArrayList<Integer> distances;
    public String map = svg();
    /**
     * Default constructor.
     */

    public Trip(){
        this.title = null;
        this.options = new Option();
        this.places = null;
        this.distances = null;
        this.map = svg();
    }

    /**
     * @param options Option Object.
     * @param places ArrayList of Place.
     * Constructor without title.
     */
    public Trip(Option options, ArrayList<Place> places){
        this.title = null;
        this.options = options;
        this.places = places;
        this.map = svg();
    }

    /**
     * @param title Title of the trip.
     * @param options The options that the trip will have
     * @param places The list of places in the trip.
     * @param dist The list of distances between each place
     *
     */
    public Trip(String title, Option options, ArrayList<Place> places, ArrayList<Integer> dist){
        this.title =title;
        this.options=options;
        this.places=places;
        this.distances=dist;
        this.map = svg();
    }

    /**
     * @param title String user defined title for trip.
     * @param options Option Object.
     * @param places ArrayList of Place objects.
     * Constructor with title.
     */

    public Trip(String title, Option options, ArrayList<Place> places){
        this.title=title;
        this.options = options;
        this.places = places;
        this.map = svg();
    }

        /** The top level method that does planning.
     * At this point it just adds the map and distances for the places in order.
     * It might need to reorder the places in the future.
     */
    public void plan() {
        if(this.options.optimization.equalsIgnoreCase("none")){
            this.distances = legDistances();
        } else{
            Optimize opt = new Optimize(this);
            Trip optTrip = opt.getOptimalTrip();
            this.title = optTrip.title;
            this.options = optTrip.options;
            this.places = optTrip.places;
            this.distances=optTrip.distances;
            this.map = optTrip.map;
        }
//        LineDistance worldMap = new LineDistance(this.places);
//        this.map = worldMap.getBackground();
    }

    /**
     * Setter for places ArrayList.
     * @param newPlaces Array of Places.
     */
    public void setPlace(Place[] newPlaces) {
        this.places.clear();
        this.places.addAll(Arrays.asList(newPlaces));
    }

    /**
     * Setter for distances ArrayList.
     * @param distances ArrayList of Integers.
     */
    public void setDistances(ArrayList<Integer> distances){
        this.distances = distances;
    }


    /**
     * Creates an SVG containing the background and the legs of the trip.
     */
    public String svg() {
        LineDistance worldMap = new LineDistance(this.places);
        return worldMap.getBackground();
    }

    /**
     * Returns the distances between consecutive places,
     * including the return to the starting point to make a round trip.
     * @return An arrayList of type Integer
     */
    private ArrayList<Integer> legDistances() {

        ArrayList<Integer> dist = new ArrayList<>();

        if(this.options.units.equals("user defined")){
            dist = makeUserDefTrip();
        } else {
            dist = makeNormalTrip();
        }
        return dist;
    }

    private ArrayList<Integer> makeUserDefTrip() {

        ArrayList<Integer> dist = new ArrayList<>();

        for (int count = 0; count < places.size() - 1; count++) {
            Distance leg = new Distance(places.get(count), places.get(count + 1),
                    options.units, options.unitName, options.unitRadius);
            leg.setDistance();
            dist.add(leg.distance);
        }
        Distance leg = new Distance(places.get(places.size() - 1), places.get(0),
                options.units, options.unitName, options.unitRadius);
        leg.setDistance();
        dist.add(leg.distance);

        return dist;
    }

    private ArrayList<Integer> makeNormalTrip() {

        ArrayList<Integer> dist = new ArrayList<>();

        for (int count = 0; count < places.size() - 1; count++) {
            Distance leg = new Distance(places.get(count), places.get(count+1), options.units);
            leg.setDistance();
            dist.add(leg.distance);
        }
        Distance leg = new Distance(places.get(places.size()-1), places.get(0), options.units);
        leg.setDistance();
        dist.add(leg.distance);

        return dist;
    }
}