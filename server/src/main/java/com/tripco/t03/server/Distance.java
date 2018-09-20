package com.tripco.t03.server;

import com.google.gson.Gson;
import com.tripco.t03.planner.Place;

import java.util.ArrayList;

public class Distance {
    public short version = 1;
    public String type = "distance";

    public Place origin;
    public Place destination;

    public String units;
    public int distance;

    /**Create a default distance object
     * Declares and initializes default Place objects for destination and origin
     * No params
     */
    public Distance(){
        origin = new Place();
        destination = new Place();


    }

    /**
     * @param orig trip origin Place object
     * @param dest trip destination Place object
     * @param units String designation of unit type
     *              Creates a distance object with data
     */
    public Distance(Place orig, Place dest, String units){
        origin = orig;
        destination = dest;
        this.units = units;

    }

    /**
     * @return returns the distance value
     */
    public int getDist(){
        return this.distance;
    }

    /**
     * @param oLat double origin latitude
     * @param oLong double origin longitude
     * @param dLat double destination latitude
     * @param dLong double destination longitude
     *              calculates delta sigma for circle distance using Vincenty formula
     * @return returns floating point delta sigma value for the designated units
     */
    private static double getDeltaSigma(double oLat, double oLong, double dLat, double dLong) {

        double deltaLongitude = Math.toRadians(dLong - oLong),
                destinationLatitude = Math.toRadians(dLat),
                originLatitude = Math.toRadians(oLat),

                numerator, denominator;


        numerator = Math.sqrt(Math.pow((Math.cos(destinationLatitude) * Math.sin(deltaLongitude)), 2.0) +
                Math.pow((Math.cos(originLatitude) * Math.sin(destinationLatitude) -
                        Math.sin(originLatitude) * Math.cos(destinationLatitude) * Math.cos(deltaLongitude)), 2.0));

        denominator = Math.sin(originLatitude) * Math.sin(destinationLatitude) +
                Math.cos(originLatitude) * Math.cos(destinationLatitude) * Math.cos(deltaLongitude);

        return Math.atan2(numerator, denominator);

    }

    /**
     * @param lat1 origin latitude
     * @param long1 origin longitude
     * @param lat2 destination latitude
     * @param long2 destination longitude
     * @param units units for radius
     * @return integer value of distance between origin and destination; -1 if invalid.
     * Calls getDeltaSigma() and uses that value to determine the distance between two lat/long coordinates
     * Assigns that value to the distance variable
     */
    public static int calcDistance(double lat1, double long1, double  lat2, double long2, String units){
        if(units.equals("miles")) {
            return (int) Math.round((3959 * getDeltaSigma(lat1, long1, lat2, long2)));
        }else if(units.equals("kilometers")) {
            return (int) Math.round((6371 * getDeltaSigma(lat1, long1, lat2, long2)));
        }else if(units.equals("nautical miles")){
            return (int) Math.round((3440 * getDeltaSigma(lat1, long1, lat2, long2)));
        }else {
            return -1;
        }
    }

    /**
     * Sets distance element to circle distance between origin and destination by calling calcDistance
     */
    public void setDistance(){
            this.distance = calcDistance(this.origin.latitude, this.origin.longitude, this.destination.latitude, this.destination.longitude, this.units);
    }

    /**
     * @return returns the string format for a distance variable
     */
    public String toString(){
        return String.format("Origin: latitude: %f, longitude: %f, name: %s, Destination: latitude: %f, longitude: %f, name: %s, Units: %s, Distance: %d",
                origin.getLatitude(), origin.getLongitude(), origin.getName(), destination.getLatitude(), destination.getLongitude(), destination.getName(),
                this.units, this.distance);

    }
}
