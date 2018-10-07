package com.tripco.t03.planner;
import com.google.gson.Gson;
import com.tripco.t03.planner.Place;
public class Distance {
    public short version = 2;
    public String type = "distance";
    public Place origin;
    public Place destination;
    public String units;
    public String unitName;
    public double unitRadius;
    public int distance;

    /**Create a default distance object
     * Declares and initializes default Place objects for destination and origin
     * No params
     */
    public Distance(){
        origin = new Place();
        destination = new Place();
        this.units = null;
        this.unitName = null;
        this.unitRadius = '\0';
        this.distance = 0;
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
        this.unitRadius = '\0';
        this.distance = 0;

    }

    /**
     * @param orig trip origin Place object
     * @param dest trip destination Place object
     * @param units String designation of unit type
     * @param uName String name of user defined units
     * @param rad value of Earth's radius in user defined units
     * Creates a distance object with data provided.
     */
    public Distance(Place orig, Place dest, String units, String uName, double rad){
        this.origin=orig;
        this.destination=dest;
        this.units = units;
        this.unitName=uName;
        this.unitRadius=rad;
        this.distance = 0;
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
        this.unitRadius = '\0';
        this.distance = distance;
    }

    /**
     * Sets distance element to circle distance between origin and destination by calling calcDistance
     */
    public void setDistance(){
        this.distance = Calculate.calcDistance(this);
    }

    /**
     * @param distance Distance object
     * Compares one distance object to another distance object.
     * @return returns true if equal, false if not equal.
     */
    public boolean equals(Distance distance){
        return this.destination.equals(distance.destination) && this.distance == distance.distance && this.origin.equals(distance.origin)
                && this.type.equalsIgnoreCase(distance.type) && this.unitName.equalsIgnoreCase(distance.unitName) && this.unitRadius == distance.unitRadius
                && this.units.equalsIgnoreCase(distance.units) && this.version == distance.version;
    }
    /**
     * @return returns the string format for a distance variable
     */
    public String toString(){
        String out = String.format("Origin: latitude: %f, longitude: %f, name: %s, Destination: latitude: %f, longitude: %f, name: %s,",
                origin.getLatitude(), origin.getLongitude(), origin.getName(), destination.getLatitude(), destination.getLongitude(), destination.getName());
        if(this.units.equals("user defined")){
            out = String.format(" Units: %s, Unit Name: %s, Distance: %d.", this.units, this.unitName, this.distance);
        }else {
            out = String.format(" Units: %s, Distance: %d.", this.units, this.distance);
        }
        return out;
    }
}
