package com.tripco.t03.planner;

import java.io.*;
import java.util.ArrayList;

import com.google.gson.Gson;

public class LineDistance {
    private Double left = -180.0;
    private Double right = 180.0;
    private Double top = 90.0;
    private Double bottom = -90.0;
    private Double height = 512.0;
    private Double width = 1024.0;

    private String background = "";
    private String lines = "";
    private String map = "";

    private int[][] coordinates;
    private Double[] places;

    /**
     * Default Constructor.
     */
    public LineDistance() {
    }

    /**
     * Constructor.
     *
     * @param places ArrayList of places.
     */
    public LineDistance(ArrayList<Place> places) {
        this.coordinates = new int[places.size()][2];
        this.places = new Double[places.size()];
        for (int i = 0; i < places.size(); i++) {
            Place placeOut = places.get(i);
            this.places[i] = placeOut.longitude;
            this.coordinates[i][0] = (int) ((placeOut.longitude - left) / (right - left) * (width - 0.0) + 0.0); //x
            this.coordinates[i][1] = (int) ((placeOut.latitude - top) / (bottom - top) * (height - 0.0) + 0.0); //y
        }
    }

    /**
     * This function is for calculating the background layer.
     */
    private void backgroundMap() {
        this.background = "<g id=\"l1\">";
        String str = "";
        try {
            InputStream svgFile = Trip.class.getResourceAsStream("/World_map_with_nations.svg");
            BufferedReader reader = new BufferedReader(new InputStreamReader(svgFile));
            while (reader.ready()) {
                str += reader.readLine();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        this.background += str.substring(str.indexOf("<svg"));
        this.background += "</g>";
    }

    /**
     * This function is for calculating the route layer.
     */
    private void AddLines() {
        this.backgroundMap();
        this.lines = "<g id=\"l2\">";
        this.lines += "<svg id=\"lines\" height=\"512\" width=\"1024\" y=\"0\" x=\"0\" " +
                "xmlns:svg=\"http://www.w3.org/2000/svg\" xmlns=\"http://www.w3.org/2000/svg\">" +
                "<title>lines</title>";
        for (int i = 0; i < this.coordinates.length; i++) {
            if (i != this.coordinates.length - 1) {
                check(this.places[i], this.places[i + 1], i, i + 1);
            } else {
                check(this.places[i], this.places[0], i, 0);
            }
        }
        this.lines += "</svg>";
        this.lines += "</g>";
    }

    /**
     * Helper function to draw the line between two points.
     *
     * @param x1 integer origin's x coordinate.
     * @param y1 integer origin's y coordinate.
     * @param x2 integer destination's x coordinate.
     * @param y2 integer destination's y coordinate.
     */
    public void drawLine(int x1, int y1, int x2, int y2) {
        this.lines += "<polyline points=\""
                + x1 + "," + y1 + " "
                + x2 + "," + y2 + "\" "
                + "fill=\"none\" stroke-width=\"4\" stroke=\"#203060\" />";
    }

    /**
     * Helper function to check the position of two points.
     *
     * @param x1 double origin's longitude.
     * @param x2 double destination's y longitude.
     * @param i integer origin's index.
     * @param j integer destination's index.
     */
    public void check(double x1, double x2, int i, int j) {
        if (x1 - x2 < -180.0) {
            drawLine((this.coordinates[i][0] + 1024), this.coordinates[i][1],
                    this.coordinates[j][0], this.coordinates[j][1]);
            drawLine(this.coordinates[i][0], this.coordinates[i][1],
                    (this.coordinates[j][0] - 1024), this.coordinates[j][1]);
        } else if (x1 - x2 > 180.0) {
            drawLine(this.coordinates[i][0], this.coordinates[i][1],
                    (this.coordinates[j][0] + 1024), this.coordinates[j][1]);
            drawLine((this.coordinates[i][0] - 1024), this.coordinates[i][1],
                    this.coordinates[j][0], this.coordinates[j][1]);
        } else {
            drawLine(this.coordinates[i][0], this.coordinates[i][1],
                    this.coordinates[j][0], this.coordinates[j][1]);
        }
    }

    /**
     * This function is to get the background map.
     */
    public String getBackground() {
        this.backgroundMap();
        this.map = "<svg width=\"1024\" height=\"512\" xmlns=\"http://www.w3.org/2000/svg\" "
                + "xmlns:svg=\"http://www.w3.org/2000/svg\">"
                + "<g>"
                + this.background
                + "</g>"
                + "</svg>";
        return this.map;
    }

    /**
     * This function is to get the complete map.
     */
    public String getMap() {
        this.AddLines();
        this.map = "<svg width=\"1024\" height=\"512\" xmlns=\"http://www.w3.org/2000/svg\" "
                + "xmlns:svg=\"http://www.w3.org/2000/svg\">"
                + "<g>"
                + this.background
                + this.lines
                + "</g>"
                + "</svg>";
        return this.map;
    }

    /**
     * Gets the LineDistance object.
     *
     * @return String.
     */
    public static String getDefaultMap() {
        LineDistance worldMap = new LineDistance();
        worldMap.getBackground();
        Gson gson = new Gson();
        return gson.toJson(worldMap);
    }
}
