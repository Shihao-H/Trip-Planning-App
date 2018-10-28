package com.tripco.t03.planner;

public class DistanceGrid {

    public Distance[][] distanceGrid;
    private Place[] locations;
    private String units;
    private String userDefinedUnitName;
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
    public DistanceGrid(Place[] location, String units){
        this.locations=location;
        this.units = units;
        this.distanceGrid = new Distance[locations.length][locations.length];
    }

    /**
     * Constructor for user defined units
     * @param locations Array of places.
     * @param units String type of units.
     * @param udUnitName String name of user defined units.
     * @param udRadius Double radius of Earth in user defined units.
     */
    public DistanceGrid(Place[] locations, String units, String udUnitName, Double udRadius){
        this.locations = locations;
        this.units = units;
        this.userDefinedUnitName = udUnitName;
        this.userDefinedRadius = udRadius;
        this.distanceGrid = new Distance[locations.length][locations.length];
    }

    /**
     * Builds 2D array of Distance objects.
     */
    public void buildGrid(int row, int column){
        setSamePlace();
        if ((column < this.distanceGrid[row].length) && (this.distanceGrid[row][column] == null)) {
            setDistanceObject(row, column);
            setOpposite(row, column);
            buildGrid(row, column+1);
        }else if (column < this.distanceGrid.length){
            buildGrid(row+1, 0);
        }
    }

    /**
     * Sets distance object in Distance Grid.
     * @param row int row index.
     * @param column int column index.
     */
    private void setDistanceObject(int row, int column){
        if (this.units.equalsIgnoreCase("user defined")) {
            distanceGrid[row][column] = new Distance(this.locations[row], locations[column], this.units, this.userDefinedUnitName, this.userDefinedRadius);
        } else {
            distanceGrid[row][column] = new Distance(locations[row], locations[column], this.units);
        }
        distanceGrid[row][column].setDistance();
    }

    /**
     * Sets the opposite distance object equal to the same distance.
     * @param row int row index.
     * @param column int column index.
     */
    private void setOpposite(int row, int column){
        distanceGrid[column][row] = new Distance(locations[column], locations[row], this.units, distanceGrid[row][column].distance);
    }

    /**
     * Helper method for setting the Distance objects with the same origin and destination to null.
     */
    private void setSamePlace(){
        for (int i = 0; i < this.locations.length; i++) {
            distanceGrid[i][i] = null;
        }
    }

}
