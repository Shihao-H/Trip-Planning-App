package com.tripco.t03.planner;

import java.io.*;
import java.util.ArrayList;

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

    /**
     * Constructor.
     *
     * @param places ArrayList of places.
     */
    public LineDistance(ArrayList<Place> places) {
        this.coordinates = new int[places.size()][2];
        for (int i = 0; i < places.size(); i++) {
            Place placeOut = places.get(i);
            this.coordinates[i][0] = (int) ((placeOut.longitude - left) / (right - left) * (width - 0.0) + 0.0); //x
            this.coordinates[i][1] = (int) ((placeOut.latitude - top) / (bottom - top) * (height - 0.0) + 0.0); //y
        }
    }

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


    private void AddLines() {
        this.backgroundMap();
        this.lines = "<g id=\"l2\">";
        this.lines += "<svg id=\"lines\" height=\"512\" width=\"1024\" y=\"0\" x=\"0\" " +
                "xmlns:svg=\"http://www.w3.org/2000/svg\" xmlns=\"http://www.w3.org/2000/svg\">" +
                "<title>lines</title>";
        for (int i = 0; i < this.coordinates.length; i++) {
            if (i != this.coordinates.length - 1) {
                if (this.coordinates[i][0] - this.coordinates[i + 1][0] < -180) {
                    this.lines += "<polyline points=\""
                            + (this.coordinates[i][0] + 360) + "," + this.coordinates[i][1] + " "
                            + this.coordinates[i + 1][0] + "," + this.coordinates[i + 1][1] + " "
                            + "fill=\"none\" stroke-width=\"4\" stroke=\"#203060\""
                            + "<polyline points=\""
                            + this.coordinates[i][0] + "," + this.coordinates[i][1] + " "
                            + (this.coordinates[i + 1][0] - 360) + "," + this.coordinates[i + 1][1] + " "
                            + "fill=\"none\" stroke-width=\"4\" stroke=\"#203060\"";
                } else if (this.coordinates[i][0] - this.coordinates[i + 1][0] > 180) {
                    this.lines += "<polyline points=\""
                            + this.coordinates[i][0] + "," + this.coordinates[i][1] + " "
                            + (this.coordinates[i + 1][0] + 360) + "," + this.coordinates[i + 1][1] + " "
                            + "fill=\"none\" stroke-width=\"4\" stroke=\"#203060\""
                            + "<polyline points=\""
                            + (this.coordinates[i][0] - 360) + "," + this.coordinates[i][1] + " "
                            + this.coordinates[i + 1][0] + "," + this.coordinates[i + 1][1] + " "
                            + "fill=\"none\" stroke-width=\"4\" stroke=\"#203060\"";
                }
            }


            if (i != 0 && i != this.coordinates.length) {
                if (this.coordinates[i][0] - this.coordinates[i - 1][0] < -180) {
                    this.lines += this.coordinates[i][0] + "," + this.coordinates[i][1] + " ";
                    continue;
                } else if (this.coordinates[i][0] - this.coordinates[i - 1][0] > 180) {
                    this.lines += this.coordinates[i][0] + "," + this.coordinates[i][1] + " ";
                    continue;
                }
            } else if (i == this.coordinates.length - 1) {

            }
            this.lines += this.coordinates[i][0] + "," + this.coordinates[i][1] + " ";
        }
        this.lines += "fill=\"none\" stroke-width=\"4\" stroke=\"#203060\" id=\"route\"/>";
        this.lines += "</svg>";
        this.lines += "</g>";
    }

    public String getBackground() {
        this.backgroundMap();
        this.map = "<svg width=\"1024\" height=\"512\" xmlns=\"http://www.w3.org/2000/svg\" " +
                "xmlns:svg=\"http://www.w3.org/2000/svg\">" +
                "<g>" +
                this.background +
                "</g>" +
                "</svg>";
        return this.map;
    }

    public String getMap() {
        this.AddLines();
        this.map = "<svg width=\"1024\" height=\"512\" xmlns=\"http://www.w3.org/2000/svg\" " +
                "xmlns:svg=\"http://www.w3.org/2000/svg\">" +
                "<g>" +
                this.background +
                this.lines +
                "</g>" +
                "</svg>";
        return this.map;
    }

}
