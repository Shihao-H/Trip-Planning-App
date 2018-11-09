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
    public Trip(String title, Option options, ArrayList<Place> places, ArrayList<Long> dist){
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
//        if(this.options.optimization.equalsIgnoreCase("none")){
//            this.distances = legDistances();
//        } else{
//            Optimize opt = new Optimize(this);
//            Trip optTrip = opt.getOptimalTrip();
//            this.title = optTrip.title;
//            this.options = optTrip.options;
//            this.places = optTrip.places;
//            this.distances = optTrip.distances;
//        }
        if(this.options.optimization.equals("short")){
            nearestNeighbor();
        }
        this.distances = legDistances();
        LineDistance worldMap = new LineDistance(this.places);
        if(this.options.map.equals("svg")){
            this.map = worldMap.getSvgMap();}
        else{
            this.map = worldMap.getKmlMap();
        }
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
     * @param distances ArrayList of Longs.
     */
    public void setDistances(ArrayList<Long> distances){
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

    /////////////////////////////////////////////////
    private void nearestNeighbor(){

        int size = this.places.size();
        long distance[][] = new long[size][size];
        Place[] copyToArray = this.places.toArray(new Place[size]);

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if (i == j){
                    distance[i][j] = Integer.MAX_VALUE;
                } else if (i > j){
                    distance[i][j] = distance[j][i];
                } else {
                    Distance calc;
                    if (this.options.units.equals("user defined")) {
                        calc = new Distance(copyToArray[i], copyToArray[j], options.units, options.unitName, options.unitRadius);
                        calc.setDistance();
                        distance[i][j] = calc.distance;
                    } else {
                        calc = new Distance(copyToArray[i], copyToArray[j], options.units);
                        calc.setDistance();
                        distance[i][j] = calc.distance;
                    }
                }
            }
        }

        ////////////////////////////////////////////////////////

        boolean visit[];
        long total[] = new long[size];
        for(int k = 0; k < size; k++){
            visit = new boolean[size];
            visit[k] = true;
            int start = k;
            while(unvisitedCityLeft(visit)){
                int index = getMin(distance[start], visit, total, k);
                start = index;

            }
            total[k] += distance[start][k];
        }

        ///////////////////////////////////////////

        this.places = new ArrayList<Place>();
        int k = getMinValue(total);
        this.places.add(copyToArray[k]);
        visit = new boolean[size];
        visit[k] = true;
        int start = k;
        while(unvisitedCityLeft(visit)){
            int index = getMin(distance[start], visit, total, k);
            start = index;
            this.places.add(copyToArray[index]);
        }

    }

    public boolean unvisitedCityLeft(boolean[] visit){
        boolean flag = false;
        for(int i = 0; i < visit.length; i++){
            if(visit[i] == false)
                return true;
        }
        return flag;
    }

    public int getMin(long[] numbers, boolean[] visit, long[] total, int k){
        long minValue = -1;
        int i, index = -1;

        for(i = 0;i < numbers.length; i++){
            if(visit[i] == false){
                minValue = numbers[i];
                index = i;
                break;
            }
        }

        while(i < numbers.length){
            if(numbers[i] < minValue){
                if(visit[i] == false){
                    minValue = numbers[i];
                    index = i;
                }
            }
            i++;
        }

        visit[index] = true;
        total[k] += minValue;
        return index;
    }

    public int getMinValue(long[] numbers){
        long minValue = numbers[0];
        int index = 0;
        for(int i=1;i<numbers.length;i++){
            if(numbers[i] < minValue){
                minValue = numbers[i];
                index = i;
            }
        }
        return index;
    }
    ///////////////////////////////////////////////////
}
