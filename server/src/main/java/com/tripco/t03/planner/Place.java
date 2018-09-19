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

    /**
     * Default constructor
     */
    public Place(){
        name = null;
        latitude = -1.0;
        longitude = -1.0;
    }

    /**
     * @param name name of the place string
     * @param latitude latitude coordinates double
     * @param longitude longitude coordinates double
     *                  constructs an instance of Place for the desired place
     */
    public Place(String name, double latitude, double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * @return name of place object
     */
    public String getName(){
        return this.name;
    }

    /**
     * @return latitude of place object
     */
    public double getLatitude(){
        return this.latitude;
    }

    /**
     * @return longitude of place object
     */
    public double getLongitude(){
        return this.longitude;
    }
}