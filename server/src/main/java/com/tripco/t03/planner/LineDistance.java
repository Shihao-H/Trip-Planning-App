package com.tripco.t03.planner;

import java.util.ArrayList;

public class LineDistance {
    public ArrayList<Place> places;
    private double centerLat = 39.0;
    private double centerLong = -105.5472;


    public LineDistance(ArrayList<Place> places){
        this.places = places;
    }

    private boolean setNorth(double lat) {
        return lat > centerLat;
    }

    private boolean setEast(double longitude){
        return longitude > centerLong;
    }

    private double getLatDistFromCenter(double lat, boolean east){
        double distFromCent = lat - centerLat;
        if(east) {
            return distFromCent * 177.08811;
        }else{
            return distFromCent*177.16875;
        }
    }

    private double getLongDistFromCenter(double longitude, boolean north){
        double distFromCent = longitude-centerLong;
        if (north) {
            return distFromCent * 141.6839;
        } else {
            return distFromCent * 142.0528;
        }
    }

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
