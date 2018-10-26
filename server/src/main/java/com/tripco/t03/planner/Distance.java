package com.tripco.t03.planner;
public class Distance {
    public short version = 4;
    public String type = "distance";
    public Place origin;
    public Place destination;
    public String units;
    public String unitName;
    public Double unitRadius;
    public Integer distance;

    /**Create a default distance object
     * Declares and initializes default Place objects for destination and origin
     * No params
     */
    public Distance(){
        origin = new Place();
        destination = new Place();
        this.units = "";
        this.unitName = null;
        this.unitRadius = null;
        this.distance = null;
    }

    /**
     * @param orig trip origin Place object
     * @param dest trip destination Place object
     * @param units String designation of unit type
     *Creates a distance object with data.
     */
    public Distance(Place orig, Place dest, String units){
        this.origin = orig;
        this.destination = dest;
        this.units = units;
        this.unitName=null;
        this.unitRadius = null;
        this.distance = null;

    }

    /**
     * @param orig trip origin Place object
     * @param dest trip destination Place object
     * @param units String designation of unit type
     * @param uName String name of user defined units
     * @param rad value of Earth's radius in user defined units
     * Creates a distance object with data provided.
     */
    public Distance(Place orig, Place dest, String units, String uName, Double rad){
        this.origin=orig;
        this.destination=dest;
        this.units = units;
        this.unitName=uName;
        this.unitRadius=rad;
        this.distance = null;
    }

    /**
     * @param orig trip origin Place object
     * @param dest trip destination Place object
     * @param units String designation of unit type
     *Creates a distance object with data.
            */
    public Distance(Place orig, Place dest, String units, int distance){
        this.origin = orig;
        this.destination = dest;
        this.units = units;
        this.unitName=null;
        this.unitRadius = null;
        this.distance = distance;
    }

    /**
     * Sets distance element to circle distance between origin and destination by calling calcDistance
     */
    public void setDistance(){
        this.distance = Calculate.calcDistance(this);
    }

    /**
     * @return returns the string format for a distance variable
     */
    public String toString(){
        String out;
        if(this.units.equals("user defined")) {
            out = String.format(" Units: %s, Unit Name: %s, Distance: %d.", this.units, this.unitName, this.distance);
        } else {
            out = String.format(" Units: %s, Distance: %d.", this.units, this.distance);
        }
        return String.format("Origin: latitude: %f, longitude: %f, name: %s, Destination: latitude: %f, longitude: %f, name: %s,",
                origin.getLatitude(), origin.getLongitude(), origin.getName(), destination.getLatitude(), destination.getLongitude(), destination.getName()) + out;
    }
}
