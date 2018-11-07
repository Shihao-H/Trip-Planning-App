package com.tripco.t03.planner;

public class Distance {
    public short version = 4;
    public String type = "distance";
    public Place origin;
    public Place destination;
    public String units;
    public String unitName;
    public Double unitRadius;
    public Long distance;
    
    /**
     * Create a default distance object. Declares and initializes default Place objects for
     * destination and origin No params
     */
    public Distance() {
        origin = new Place();
        destination = new Place();
        this.units = "";
        this.unitName = null;
        this.unitRadius = null;
        this.distance = null;
    }
    
    /**
     * Creates a distance object with data.
     * @param orig trip origin Place object
     * @param dest trip destination Place object
     * @param units String designation of unit type
     */
    public Distance(Place orig, Place dest, String units) {
        this.origin = orig;
        this.destination = dest;
        this.units = units;
        this.unitName = null;
        this.unitRadius = null;
        this.distance = null;
    }
    
    /**
     * Creates a distance object with data provided.
     * @param orig trip origin Place object
     * @param dest trip destination Place object
     * @param units String designation of unit type
     * @param unitName String name of user defined units
     * @param rad value of Earth's radius in user defined units
     */
    public Distance(Place orig, Place dest, String units, String unitName, Double rad) {
        this.origin = orig;
        this.destination = dest;
        this.units = units;
        this.unitName = unitName;
        this.unitRadius = rad;
        this.distance = null;
    }
    
    /**
     * Creates a distance object with data.
     * @param orig trip origin Place object
     * @param dest trip destination Place object
     * @param units String designation of unit type
     * @param distance Long.
     */
    public Distance(Place orig, Place dest, String units, Long distance) {
        this.origin = orig;
        this.destination = dest;
        this.units = units;
        this.unitName = null;
        this.unitRadius = null;
        this.distance = distance;
    }
    
    /**
     * Sets dist element to circle dist between origin and dest by calling calcDistance.
     */
    public void setDistance() {
        this.distance = Calculate.calcDistance(this);
    }
}
