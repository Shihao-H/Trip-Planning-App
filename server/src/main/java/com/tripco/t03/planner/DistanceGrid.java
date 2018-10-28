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
    public void buildGrid(){
        setSamePlace();
        for (int i = 0; i < locations.length; i++) {
            for (int j = i + 1; j < locations.length; j++) {
                if (distanceGrid[i][j] == null) {
                    setDistanceObject(i, j);
                    setOpposite(i, j);
                }
            }
        }
    }

    /**
     * Sets distance object in Distance Grid.
     * @param i int row index.
     * @param j int column index.
     */
    private void setDistanceObject(int i, int j){
        if (this.units.equalsIgnoreCase("user defined")) {
            distanceGrid[i][j] = new Distance(this.locations[i], locations[j], this.units, this.userDefinedUnitName, this.userDefinedRadius);
        } else {
            distanceGrid[i][j] = new Distance(locations[i], locations[j], this.units);
        }
        distanceGrid[i][j].setDistance();
    }

    /**
     * Sets the opposite distance object equal to the same distance.
     * @param i int row index.
     * @param j int column index.
     */
    private void setOpposite(int i, int j){
        distanceGrid[j][i] = new Distance(locations[j], locations[i], this.units, distanceGrid[i][j].distance);
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
