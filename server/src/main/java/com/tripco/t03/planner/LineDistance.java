package com.tripco.t03.planner;

import java.util.ArrayList;

public class LineDistance {
    public ArrayList<Place> places;
    private double centerLat = 39.0;
    private double centerLong = -105.5472;

    /**
     * Constructor.
     * @param places ArrayList of places.
     */
    public LineDistance(ArrayList<Place> places){
        this.places = places;
    }

    /**
     * Sets north boolean.
     * @param lat double.
     * @return true if the latitude is greater than the center lat of the state.
     */
    private boolean setNorth(double lat) {
        return lat > centerLat;
    }

    /**
     * Sets east boolean.
     * @param longitude double.
     * @return boolean.
     */
    private boolean setEast(double longitude){
        return longitude > centerLong;
    }

    /**
     * Gets the distance from the center of the state.
     * @param lat double.
     * @param east boolean.
     * @return double.
     */
    private double getLatDistFromCenter(double lat, boolean east){
        double distFromCent = lat - centerLat;
        if(east) {
            return distFromCent * 177.08811;
        }else{
            return distFromCent*177.16875;
        }
    }

    /**
     * Gets distance from the center of the state.
     * @param longitude double.
     * @param north boolena.
     * @return double.
     */
    private double getLongDistFromCenter(double longitude, boolean north){
        double distFromCent = longitude-centerLong;
        if (north) {
            return distFromCent * 141.6839;
        } else {
            return distFromCent * 142.0528;
        }
    }

    /**
     * Gets coordinates.
     * @return String.
     */
    public String getCoordinates(){
        String path = " d=\"M ";
        for(int i = 0; i < this.places.size(); i++) {
            path += String.format("%.6f", (533.30365 + getLongDistFromCenter(this.places.get(i).longitude, setNorth(places.get(i).latitude)))) +
                    "," + String.format("%.6f", (391.5412 - getLatDistFromCenter(this.places.get(i).latitude, setEast(places.get(i).longitude))));
            if(i != (this.places.size()-1)){
                path += " L ";
            }else{
                path += " z";
            }
        }
        path+="\" style=\"fill:none;fill-rule:evenodd;stroke:red;stroke-width:2.5\" ";
        return path;
    }
}
