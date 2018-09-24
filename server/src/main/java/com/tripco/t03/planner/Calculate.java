package com.tripco.t03.planner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tripco.t03.server.HTTP;
import spark.Request;


/**
 * This class handles to the conversions of the JSON request/resopnse,
 * converting from the Json string in the request body to a Distance object,
 * perform calculation, and
 * converting the resulting Distance object back to a Json string
 * so it may returned as the response.
 */
public class Calculate {

  private static Distance distance;

  /** Handles JSON request, creating a new Distance object from the JSON request.
   * Does the conversion from Json to a Java class before performing calculation.
   */
  public Calculate (Request request) {
    // first print the request
    System.out.println(HTTP.echoRequest(request));

    // extract the information from the body of the request.
    JsonParser jsonParser = new JsonParser();
    JsonElement requestBody = jsonParser.parse(request.body());

    // convert the body of the request to a Java class.
    Gson gson = new Gson();
    distance = gson.fromJson(requestBody, Distance.class);

    // perform calculation.
    distance.setDistance();

    // log something.
    System.out.println(distance.toString());
  }

  /** Handles the response for a Distance object.
   * Does the conversion from a Java class to a Json string.*
   */
  public String getDistance () {
    Gson gson = new Gson();
    return gson.toJson(distance);
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

    double  deltaLongitude = Math.abs(Math.toRadians(oLong - dLong));
    double  destinationLatitude = Math.toRadians(dLat);
    double  originLatitude = Math.toRadians(oLat);
    double  numerator;
    double  denominator;

    double cosLatSinLongSqr = (Math.cos(destinationLatitude) * Math.sin(deltaLongitude));
    cosLatSinLongSqr = cosLatSinLongSqr * cosLatSinLongSqr; 

    double cosLatSinLatMnsSinLatCosLatCosLongSqr = ((Math.cos(originLatitude) * Math.sin(destinationLatitude) -
            Math.sin(originLatitude) * Math.cos(destinationLatitude) * Math.cos(deltaLongitude));
    cosLatSinLatMnsSinLatCosLatCosLongSqr = cosLatSinLatMnsSinLatCosLatCosLongSqr * cosLatSinLatMnsSinLatCosLatCosLongSqr;

      numerator = Math.sqrt(cosLatSinLongSqr + cosLatSinLatMnsSinLatCosLatCosLongSqr);
            denominator = Math.sin(originLatitude) * Math.sin(destinationLatitude) +
            Math.cos(originLatitude) * Math.cos(destinationLatitude) * Math.cos(deltaLongitude);
    return Math.atan2(numerator, denominator);
  }


  public static int circleDistance(double radius, double deltaSigma){
    return (int)Math.round((radius*deltaSigma));
  }
  /**
   * @param distance Distance object
   * @return integer value of distance between origin and destination; -1 if invalid.
   * Calls getDeltaSigma() and uses that value to determine the distance between two lat/long coordinates
   * Assigns that value to the distance variable
   */
  public static int calcDistance(Distance distance){
    double radius = -1;
    if(distance.units.equalsIgnoreCase("miles")) {
      radius = 3959.0;
    }else if(distance.units.equalsIgnoreCase("kilometers")) {
      radius = 6371.0;
    }else if(distance.units.equalsIgnoreCase("nautical miles")){
      radius = 3440.0;
    }else if(distance.units.equalsIgnoreCase("user defined")) {
      radius = distance.unitRadius;
    }
   return circleDistance(radius, getDeltaSigma(distance.origin.latitude,distance.origin.longitude,distance.destination.latitude, distance.destination.longitude));
  }
}
