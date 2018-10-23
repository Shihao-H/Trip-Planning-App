package com.tripco.t03.server;

import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;

public class Config {
  private String type = "config";
  private short version = 3;
  private List<String> units;
  private List<Optimization> optimization;
  private List<String> attributes;

  public Config(){
    units = Arrays.asList("miles", "kilometers", "nautical miles", "user defined");
    optimization = Arrays.asList(new Optimization("none", "The trip is not optimized."), new Optimization("short", "Nearest neighbor."),
            new Optimization("shorter", "Two Opt"));
    attributes = Arrays.asList("name", "id", "latitude", "longitude", "userDefined");
  }

  static String getConfig() {
    Config conf = new Config();
    Gson gson = new Gson();
    return gson.toJson(conf);
  }
}
