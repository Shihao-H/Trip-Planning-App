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
    public ArrayList<Long> distances;
    public String map;
    private Long totalDist;
    /**
     * Default constructor.
     */

    public Trip(){
        this.title = null;
        this.options = new Option();
        this.places = null;
        this.distances = null;
        this.map = "";
        svg();
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
        svg();
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
        svg();
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
        svg();
    }

        /** The top level method that does planning.
     * At this point it just adds the map and distances for the places in order.
     * It might need to reorder the places in the future.
     */
    public void plan() {

        if(this.options.optimization.equalsIgnoreCase("none")){
            this.distances = legDistances();
            System.out.printf("Trip Distance: %d\n", this.totalDist);
        } else{
           // this.distances = legDistances();
            Optimize opt = new Optimize(this);
            Trip optTrip = opt.getOptimalTrip();
            this.title = optTrip.title;
            this.options = optTrip.options;
            this.places = optTrip.places;
            this.map = optTrip.map;
            this.distances=optTrip.distances;
            svg();
            setRoute();
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
     * Adds the route to the existing map.
     */
    void setRoute() {
        LineDistance ld = new LineDistance(this.places);
        String route = ld.getCoordinates();
        this.map = this.map.substring(0, this.map.length()-16) + route
                + this.map.substring(this.map.length()-16);
    }

    /**
     * Creates an SVG containing the background and the legs of the trip.
     */
    public void svg() {
        String fileLines = "" ;
        try {
            InputStream thisSvgWillNotWin =Trip.class.getResourceAsStream("/CObackground.svg");
            BufferedReader buffy = new BufferedReader(new InputStreamReader(thisSvgWillNotWin));
            if(buffy.ready()){
                while(buffy.ready()){
                    fileLines= fileLines + buffy.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.map = fileLines;
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

    private ArrayList<Long> makeUserDefTrip() {

        this.totalDist = 0L;
        ArrayList<Long> dist = new ArrayList<>();

        for (int count = 0; count < places.size() - 1; count++) {
            Distance leg = new Distance(places.get(count), places.get(count + 1),
                    options.units, options.unitName, options.unitRadius);
            leg.setDistance();
            dist.add(leg.distance);
            totalDist += leg.distance;
        }
        Distance leg = new Distance(places.get(places.size() - 1), places.get(0),
                options.units, options.unitName, options.unitRadius);
        leg.setDistance();
        dist.add(leg.distance);
        totalDist += leg.distance;

        return dist;
    }

    private ArrayList<Long> makeNormalTrip() {
    
        this.totalDist = 0L;
        ArrayList<Long> dist = new ArrayList<>();

        for (int count = 0; count < places.size() - 1; count++) {
            Distance leg = new Distance(places.get(count), places.get(count+1), options.units);
            leg.setDistance();
            dist.add(leg.distance);
            totalDist += leg.distance;
        }
        Distance leg = new Distance(places.get(places.size()-1), places.get(0), options.units);
        leg.setDistance();
        dist.add(leg.distance);
        totalDist += leg.distance;

        return dist;
    }
}