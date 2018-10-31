package com.tripco.t03.planner;

import java.util.ArrayList;
import java.util.Arrays;

public class Optimize {
    private Trip trip;
    private Integer[] sortedPlaces;
    private DistanceGrid grid;

    /**
     * Constructor for Optimize object.
     * @param trip Trip object to optimize.
     */
    public Optimize(Trip trip) {
       /*this.sortedPlaces = new Integer[trip.places.size()];
        this.trip = trip;
        this.sortedPlaces = MergeSortPlace.sort(this.trip.places);
        setGrid();*/
    }

    /**
     * Getter::2D array for distance objects.
     * @return 2D Distance Array.
     */
    public DistanceGrid getDistanceGrid(){
        return this.grid;
    }

    /**
     * Getter::Sorted Places Array.
     * @return Place[] sorted array.
     */
    public Integer[] getSortedArray(){
        return this.sortedPlaces;
    }

    /**
    *Method currently does nothing.
    */
    public Trip getOptimalTrip(){/*
        NearestNeighbor nn = new NearestNeighbor(this);
        int index = nn.optimizeNearestNeighbor();
        Place[] optimalTrip = nn.getTripPlaceArray(index);
        Integer[] optimalDistances = nn.getDistanceArray(index);
        this.trip.setPlace(optimalTrip);
        this.trip.setDistances(new ArrayList<>(Arrays.asList(optimalDistances[index])));
        return this.trip;
    */
        return null;
    }

        /**
     * Method to set up Distance object grid.
     *//*
    private void setGrid(){
        if(this.trip.options.units.equalsIgnoreCase("user defined")){
            Double rad = this.trip.options.unitRadius;
            String uName = this.trip.options.unitName;
            this.grid = new DistanceGrid(this.sortedPlaces, this.trip.options.units, uName, rad);
        }else {
            this.grid = new DistanceGrid(this.sortedPlaces, this.trip.options.units);
        }
        this.grid.buildGrid(0,0);
    }

    *//**
     * Method to convert ArrayList to Array and call MergeSortPlace class.
     * @param original ArrayList of Places.
     * @return Place[] Array of Places sorted by longitude.
     *//*
    private Place[] setSortedArray(ArrayList<Place> original){
        Place[] toSort = new Place[original.size()];
        toSort = original.toArray(toSort);
        return MergeSortPlace.sort(toSort);
    }*/
}
