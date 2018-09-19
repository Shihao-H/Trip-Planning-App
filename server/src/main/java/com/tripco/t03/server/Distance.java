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
     *
     * @return returns the delta sigma value for the designated units
     */
    private double getDeltaSigma() {

        double deltaLongitude = Math.toRadians(destination.getLongitude() - origin.getLongitude()),
                destinationLatitude = Math.toRadians(destination.getLatitude()),
                originLatitude = Math.toRadians(origin.getLatitude()),

                numerator, denominator;


        numerator = Math.sqrt(Math.pow((Math.cos(destinationLatitude) * Math.sin(deltaLongitude)), 2.0) +
                Math.pow((Math.cos(originLatitude) * Math.sin(destinationLatitude) -
                        Math.sin(originLatitude) * Math.cos(destinationLatitude) * Math.cos(deltaLongitude)), 2.0));

        denominator = Math.sin(originLatitude) * Math.sin(destinationLatitude) +
                Math.cos(originLatitude) * Math.cos(destinationLatitude) * Math.cos(deltaLongitude);

        return Math.atan2(numerator, denominator);

    }

    /**
     * Calls getDeltaSigma() and uses that value to determine the distance between two lat/long coordinates
     * Assigns that value to the distance variable
     */
    public void calculationDistance(){
        switch(units) {
            case "miles":distance = (int)Math.round((3959 * getDeltaSigma())); break;
            case "kilometers":distance = (int) Math.round((6371 * getDeltaSigma())); break;
            case "nautical miles": distance = (int) Math.round((3440 * getDeltaSigma())); break;
        }
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