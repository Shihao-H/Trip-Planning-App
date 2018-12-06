package com.tripco.t03.planner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import spark.Request;

public class Calculate {

    private static Distance distance;

    /**
     * Handles JSON request, creating a new Distance object from the JSON request.
     * Does the conversion from Json to a Java class before performing calculation.
     */
    public Calculate(Request request) {

        JsonElement requestBody = jsonHandler(request);
        // convert the body of the request to a Java class.
        Gson gson = new Gson();
        distance = gson.fromJson(requestBody, Distance.class);

        // perform calculation.
        distance.setDistance();
    }

    /**
     * Constructor.
     *
     * @param input Distance object.
     */
    public Calculate(Distance input) {
        distance = input;
        distance.setDistance();
    }

    /**
     * A method written to cut code climate duplication that failed.
     *
     * @param request Request.
     * @return JsonElement.
     */
    static JsonElement jsonHandler(Request request) {

        // extract the information from the body of the request.
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(request.body());
    }

    /**
     * Handles the response for a Distance object.
     * Does the conversion from a Java class to a Json string.*
     */
    public String getDistance() {
        Gson gson = new Gson();
        return gson.toJson(distance);
    }

    /**
     * Calls getDeltaSigma() and uses that value to determine the distance between two.
     * lat/long coordinates and assigns that value to the distance variable.
     *
     * @param p1     Place object.
     * @param p2     Place object.
     * @param radius double radius of Earth in desired units.
     * @return long value of distance between origin and destination; -1 if invalid.
     */
    static long optDistance(Place p1, Place p2, double radius) {
        double temp = getDeltaSigma(p1.latitude, p1.longitude, p2.latitude, p2.longitude);
        return circleDistance(radius, temp);
    }

    /**
     * Calls getDeltaSigma() and uses that value to determine the distance between two.
     * lat/long coordinates and assigns that value to the distance variable.
     *
     * @param origin      Place object.
     * @param destination Place object.
     * @param units       String designating the units for radius.
     * @return long value of distance between origin and destination; -1 if invalid.
     */
    static long calcDistance(Place origin, Place destination, String units) {
        double radius = -1;
        if (units.equalsIgnoreCase("miles")) {
            radius = 3959.0;
        } else if (units.equalsIgnoreCase("kilometers")) {
            radius = 6371.0;
        } else if (units.equalsIgnoreCase("nautical miles")) {
            radius = 3440.0;
        }
        double origLat = origin.latitude;
        double origLong = origin.longitude;
        double destLat = destination.latitude;
        double destLong = destination.longitude;
        double temp = getDeltaSigma(origLat, origLong, destLat, destLong);
        return circleDistance(radius, temp);
    }

    /**
     * Calls getDeltaSigma() and uses that value to determine the distance between two.
     * lat/long coordinates and assigns that value to the distance variable.
     *
     * @param distance Distance object.
     * @return long value of distance between origin and destination; -1 if invalid.
     */
    static long calcDistance(Distance distance) {
        double radius = -1;
        if (distance.units.equalsIgnoreCase("miles")) {
            radius = 3959.0;
        } else if (distance.units.equalsIgnoreCase("kilometers")) {
            radius = 6371.0;
        } else if (distance.units.equalsIgnoreCase("nautical miles")) {
            radius = 3440.0;
        } else if (distance.units.equalsIgnoreCase("user defined")) {
            radius = distance.unitRadius;
        }
        double origLat = distance.origin.latitude;
        double origLong = distance.origin.longitude;
        double destLat = distance.destination.latitude;
        double destLong = distance.destination.longitude;
        double temp = getDeltaSigma(origLat, origLong, destLat, destLong);
        return circleDistance(radius, temp);
    }

    /**
     * Calculates the final circle distance value.
     *
     * @param radius     radius of earth in desired units
     * @param deltaSigma delta sigma from Vincenty formula
     * @return long final distance between two points.
     */
    private static long circleDistance(double radius, double deltaSigma) {
        return Math.round((radius * deltaSigma));
    }

    /**
     * Calculates delta sigma for circle distance using Vincenty formula.
     *
     * @param origLat  double origin latitude.
     * @param origLong double origin longitude.
     * @param destLat  double destination latitude.
     * @param destLong double destination longitude.
     * @return returns double delta sigma value for the designated units.
     */
    private static double getDeltaSigma(double origLat, double origLong,
                                        double destLat, double destLong) {

        double deltaLongitude = Math.abs(Math.toRadians(origLong - destLong));
        double destinationLatitude = Math.toRadians(destLat);
        double originLatitude = Math.toRadians(origLat);

        double cosDestLat = Math.cos(destinationLatitude);
        double sinDeltaLong = Math.sin(deltaLongitude);

        double cosLatSinLongSqr = (cosDestLat * sinDeltaLong);
        cosLatSinLongSqr *= cosLatSinLongSqr;

        double cosOrigLat = Math.cos(originLatitude);
        double sinDestLat = Math.sin(destinationLatitude);
        double sinOrigLat = Math.sin(originLatitude);
        double cosDeltaLong = Math.cos(deltaLongitude);

        double cosLatSinLatMnsSinLatCosLatCosLongSqr = (cosOrigLat * sinDestLat
                - sinOrigLat * cosDestLat
                * cosDeltaLong);
        cosLatSinLatMnsSinLatCosLatCosLongSqr = cosLatSinLatMnsSinLatCosLatCosLongSqr
                * cosLatSinLatMnsSinLatCosLatCosLongSqr;

        double numerator = Math.sqrt(cosLatSinLongSqr + cosLatSinLatMnsSinLatCosLatCosLongSqr);
        double denominator = sinOrigLat * sinDestLat
                + cosOrigLat * cosDestLat * cosDeltaLong;
        return Math.atan2(numerator, denominator);
    }
}
