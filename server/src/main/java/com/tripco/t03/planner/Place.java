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
    public Place() {}

    /**
     * Constructs an instance of Place for the desired place.
     *
     * @param id        identifier given by user.
     * @param name      name of the place string.
     * @param latitude  latitude coordinates double.
     * @param longitude longitude coordinates double.
     */
    public Place(String id, String name, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Constructor for user defined units.
     *
     * @param id           String identifier for place.
     * @param name         String place name.
     * @param latitude     Double latitude in decimal degrees.
     * @param longitude    Double longitude in decimal degrees.
     * @param type         Double longitude in decimal degrees.
     * @param elevation    Double elevation looks like an integer, for safety, declare it as a double.
     * @param continent    String the continent where the airport is in.
     * @param country      String the country where the airport is in.
     * @param region       String the region where the airport is in.
     * @param municipality String the municipality where the airport is in.
     */
    public Place(String id, String name, Double latitude, Double longitude,
                 String type, String elevation, String continent, String country,
                 String region, String municipality) {
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
     *
     * @param from Place object to copy.
     */
    public Place(Place from) {
        this.id = from.id;
        this.name = from.name;
        this.latitude = from.latitude;
        this.longitude = from.longitude;
    }

    /**
     * Setter for attributes.
     *
     * @param type String.
     */
    public void setAttributeType(String type) {
        this.type = type;
    }

    /**
     * Setter for attributes.
     *
     * @param elevation Double.
     */
    public void setAttributeElevation(String elevation) {
        this.elevation = elevation;
    }

    /**
     * Setter for attributes.
     *
     * @param continent String.
     */
    public void setAttributeContinent(String continent) {
        this.continent = continent;
    }

    /**
     * Setter for attributes.
     *
     * @param county String.
     */
    public void setAttributeCounty(String county) {
        this.country = country;
    }

    /**
     * Setter for attributes.
     *
     * @param region String.
     */
    public void setAttributeRegion(String region) {
        this.region = region;
    }

    /**
     * Setter for attributes.
     *
     * @param municipality String.
     */
    public void setAttributeMunicipality(String municipality) {
        this.municipality = municipality;
    }

    /**
     * @return name of place object.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return latitude of place object.
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * @return longitude of place object.
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * @param place Place object.
     * @return true if equal, false if not equal.
     * Compares two Place objects for equality.
     */
    public boolean equals(Place place) {
        return (this.longitude == place.longitude)
                && (this.latitude == place.latitude)
                && (this.name.equals(place.name))
                && (this.id.equals(place.id));
    }

    public String toString() {
        return String.format("Id: %s, Name: %s, Latitude: %f, Longitude: %f",
                this.id, this.name, this.latitude, this.longitude);
    }
}