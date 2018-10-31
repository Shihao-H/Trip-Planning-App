package com.tripco.t03.planner;

/**
 * Describes the places to visit in a trip in TFFI format.
 * There may be other attributes of a place, but these are required to plan a trip.
 */
public class Place {
    public String id;
    public String name;
    public Double latitude;
    public Double longitude;

    public String type;
    public String elevation;
    public String continent;
    public String country;
    public String region;
    public String municipality;

    /**
     * Default constructor
     */
    public Place(){}

    /**
     * @param id identifier given by user
     * @param name name of the place string
     * @param latitude latitude coordinates double
     * @param longitude longitude coordinates double
     *                  constructs an instance of Place for the desired place
     */
    public Place(String id, String name, Double latitude, Double longitude){
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Place(String id, String name, Double latitude, Double longitude,
                 String type, String elevation, String continent, String country, String region, String municipality){
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.elevation = elevation;
        this.continent = continent;
        this.country = country;
        this.region = region;
        this.municipality = municipality;
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
        return (this.longitude == place.longitude)
                && (this.latitude == place.latitude)
                && (this.name.equals(place.name))
                && (this.id.equals(place.id));
    }

    public String toString(){
        return String.format("Id: %s, Name: %s, Latitude: %f, Longitude: %f",
                this.id, this.name, this.latitude, this.longitude);
    }
}