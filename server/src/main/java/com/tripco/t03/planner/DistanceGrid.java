package com.tripco.t03.planner;

import java.util.ArrayList;

class DistanceGrid {
    Long[][] distanceGrid;
    private Integer[] indexKey;
    private ArrayList<Place> locations;
    private String units;
    private Double userDefinedRadius;

    /**
     * Default constructor.
     */
    DistanceGrid(){
        this.distanceGrid = null;
    }

    /**
     * Constructor for non user defined units.
     * @param location Array of places.
     * @param units String type of units.
     */
    DistanceGrid(ArrayList<Place> location, String units, Integer[] key){
        this.locations = new ArrayList<>();
        this.locations.addAll(location);
        this.units = units;
        this.distanceGrid = new Long[locations.size()][locations.size()];
        this.indexKey=key;
        this.setSamePlace();
    }

    /**
     * Constructor for user defined units.
     * @param locations Array of places.
     * @param units String type of units.
     * @param udRadius Double radius of Earth in user defined units.
     */
    DistanceGrid(ArrayList<Place> locations, String units, Double udRadius, Integer[] key){
        this.locations = new ArrayList<>();
        this.locations.addAll(locations);
        this.units = units;
        this.userDefinedRadius = udRadius;
        this.distanceGrid = new Long[locations.size()][locations.size()];
        this.indexKey = key;
        this.setSamePlace();
    }

    /**
     * Builds 2D array of Distance objects.
     */
    void buildGrid() throws Exception {
        for(int row = 0; row < this.distanceGrid.length; row++) {
            setDistance(row, row+1);
        }
    }

    /**
     * Helper method for setting initializing distance grid.
     * @param row int index.
     * @param column int index.
     */
    private void setDistance(int row, int column) throws Exception {
        if(column < this.distanceGrid[row].length) {
            if(this.distanceGrid[row][column] == null) {
                Place origin = this.locations.get(indexKey[row]);
                Place destination = this.locations.get(indexKey[column]);
                double radius = getRadius(this.units);
                long temp = getDistance(origin, destination, radius);
                this.distanceGrid[row][column] = temp;
                distanceGrid[column][row] = this.distanceGrid[row][column];
            }
            setDistance(row, column + 1);
        }
    }

    /**
     * Helper method to get radius value.
     * @param units String.
     * @return double Radius.
     */
    private double getRadius(String units) throws Exception {
        Double result;
        switch (units){
            case "miles": result = 3959.0;
                break;
            case "kilometers": result = 6371.0;
                break;
            case "nautical miles": result = 3440.0;
                break;
            case "user defined": result = this.userDefinedRadius;
                break;
            default: throw new Exception("No valid units");
        }
        return result;
    }

    /**
     * Helper method to get the distance.
     * @param origin Place object.
     * @param destination Place object.
     * @param radius double radius for units.
     * @return long distance between origin and destination.
     */
    private long getDistance(Place origin, Place destination, double radius){
        return Calculate.optDistance(origin, destination, radius);
    }

    /**
     * Helper method for setting the Distance objects with the same origin and destination to null.
     */
    private void setSamePlace(){
        for (int i = 0; i < this.distanceGrid.length; i++) {
            distanceGrid[i][i] = Long.MAX_VALUE;
        }
    }
}
