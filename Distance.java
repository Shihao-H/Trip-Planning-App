package com.tripco.t03.server;

import com.google.gson.Gson;
import com.tripco.t03.planner.Place;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.ArrayList;

public class Distance {
    private short version = 1;
    private String type = "distance";

 private Place origin;
    private Place destination;
    private ArrayList<String> units;
    private ArrayList<String> distance;

    public Distance(){
        origin = null;
        destination = null;
        units = new ArrayList<String>();
        distance = new ArrayList<String>();
    }

    public Distance(Place orig, Place dest, ArrayList<String> units){
        origin = orig;
        destination = dest;
        this.units = units;
        distance = new ArrayList<String>();
    }

    static String getDistance() {
        Distance dist = new Distance();
        Gson gson = new Gson();
        return gson.toJson(dist);
    }

    private double getDeltaSigma() {
        double deltaLongitude = Math.abs((Double.parseDouble(origin.getLongitude()) - Double.parseDouble(destination.getLongitude()))),
                deltaLatitude = Math.abs((Double.parseDouble(origin.getLatitude()) - Double.parseDouble(destination.getLatitude()))),
                destinationLatitude = Double.parseDouble(destination.getLatitude()),
                originLatitude = Double.parseDouble(origin.getLatitude()),
                destinationLongitude = Double.parseDouble(destination.getLongitude()),
                originLongitude = Double.parseDouble(origin.getLongitude()),
                numerator, denominator, deltaSigma;
        
        numerator = Math.pow((Math.cos(destinationLatitude) * Math.sin(deltaLongitude)), 2.0);
        
        double rightSide = Math.pow(((Math.cos(originLatitude) * Math.sin(destinationLatitude)) -
                (Math.sin(originLatitude) * Math.cos(destinationLatitude) * Math.cos(deltaLongitude))), 2.0);
        
        numerator = Math.sqrt(numerator + rightSide);

        denominator = Math.sin(originLatitude) * Math.sin(destinationLatitude) +
                Math.cos(originLatitude) * Math.cos(destinationLatitude) * Math.cos(deltaLongitude);

        return 1/Math.tan(numerator/denominator);
    }

    private void vincentyCalculation(){
        if (u.equals("miles")) {
            distance.add(String.format("%.2f", (3959 * getDeltaSigma())));
        }else {
            distance.add("-1");         
       }
    }
}
