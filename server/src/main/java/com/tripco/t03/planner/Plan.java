package com.tripco.t03.planner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.io.IOException;

import spark.Request;

/**
 * This class handles to the conversions of the trip request/resopnse,
 * converting from the Json string in the request body to a Trip object,
 * planning the Trip, and
 * converting the resulting Trip object back to a Json string
 * so it may returned as the response.
 */
public class Plan {

  private Trip trip;

  /** Handles trip planning request, creating a new trip object from the trip request.
   * Does the conversion from Json to a Java class before planning the trip.
   * @param request HTTP Request.
   */
  public Plan(Request request) throws Exception {

    // extract the information from the body of the request.
    JsonElement requestBody = Calculate.jsonHandler(request);
  
    // convert the body of the request to a Java class.
    Gson gson = new Gson();
    trip = gson.fromJson(requestBody, Trip.class);

    // plan the trip.
    trip.plan();

  }

    /**
     * Constructor that takes a JsonObject for testing.
     * @param json JsonObject.
     */
  public Plan(String json) throws Exception {
      Gson gson = new Gson();
      trip = gson.fromJson(json, Trip.class);

      trip.plan();
  }

  /** Handles the response for a Trip object.
   * Does the conversion from a Java class to a Json string.*
   */
  public String getTrip() {
    Gson gson = new Gson();
    return gson.toJson(trip);
  }
}
