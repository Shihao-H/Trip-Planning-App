package com.tripco.t03.planner;

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

    public Trip() {
        this.title = null;
        this.options = new Option();
        this.places = null;
        this.distances = null;
        this.map = "";
    }

    /**
     * @param options Option Object.
     * @param places  ArrayList of Place.
     *                Constructor without title.
     */
    public Trip(Option options, ArrayList<Place> places) {
        this.title = null;
        this.options = options;
        this.places = places;
        this.map = "";
    }

    /**
     * @param title   Title of the trip.
     * @param options The options that the trip will have
     * @param places  The list of places in the trip.
     * @param dist    The list of distances between each place
     */

    public Trip(String title, Option options, ArrayList<Place> places, ArrayList<Long> dist) {
        this.title = title;
        this.options = options;
        this.places = places;
        this.distances = dist;
        this.map = svg();
    }

    /**
     * @param title   String user defined title for trip.
     * @param options Option Object.
     * @param places  ArrayList of Place objects.
     *                Constructor with title.
     */

    public Trip(String title, Option options, ArrayList<Place> places) {
        this.title = title;
        this.options = options;
        this.places = places;
        this.map = "";
    }

    /**
     * Setter for places ArrayList.
     *
     * @param newPlaces Array of Places.
     */
    public void setPlace(Place[] newPlaces) {
        this.places.clear();
        this.places.addAll(Arrays.asList(newPlaces));
    }

    /**
     * Setter for distances ArrayList.
     *
     * @param distances ArrayList of Longs.
     */
    public void setDistances(ArrayList<Long> distances) {
        this.distances = distances;
    }


    /**
     * Creates an SVG containing the background and the legs of the trip.
     */
    public String svg() {
        LineDistance worldMap = new LineDistance();
        return worldMap.getBackground();
    }

    /**
     * Returns the distances between consecutive places,
     * including the return to the starting point to make a round trip.
     *
     * @return An arrayList of type Long
     */
    private ArrayList<Long> legDistances() {

        ArrayList<Long> dist;
        if (this.options.units.equals("user defined")) {
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
            Distance leg = new Distance(places.get(count), places.get(count + 1), options.units);
            leg.setDistance();
            dist.add(leg.distance);
            totalDist += leg.distance;
        }
        Distance leg = new Distance(places.get(places.size() - 1), places.get(0), options.units);
        leg.setDistance();
        dist.add(leg.distance);
        totalDist += leg.distance;

        return dist;
    }


    public void plan() {
        optimize();
        this.distances = legDistances();
        LineDistance worldMap = new LineDistance(this.places);
        this.map = worldMap.getMap();
    }

    private void optimize() {
        if (places.size() == 0) {
            return;
        }
        if (options == null || options.optimization == null) {
            return;
        } else if ("none".equals(options.optimization)) {
            return;
        }
        long[][] disGrid = disGrid();
        if ("short".equals(options.optimization)) {
            int []loc=fill();
            NearestNeighbor neighbor=new NearestNeighbor(loc,disGrid);
            neighbor.near(disGrid);
            updateList(neighbor.index);
        } else if ("shorter".equals(options.optimization)) {
            int []loc=fill();
            TwoOpt two=new TwoOpt(loc,disGrid);
            two.twoOpt(disGrid);
            updateList(two.index);
        }
        else if ("shortest".equals(options.optimization)) {
            int []loc=fill();
            ThreeOpt three=new ThreeOpt(loc,disGrid);
            three.threeOpt(disGrid);
            updateList(three.index);
        }
    }

    public void updateList(int[] loc)
    {
        ArrayList<Place> container=new ArrayList<>();
        for(int i=0;i<loc.length;i++)
        {
            container.add(this.places.get(loc[i]));
        }
        this.places=container;
    }

    public int[] fill()
    {
        int [] arr = new int[this.places.size()];
        for(int i=0;i<this.places.size();i++)
        {
            arr[i]=i;
        }
        return arr;
    }

    private long[][] disGrid() {
        long distance[][] = new long[this.places.size()][this.places.size()];
        Place[] copyToArray = this.places.toArray(new Place[this.places.size()]);
        for (int i = 0; i < this.places.size(); i++) {
            for (int j = i; j < this.places.size(); j++) {
                if (i == j) {
                    distance[i][j] = Long.MAX_VALUE;
                }
                 else {
                    Distance calc;
                    if (this.options.units.equals("user defined")) {
                        calc = new Distance(copyToArray[i], copyToArray[j], options.units, options.unitName, options.unitRadius);
                        calc.setDistance();
                        distance[i][j] = calc.distance;
                        distance[j][i] = distance[i][j];
                    } else {
                        calc = new Distance(copyToArray[i], copyToArray[j], options.units);
                        calc.setDistance();
                        distance[i][j] = calc.distance;
                        distance[j][i] = distance[i][j];
                    }
                }
            }
        }
        return distance;
    }
}


