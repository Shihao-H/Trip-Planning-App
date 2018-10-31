package com.tripco.t03.server;

import com.google.gson.Gson;
import com.tripco.t03.planner.Filters;

import java.util.ArrayList;
import java.util.Arrays;

public class Config {
  private String type = "config";
  private short version = 4;
  private ArrayList<String> units;
  private ArrayList<Optimization> optimization;
  private ArrayList<String> attributes;
  private Filters filters;
  private String maps;

  public Config(){
    units = new ArrayList<>(Arrays.asList("miles", "kilometers", "nautical miles", "user defined"));
    optimization = new ArrayList<>(Arrays.asList(new Optimization("none", "The trip is not optimized."), new Optimization("short", "Nearest neighbor."), new Optimization("shorter", "Two Opt")));
    attributes = new ArrayList<>(Arrays.asList("name", "id", "latitude", "longitude", "type", "elevation", "continent", "country", "region", "municipality"));
    filters = new Filters();
    maps = "svg";
  }

  static String getConfig() {
    Config conf = new Config();
    Gson gson = new Gson();
    return gson.toJson(conf);
  }
}
