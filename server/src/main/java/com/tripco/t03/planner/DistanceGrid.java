package com.tripco.t03.planner;

public class DistanceGrid {

    public Distance[][] distanceGrid;
    private Place[] locations;
    private String units;
    private String userDefinedUnitName;
    private Double userDefinedRadius;

    public DistanceGrid(){
        this.distanceGrid = null;
    }

    public DistanceGrid(Place[] location, String units){
        this.locations=location;
        this.units = units;
        this.distanceGrid = new Distance[locations.length][locations.length];
    }

    public DistanceGrid(Place[] locations, String units, String udUnitName, Double udRadius){
        this.locations = locations;
        this.units = units;
        this.userDefinedUnitName = udUnitName;
        this.userDefinedRadius = udRadius;
        this.distanceGrid = new Distance[locations.length][locations.length];
    }

    public void buildGrid(){
        for (int i = 0; i < this.locations.length; i++) {
            distanceGrid[i][i] = null;
        }
        for (int i = 0; i < locations.length; i++) {
            for (int j = i + 1; j < locations.length; j++) {
                if (distanceGrid[i][j] == null) {
                    if (this.units.equalsIgnoreCase("user defined")) {
                        distanceGrid[i][j] = new Distance(this.locations[i], locations[j], this.units, this.userDefinedUnitName, this.userDefinedRadius);
                        distanceGrid[i][j].setDistance();
                        distanceGrid[j][i] = new Distance(locations[j], locations[i], this.units, distanceGrid[i][j].distance);
                    } else {
                        distanceGrid[i][j] = new Distance(locations[i], locations[j], this.units);
                        distanceGrid[i][j].setDistance();
                        distanceGrid[j][i] = new Distance(locations[j], locations[i], this.units, distanceGrid[i][j].distance);
                    }
                }
            }
        }
    }

}
