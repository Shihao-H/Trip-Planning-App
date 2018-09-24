package com.tripco.t03.planner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tripco.t03.server.HTTP;
import spark.Request;
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
        id = null;
        name = null;
        latitude = '\0';
        longitude = '\0';
    }

    /**
     * @param id identifier given by user
     * @param name name of the place string
     * @param latitude latitude coordinates double
     * @param longitude longitude coordinates double
     *                  constructs an instance of Place for the desired place
     */
    public Place(String id, String name, double latitude, double longitude){
        this.id = id;
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

    /**
     * @param place Place object.
     * @return true if equal, false if not equal.
     * Compares two Place objects for equality.
     */
    public boolean equals(Place place){
        return (this.longitude == place.longitude) && (this.latitude == place.latitude) && (this.name.equalsIgnoreCase(place.name)) && (this.id.equalsIgnoreCase(place.id));
    }

    public String toString(){
        return String.format("Id: %s, Name: %s, Latitude: %f, Longitude: %f", this.id, this.name, this.latitude, this.longitude);
    }
}