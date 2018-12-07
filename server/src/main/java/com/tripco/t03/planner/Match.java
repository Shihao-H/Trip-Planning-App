package com.tripco.t03.planner;

import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
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

    /**
     * Handles places searching request, creating a new search object from the search request.
     * Does the conversion from Json to a Java class before searching the places.
     *
     * @param request should be {a single string}.
     */
    public Match(Request request) throws SQLException, ClassNotFoundException {

        // extract the information from the body of the request.
        JsonElement requestBody = Calculate.jsonHandler(request);
        // convert the body of the request to a Java class.
        Gson gson = new Gson();
        search = gson.fromJson(requestBody, Search.class);

        // search the places.
        search.findMatch();
    }

    /**
     * Constructor.
     *
     * @param search Search object.
     */
    public Match(Search search) {

        this.search = search;
    }

    /**
     * Handles the response for a Search object.
     * Does the conversion from a Java class to a Json string.*
     */
    public String getMatch() {

        Gson gson = new Gson();
        return gson.toJson(search);
    }
}
