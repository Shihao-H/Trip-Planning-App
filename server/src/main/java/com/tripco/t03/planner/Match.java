package com.tripco.t03.planner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tripco.t03.server.HTTP;
import spark.Request;

/**
 * This class handles to the conversions of the search request/ resopnse,
 * converting from the Json string in the request body to a Search object,
 * searching the places, and
 * converting the resulting Search object back to a Json string
 * so it may returned as the response.
 */
public class Match {

    private Search search;

    /** Handles places searching request, creating a new search object from the search request.
     * Does the conversion from Json to a Java class before searching the places.
     * @param request
     */
    public Match(Request request) {
        // first print the request
        System.out.println(HTTP.echoRequest(request));

        // extract the information from the body of the request.
        JsonParser jsonParser = new JsonParser();
        JsonElement requestBody = jsonParser.parse(request.body());

        // convert the body of the request to a Java class.
        Gson gson = new Gson();
        search = gson.fromJson(requestBody, Search.class);

        // search the places.
        search.match();
    }

    /** Handles the response for a Search object.
     * Does the conversion from a Java class to a Json string.*
     */
    public String getMatch() {
        Gson gson = new Gson();
        return gson.toJson(search);
    }
}
