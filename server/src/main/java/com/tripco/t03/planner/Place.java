package com.tripco.t03.planner;

/**
 * Describes the places to visit in a trip in TFFI format.
 * There may be other attributes of a place, but these are required to plan a trip.
 */
public class Place {
  public String id;
  public String name;
  public String latitude;
  public String longitude;

  public Place(){
    id = null;
    name = null;
    latitude = null;
    longitude = null;
  }

  public Place(String id, String name, String latitude, String longitude){
    this.id = id;
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  
}
