package com.tripco.t03.planner;

import java.io.*;
import java.util.ArrayList;

/**
 * The Trip class supports TFFI so it can easily be converted to/from Json by Gson.
 *
 */

public class Trip {

    // The variables in this class should reflect TFFI.
    public int version = 3;
    public String type = "trip";
    public String title;
    public Option options;
    public ArrayList<Place> places;
    public ArrayList<Integer> distances;
    public String map;

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

    public Trip(String title, Option options, ArrayList<Place> places, ArrayList<Integer> distances){
        this.title =title;
        this.options=options;
        this.places=places;
        this.distances=distances;
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
        this.distances = legDistances();
        if(this.options.optimization.equalsIgnoreCase("short")){
            Optimize opt = new Optimize(this);
            Trip optTrip = opt.getOptimalTrip();
            this.title = optTrip.title;
            this.options = optTrip.options;
            this.places = optTrip.places;
            this.map = optTrip.map;
            svg();
        }
        setRoute();
    }

    /**
     * Adds the route to the existing map.
     */
    public void setRoute() {

        LineDistance ld = new LineDistance(this.places);
        String route = ld.getCoordinates();
        String fileLines = this.map.substring(0, this.map.length()-16) + route + this.map.substring(this.map.length()-16);
        this.map = fileLines;
    }

    /**
     * Creates an SVG containing the background and the legs of the trip.
     */
    public void svg() {
        String fileLines = "data:image/svg+xml," ;
        try {
            InputStream thisSVGwillNOTwin =Trip.class.getResourceAsStream("/CObackground.svg");
            if(thisSVGwillNOTwin != null){
                System.out.println("There might be some hope.");
            }else{
                System.out.println("GIVE UP NOW AND GO HOME!");
            }
            BufferedReader buffy = new BufferedReader(new InputStreamReader(thisSVGwillNOTwin));
            if(buffy.ready()){
                System.out.println("It found it.....");
                while(buffy.ready()){
                    fileLines+= buffy.readLine();
                }
                            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("POOP ON BUFFY INPUT STREAM!!");
        }
        this.map = fileLines;
    }

    /**
     * Returns the distances between consecutive places,
     * including the return to the starting point to make a round trip.
     * @return ArrayList<Integer>
     */
    private ArrayList<Integer> legDistances() {

        ArrayList<Integer> dist = new ArrayList<>();

        if(this.options.units.equals("user defined")){
            for (int counter = 0; counter < places.size() - 1; counter++) {
                Distance distance = new Distance(places.get(counter), places.get(counter + 1), options.units, options.unitName, options.unitRadius);
                distance.setDistance();
                dist.add(distance.distance);
            }
            Distance distance = new Distance(places.get(places.size() - 1), places.get(0), options.units, options.unitName, options.unitRadius);
            distance.setDistance();
            dist.add(distance.distance);
        }else {
            for (int counter = 0; counter < places.size() - 1; counter++) {
                Distance distance = new Distance(places.get(counter), places.get(counter + 1), options.units);
                distance.setDistance();
                dist.add(distance.distance);
            }
            Distance distance = new Distance(places.get(places.size() - 1), places.get(0), options.units);
            distance.setDistance();
            dist.add(distance.distance);
        }

        return dist;
    }

}