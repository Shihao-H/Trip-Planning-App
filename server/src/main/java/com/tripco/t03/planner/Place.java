package com.tripco.t03.planner;


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
    public Place(){
        this.id = null;
        this.name = null;
        this.latitude = null;
        this.longitude = null;
    }

    /**
     * Constructs an instance of Place for the desired place.
     * @param id identifier given by user.
     * @param name name of the place string.
     * @param latitude latitude coordinates double.
     * @param longitude longitude coordinates double.
     */
    public Place(String id, String name, Double latitude, Double longitude){
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Constructor for user defined units.
     * @param id String identifier for place.
     * @param name String place name.
     * @param userDefined String userDefined.
     * @param latitude Double latitude in decimal degrees.
     * @param longitude Double longitude in decimal degrees.
     */
    public Place(String id, String name, String userDefined, Double latitude, Double longitude){
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
     * Constructor for place with all attributes.
     * @param id
     * @param name
     * @param latitude
     * @param longitude
     * @param type
     * @param elevation
     * @param continent
     * @param country
     * @param region
     * @param municipality
     */
    public Place(String id, String name, Double latitude, Double longitude,
                 String type, String elevation, String continent, String country,
                 String region, String municipality){
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
     * Copy constructor.
     * @param from Place object to copy.
     */
    public Place(Place from){
        this.id = from.id;
        this.name = from.name;
        this.latitude = from.latitude;
        this.longitude = from.longitude;
    }
    /**
     * @return name of place object.
     */
    public String getName(){
        return this.name;
    }

    /**
     * @return latitude of place object.
     */
    public double getLatitude(){
        return this.latitude;
    }

    /**
     * @return longitude of place object.
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
