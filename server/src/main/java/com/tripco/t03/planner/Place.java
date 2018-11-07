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
     * Constructs an instance of Place for the desired place.
     * @param country country given by user string.
     * @param id identifier given by user.
     * @param name name of the place string.
     * @param latitude latitude coordinates double.
     * @param longitude longitude coordinates double.
     */
    public Place(String country, String id, String name, Double latitude, Double longitude){
        this.country=country;
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
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
        this.type = from.type;
        this.elevation = from.elevation;
        this.continent = from.continent;
        this.country = from.country;
        this.region = from.region;
        this.municipality = from.municipality;
    }

    /**
     * Setter for attributes.
     * @param type String.
     */
    public void setAttributeType(String type) {
        this.type = type;
    }

    /**
     * Setter for atrributes.
     * @param elevation String.
     */
    public void setAttributeElevation(String elevation) {
        this.elevation = elevation;
    }

    /**
     * Setter for attributes.
     * @param continent String.
     */
    public void setAttributeContinent(String continent) {
        this.continent = continent;
    }

    /**
     * Setter for attributes.
     * @param country String.
     */
    public void setAttributeCountry(String country) {
        this.country = country;
    }

    /**
     * Setter for attributes.
     * @param region String.
     */
    public void setAttributeRegion(String region) {
        this.region = region;
    }

    /**
     * Setter for attributes.
     * @param municipality String.
     */
    public void setAttributeMunicipality(String municipality){
        this.municipality = municipality;
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
     * toString for Place object.
     * @return String.
     */
    public String toString(){
        return String.format("Id: %s, Name: %s, Latitude: %f, Longitude: %f",
                this.id, this.name, this.latitude, this.longitude);
    }
}
