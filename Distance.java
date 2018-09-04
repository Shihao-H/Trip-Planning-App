package com.tripco.t03.server;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class Distance {
    private short version = 1;
    private String type = "distance";

    private List<String> origin = Arrays.asList("latitude", "longitude", "name");
    private List<String> destination = Arrays.asList("latitude", "longitude", "name");
    private List<String> units = Arrays.asList("miles");
    private List<String> distance = Arrays.asList("0");

    static String getDistance() {
        Distance dist = new Distance();
        Gson gson = new Gson();

        return gson.toJson(dist);
    }

    private void vincentyCalculation(){
        if(units.get(0).equals("miles")) {
            double deltaLongitude = Math.abs((Double.parseDouble(origin.get(1)) - Double.parseDouble(destination.get(1)))),
                    deltaLatitude = Math.abs((Double.parseDouble(origin.get(0)) - Double.parseDouble(destination.get(0)))),
                    destinationLatitude = Double.parseDouble(destination.get(0)),
                    originLatitude = Double.parseDouble(origin.get(0)),
                    destinationLongitude = Double.parseDouble(destination.get(1)),
                    originLongitude = Double.parseDouble(origin.get(1)),
                    numerator, denominator, deltaSigma;
            
            numerator = Math.pow((Math.cos(destinationLatitude) * Math.sin(deltaLongitude)), 2.0);
            
            double rightSide = Math.pow(((Math.cos(originLatitude) * Math.sin(destinationLatitude)) -
                    (Math.sin(originLatitude) * Math.cos(destinationLatitude) * Math.cos(deltaLongitude))), 2.0);
            
            numerator = Math.sqrt(numerator + rightSide);

            denominator = Math.sin(originLatitude) * Math.sin(destinationLatitude) +
                    Math.cos(originLatitude) * Math.cos(destinationLatitude) * Math.cos(deltaLongitude);

            deltaSigma = 1/Math.tan(numerator/denominator);

            distance.add(0, String.format("%.2f", (3959 * deltaSigma)));

        }else {
            distance.add(0, "-1");
        }
    }
}
