package com.tripco.t03.planner;

import java.util.ArrayList;
import java.util.Arrays;

class Optimize {
    private Trip trip;
    private Integer[] sortedPlaces;
    private Integer[] optimalIndices;
    private Integer[] twoOptIndices;
    private Integer[] optimalLegs;
    private int optimalTotalDistance;
    private DistanceGrid grid;

    /**
     * Constructor for Optimize object.
     * @param trip Trip object to optimize.
     */
    Optimize(Trip trip) {
        this.trip = trip;
        this.sortedPlaces = new Integer[trip.places.size()];
        this.sortedPlaces = MergeSortPlace.sort(this.trip.places);
        this.optimalIndices = new Integer[this.sortedPlaces.length];
        this.optimalLegs = new Integer[this.sortedPlaces.length];
        this.twoOptIndices = new Integer[this.sortedPlaces.length];
        setGrid();
    }

    /**
     * Getter for total distance.
     * @return int total distance for optimal trip.
     */
    int getOptimalTripDistance(){
        return this.optimalTotalDistance;
    }

    /**
     *Applies the appropriate level of optimization for the trip.
     * @return Trip Object that has been optimized.
     */
    Trip getOptimalTrip(){
        NearestNeighbor nn = new NearestNeighbor(this.sortedPlaces, this.grid.distanceGrid);
        nn.nearestNeighbor();
        nn.getOptimalTrip(this.optimalIndices);
        nn.getLegDistances(this.optimalLegs);
        this.optimalTotalDistance = nn.getTotalDistance();
        if(this.trip.options.optimization.equalsIgnoreCase("shorter")){
            System.arraycopy(this.optimalIndices, 0, this.twoOptIndices, 0,
                             twoOptIndices.length);
            TwoOpt twoOpt = new TwoOpt(this.twoOptIndices, this.grid.distanceGrid);
            twoOpt.twoOpt(this.optimalIndices);
            twoOpt.getSortedIndices(this.optimalIndices);
            twoOpt.getTwoOptLegDistances(this.optimalLegs);
            this.optimalTotalDistance = twoOpt.getTotalDistance();
        }
        return buildNewTrip();
    }

    /**
     * Helper method that builds the optimized trip object to return.
     * @return Trip object.
     */
    private Trip buildNewTrip(){
        ArrayList<Place> optimal = new ArrayList<>();
        for(int i = 0; i < this.trip.places.size(); i++){
            optimal.add(this.trip.places.get(this.optimalIndices[i]));
        }
        ArrayList<Integer> legs = new ArrayList<>(Arrays.asList(this.optimalLegs));
        return new Trip( this.trip.title, this.trip.options, optimal, legs);
    }

    /**
     * Method to set up Distance object grid.
     */
    private void setGrid(){
        if(this.trip.options.units.equalsIgnoreCase("user defined")){
            this.grid = new DistanceGrid(this.trip.places, this.trip.options.units, this.trip.options.unitRadius, sortedPlaces);
        }else {
            this.grid = new DistanceGrid(this.trip.places, this.trip.options.units, sortedPlaces);
        }
        this.grid.buildGrid();
    }
}