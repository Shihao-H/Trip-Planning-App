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

}