package com.tripco.t03.planner;

import java.util.ArrayList;

public class DistanceGrid {

    public Integer[][] distanceGrid;
    private Integer[] indexKey;
    private ArrayList<Place> locations;
    private String units;
    private Double userDefinedRadius;

    /**
     * Default constructor.
     */
    public DistanceGrid(){
        this.distanceGrid = null;
    }

    /**
     * Constructor for non user defined units.
     * @param location Array of places.
     * @param units String type of units.
     */
    public DistanceGrid(ArrayList<Place> location, String units, Integer[] key){
        this.locations = new ArrayList<>();
        this.locations.addAll(location);
        this.units = units;
        this.distanceGrid = new Integer[locations.size()][locations.size()];
        this.indexKey=key;
        this.setSamePlace();
    }

    /**
     * Constructor for user defined units.
     * @param locations Array of places.
     * @param units String type of units.
     * @param udRadius Double radius of Earth in user defined units.
     */
    public DistanceGrid(ArrayList<Place> locations, String units, Double udRadius, Integer[] key){
        this.locations = new ArrayList<>();
        this.locations.addAll(locations);
        this.units = units;
        this.userDefinedRadius = udRadius;
        this.distanceGrid = new Integer[locations.size()][locations.size()];
        this.indexKey = key;
        this.setSamePlace();
    }

    /**
     * Builds 2D array of Distance objects.
     */
    public void buildGrid() {
        int row = 0;
        while (row < this.distanceGrid.length) {
            int column = row+1;
            while (column < this.distanceGrid[row].length) {
                if (this.distanceGrid[row][column] == null) {
                    Place origin = this.locations.get(indexKey[row]);
                    Place destination = this.locations.get(indexKey[column]);
                    if (this.units.equalsIgnoreCase("user defined")) {
                        this.distanceGrid[row][column] = Calculate.optDistance(origin, destination, this.userDefinedRadius);
                    } else {
                        this.distanceGrid[row][column] = Calculate.calcDistance(origin, destination, this.units);
                    }
                    distanceGrid[column][row] = this.distanceGrid[row][column];
                }
                column++;
            }
            row++;

        }
    }

    /**
     * Helper method for setting the Distance objects with the same origin and destination to null.
     */
    private void setSamePlace(){
        for (int i = 0; i < this.distanceGrid.length; i++) {
            distanceGrid[i][i] = Integer.MAX_VALUE;
        }
    }
}