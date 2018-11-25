package com.tripco.t03.planner;

import java.util.ArrayList;
import java.util.Arrays;

public class Trip {

    // The variables in this class should reflect TFFI.
    public int version = 5;
    public String type = "trip";
    public String title;
    public Option options;
    public ArrayList<Place> places;
    public ArrayList<Long> distances;
    public String map;
    
    /**
     * Default constructor.
     */
    public Trip() {
        this.title = null;
        this.options = null;
        this.places = new ArrayList<>();
        this.distances = new ArrayList<>();
        this.map = "";
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
        this.map = "";
        this.distances = new ArrayList<>();
    }

    /**
     * @param title Title of the trip.
     * @param options The options that the trip will have
     * @param places The list of places in the trip.
     * @param dist The list of distances between each place
     *
     */
    public Trip(String title, Option options, ArrayList<Place> places, ArrayList<Long> dist){
        this.title =title;
        this.options=options;
        this.places=places;
        this.distances=dist;
        this.map = "";
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
        this.map = "";
        this.distances = new ArrayList<>();
    }

    /** The top level method that does planning.
     * At this point it just adds the map and distances for the places in order.
     * It might need to reorder the places in the future.
     */
    public void plan() throws Exception {
        if(this.options.optimization.equalsIgnoreCase("none")){
            this.distances = legDistances();
        } else{
            Optimize opt = new Optimize(this);
            Trip optTrip = opt.getOptimalTrip();
            this.title = optTrip.title;
            this.options = optTrip.options;
            this.places = optTrip.places;
            this.distances = optTrip.distances;
        }
        LineDistance worldMap = new LineDistance(this.places);
        if(this.options.map.equals("svg")){
            this.map = worldMap.getSvgMap();}
        else{
            this.map = worldMap.getKmlMap();
        }
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
     * @param distances ArrayList of Longs.
     */
    public void setDistances(ArrayList<Long> distances){
        this.distances = distances;
    }
    
    /**
     * Returns the distances between consecutive places,
     * including the return to the starting point to make a round trip.
     * @return An arrayList of type Long
     */
    private ArrayList<Long> legDistances() {

        ArrayList<Long> dist;
        if(this.options.units.equals("user defined")){
            dist = makeUserDefTrip();
        } else {
            dist = makeNormalTrip();
        }
        return dist;
    }
    
    /**
     * Helper method to plan user defined trip.
     * @return ArrayList of Long.
     */
    private ArrayList<Long> makeUserDefTrip() {
    
        ArrayList<Long> dist = new ArrayList<>();

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
    
    /**
     * Helper method to plan normal trip.
     * @return ArrayList of Long.
     */
    private ArrayList<Long> makeNormalTrip() {
    
        ArrayList<Long> dist = new ArrayList<>();

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