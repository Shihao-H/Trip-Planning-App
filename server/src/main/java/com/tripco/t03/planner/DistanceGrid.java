package com.tripco.t03.planner;

public class DistanceGrid {

    public Distance[][] distanceGrid;
    private Place[] locations;
    private String units;
    private String userDefinedUnitName;
    private Double userDefinedRadius;

    /**
    *Default contructor.
    */
    public DistanceGrid(){
        this.distanceGrid = null;
    }

    /**
    *Constructor for non user defined options
    *@param location array of Places.
    *@param units string of unit type.
    */
    public DistanceGrid(Place[] location, String units){
        this.locations=location;
        this.units = units;
        this.distanceGrid = new Distance[locations.length][locations.length];
    }

    /**
    *Constructor for userd defined options
    *@param locations array of Places.
    *@param units String unit type.
    *@param udUnitName String name of user defined unit.
    *@param udRadius Double the radius of Earth in user defined units.
    */
    public DistanceGrid(Place[] locations, String units, String udUnitName, Double udRadius){
        this.locations = locations;
        this.units = units;
        this.userDefinedUnitName = udUnitName;
        this.userDefinedRadius = udRadius;
        this.distanceGrid = new Distance[locations.length][locations.length];
    }

    /**
    *Builds a 2D array of Distance objects.
    */
    public void buildGrid(){
        for (int i = 0; i < this.locations.length; i++) {
            distanceGrid[i][i] = null;
        }
        for (int i = 0; i < locations.length; i++) {
            for (int j = i + 1; j < locations.length; j++) {
                if (distanceGrid[i][j] == null) {
                    if (this.units.equalsIgnoreCase("user defined")) {
                        distanceGrid[i][j] = new Distance(this.locations[i], locations[j], this.units, this.userDefinedUnitName, this.userDefinedRadius);
                    } else {
                        distanceGrid[i][j] = new Distance(locations[i], locations[j], this.units);
                    }
                    distanceGrid[i][j].setDistance();
                    distanceGrid[j][i] = new Distance(locations[j], locations[i], this.units, distanceGrid[i][j].distance);
                }
            }
        }
    }

}
