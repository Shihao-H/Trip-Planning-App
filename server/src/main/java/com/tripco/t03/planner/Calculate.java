package com.tripco.t03.planner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tripco.t03.server.HTTP;
import spark.Request;

import java.util.ArrayList;

/**
 * This class handles to the conversions of the JSON request/resopnse,
 * converting from the Json string in the request body to a Distance object,
 * perform calculation, and
 * converting the resulting Distance object back to a Json string
 * so it may returned as the response.
 */
public class Calculate {

  private Distance distance;

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
    distance.calculationDistance();

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
}
