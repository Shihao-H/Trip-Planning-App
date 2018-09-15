package com.tripco.t03.server;

import com.google.gson.Gson;
import com.tripco.t03.planner.Place;

import java.util.ArrayList;

public class Distance {
    private short version = 1;
    private String type = "distance";

    private Place origin;
    private Place destination;

    private String units;
    private int distance;


    public Distance(){
        origin = new Place();
        destination = new Place();


    }

    public Distance(Place orig, Place dest, String units){
        origin = orig;
        destination = dest;
        this.units = units;

    }

    public int getDist(){
        return this.distance;
    }

    static String getDistance() {
        Distance dist = new Distance();
        Gson gson = new Gson();
        return gson.toJson(dist);
    }

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

    public void calculationDistance(){
        switch(units) {
            case "miles":distance = (int)Math.round((3959 * getDeltaSigma())); break;
            case "kilometers":distance = (int) Math.round((6371 * getDeltaSigma())); break;
            case "nautical miles": distance = (int) Math.round((3440 * getDeltaSigma())); break;
        }
    }

    public String toString(){
        return String.format("Origin: latitude: %f, longitude: %f, name: %s, Destination: latitude: %f, longitude: %f, name: %s, Units: %s, Distance: %d",
                origin.getLatitude(), origin.getLongitude(), origin.getName(), destination.getLatitude(), destination.getLongitude(), destination.getName(),
                this.units, this.distance);

    }
}
