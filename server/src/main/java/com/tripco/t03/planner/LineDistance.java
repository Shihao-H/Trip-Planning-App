package com.tripco.t03.planner;

import java.util.ArrayList;

public class LineDistance {
    public ArrayList<Place> places;
    private double centerLat = 39.0;
    private double centerLong = -105.5472;
    private double latToPxlEast = 177.08811;
    private double latToPxlWest = 177.16875;
    private double longToPxlNorth = 141.6839;
    private double longToPxlSouth = 142.0528;
    private boolean north;
    private boolean east;

    public LineDistance(ArrayList<Place> places){
        this.places = places;
    }

    private void setLatQuad(double lat, double longitude){
        if (lat > centerLat){
            this.north = true;
        }else{
            this.north = false;
        }
        if (longitude > centerLong){
            this.east = true;
        }
        else{
            this.east = false;
        }
    }

    private double getLatDistFromCenter(double lat){
        double distFromCent;
        if(north){
            distFromCent = lat - centerLat;
        }else{
            distFromCent = centerLat-lat;
        }
        if(east) {
            return distFromCent * latToPxlEast;
        }else{
            return distFromCent*latToPxlWest;
        }
    }

    private double getLongDistFromCenter(double longitude){
        double distFromCent;
        if(east) {
            distFromCent = longitude-centerLong;
        }else{
            distFromCent = centerLong-longitude;
        }
        if (north) {
            return distFromCent * longToPxlNorth;
        } else {
            return distFromCent * longToPxlSouth;
        }
    }

    public ArrayList<Double> getCoordinates(ArrayList<Place> places){
        ArrayList<Double> coordinates = new ArrayList<>();
        for(int i = 0; i < places.size(); i++) {
            setLatQuad(places.get(i).latitude, places.get(i).longitude);
            if (north) {
                coordinates.add(391.5412 - getLatDistFromCenter(places.get(i).latitude));
            } else {
                coordinates.add(391.5412 + getLatDistFromCenter(places.get(i).latitude));
            }
            if (east) {
                coordinates.add(30365 + getLongDistFromCenter(places.get(i).longitude));
            } else {
                coordinates.add(533.30365 - getLongDistFromCenter(places.get(i).longitude));
            }
        }
        return coordinates;
    }
}
