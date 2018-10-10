package com.tripco.t03.planner;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tripco.t03.server.HTTP;
import spark.Request;

public class Match {

    private Search search;

    public Match (Request request) {
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

    public String getMatch() {
        Gson gson = new Gson();
        return gson.toJson(search);
    }
}
