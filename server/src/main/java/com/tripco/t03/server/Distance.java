package com.tripco.t03.server;

import com.google.gson.Gson;
import com.tripco.t03.planner.Place;

import java.util.ArrayList;

public class Distance {
    private short version = 1;
    private String type = "distance";

    private Place origin;
    private Place destination;
    private ArrayList<String> units;
    private ArrayList<String> distanceArray;

    public Distance(){
        origin = new Place();
        destination = new Place();
        units = new ArrayList<String>();
        distanceArray = new ArrayList<String>();
    }

    public Distance(Place orig, Place dest, ArrayList<String> units){
        origin = orig;
        destination = dest;
        this.units = units;
        distanceArray = new ArrayList<String>();
    }

    static String getDistance() {
        Distance dist = new Distance();
        Gson gson = new Gson();
        return gson.toJson(dist);
    }

    private double getDeltaSigma() {
        double deltaLongitude = Math.toRadians((Double.parseDouble(destination.getLongitude()) - Double.parseDouble(origin.getLongitude()))),
                destinationLatitude = Math.toRadians(Double.parseDouble(destination.getLatitude())),
                originLatitude = Math.toRadians(Double.parseDouble(origin.getLatitude())),
                numerator, denominator;


        numerator = Math.sqrt(Math.pow((Math.cos(destinationLatitude) * Math.sin(deltaLongitude)), 2.0) +
                Math.pow((Math.cos(originLatitude) * Math.sin(destinationLatitude) -
                        Math.sin(originLatitude) * Math.cos(destinationLatitude) * Math.cos(deltaLongitude)), 2.0));

        denominator = Math.sin(originLatitude) * Math.sin(destinationLatitude) +
                Math.cos(originLatitude) * Math.cos(destinationLatitude) * Math.cos(deltaLongitude);

        return Math.atan2(numerator, denominator);
    }

    public void calculationDistance(){
        for(String u: units) {
            switch(u) {
                case "miles":distanceArray.add(String.format("%d", Math.round((3959 * getDeltaSigma())))); break;
                case "kilometers":distanceArray.add(String.format("%d", Math.round((6371 * getDeltaSigma())))); break;
                case "nautical miles": distanceArray.add(String.format("%d", Math.round((3440 * getDeltaSigma())))); break;
                default: distanceArray.add("-1");
            }
        }
    }
}