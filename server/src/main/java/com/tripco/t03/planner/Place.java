package com.tripco.t03.planner;

/**
 * Describes the places to visit in a trip in TFFI format.
 * There may be other attributes of a place, but these are required to plan a trip.
 */
public class Place {
  public String id;
  public String name;
  public double latitude;
  public double longitude;

  public Place(){
    name = null;
    latitude = -1.0;
    longitude = -1.0;
  }

  public Place(String name, double latitude, double longitude){
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getName(){
    return this.name;
  }

  public double getLatitude(){
    return this.latitude;
  }

  public double getLongitude(){
    return this.longitude;
  }

  public void setName(String name){
    this.name = name;
  }

  public void setLatitude(double latitude){
    this.latitude = latitude;
  }

  public void setLongitude(double longitude){
    this.longitude = longitude;
  }
}
