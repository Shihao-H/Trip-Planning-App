package com.tripco.t03.planner;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Trip {

    public int version = 4;
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
        this.places = new ArrayList<>();
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
     * Constructor that accepts a distance ArrayList.
     * @param title String.
     * @param options Option object.
     * @param places ArrayList of Place Objects.
     * @param distances ArrayList of Integers.
     */
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

    /** The top level method that does planning.
     * At this point it just adds the map and distances for the places in order.
     * It might need to reorder the places in the future.
     */
    public void plan() {
        if(this.options.optimization.equalsIgnoreCase("short")){
            Optimize opt = new Optimize(this);
            Trip optTrip = opt.getOptimalTrip();
            this.title = optTrip.title;
            this.options = optTrip.options;
            this.places = new ArrayList<>();
            this.places.addAll(optTrip.places);
            svg();
        }else {
            this.distances = legDistances();
        }
        setRoute();
    }

    /**
     * Adds the route to the existing map.
     */
    public void setRoute() {
        LineDistance ld = new LineDistance(this.places);
        String route = ld.getCoordinates();
        this.map = this.map.substring(0, this.map.length()-16) + route + this.map.substring(this.map.length()-16);
    }

    /**
     * Creates an SVG containing the background and the legs of the trip.
     */
    public void svg() {
        String fileLines = "" ;
        try {
            InputStream thisSVGwillNOTwin =Trip.class.getResourceAsStream("/CObackground.svg");
            BufferedReader buffy = new BufferedReader(new InputStreamReader(thisSVGwillNOTwin));
            if(buffy.ready()){
                while(buffy.ready()){
                    fileLines= fileLines + buffy.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("POOP ON BUFFY INPUT STREAM!!");
        }
        this.map = fileLines;
    }

    /**
     * Returns the distances between consecutive places.
     * including the return to the starting point to make a round trip.
     * @return ArrayList<Integer>.
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

    public void opt2Reverse(int i,int k,Place[] route) {
        while(i < k) {
            Place temp = route[i];
            route[i] = route[k];
            route[k] = temp;
            i++; k--;
        }
        this.places =new ArrayList<>(Arrays.asList(route));
    }

    public void TwoOpt () {
        final int n = places.size();
        Place[] bestPath = new Place[n];
        for(int k=0;k<n;k++) {
            bestPath[k]=this.places.get(k);
        }
        if (n > 4) {
            boolean improvement = true;
            while (improvement) {
                improvement = false;
                for (int i = 0; i <= n - 3; i++) {
                    for (int j = i + 2; j <= n - 1; j++) {
                        Place o1, o2, d1, d2;
                        if (j == n - 1) {
                            o1 = bestPath[i];
                            o2 = bestPath[i+1];
                            d1 = bestPath[j];
                            d2 = bestPath[0];
                        }
                        else {
                            o1 = bestPath[i];
                            o2 = bestPath[i+1];
                            d1 = bestPath[j];
                            d2 = bestPath[j+1];
                        }
                        int delta = -Calculate.calcDistance(o1, o2, this.options.units) - Calculate.calcDistance(d1, d2, this.options.units)
                                + Calculate.calcDistance(o1, d1, this.options.units) + Calculate.calcDistance(o2, d2, this.options.units);
                        if (delta < 0) {
                            opt2Reverse(i + 1, j, bestPath);
                            improvement = true;
                        }
                    }
                }
            }
        }
    }
}