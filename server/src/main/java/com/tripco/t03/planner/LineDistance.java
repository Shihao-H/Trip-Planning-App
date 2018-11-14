package com.tripco.t03.planner;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    private String kmlCoordinates="";

    private int[][] coordinates;
    private Double[][] places;

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
        this.places = new Double[places.size()][2];
        for (int i = 0; i < places.size(); i++) {
            Place placeOut = places.get(i);
            this.places[i][0] = placeOut.longitude;
            this.places[i][1] = placeOut.latitude;
            this.coordinates[i][0] = (int) ((placeOut.longitude - left)
                    / (right - left) * (width - 0.0) + 0.0); //x
            this.coordinates[i][1] = (int) ((placeOut.latitude - top)
                    / (bottom - top) * (height - 0.0) + 0.0); //y
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
    private void addLines() {
        this.backgroundMap();
        this.lines = "<g id=\"l2\">";
        this.lines += "<svg id=\"lines\" height=\"512\" width=\"1024\" y=\"0\" x=\"0\" "
                + "xmlns:svg=\"http://www.w3.org/2000/svg\" xmlns=\"http://www.w3.org/2000/svg\">"
                + "<title>lines</title>";
        for (int i = 0; i < this.coordinates.length; i++) {
            if (i != this.coordinates.length - 1) {
                check(this.places[i][0], this.places[i + 1][0], i, i + 1);
            } else {
                check(this.places[i][0], this.places[0][0], i, 0);
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
     * @param i1 integer origin's index.
     * @param i2 integer destination's index.
     */
    public void check(double x1, double x2, int i1, int i2) {
        if (x1 - x2 < -180.0) {
            drawLine((this.coordinates[i1][0] + 1024), this.coordinates[i1][1],
                    this.coordinates[i2][0], this.coordinates[i2][1]);
            drawLine(this.coordinates[i1][0], this.coordinates[i1][1],
                    (this.coordinates[i2][0] - 1024), this.coordinates[i2][1]);
        } else if (x1 - x2 > 180.0) {
            drawLine(this.coordinates[i1][0], this.coordinates[i1][1],
                    (this.coordinates[i2][0] + 1024), this.coordinates[i2][1]);
            drawLine((this.coordinates[i1][0] - 1024), this.coordinates[i1][1],
                    this.coordinates[i2][0], this.coordinates[i2][1]);
        } else {
            drawLine(this.coordinates[i1][0], this.coordinates[i1][1],
                    this.coordinates[i2][0], this.coordinates[i2][1]);
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
     * This function is to get the complete svg map.
     */
    public String getSvgMap() {
        this.addLines();
        this.map = "<svg width=\"1024\" height=\"512\" xmlns=\"http://www.w3.org/2000/svg\" "
                + "xmlns:svg=\"http://www.w3.org/2000/svg\">"
                + "<g>"
                + this.background
                + this.lines
                + "</g>"
                + "</svg>";
        return this.map;
    }

    public void getKmlCoordinates(){
        for (int i = 0; i < this.coordinates.length; i++) {
            if (i != this.coordinates.length - 1) {
                this.kmlCoordinates += this.places[i][0] + "," + this.places[i][1] + " "
                        + this.places[i + 1][0] + "," + this.places[i+1][1] + "\n";
            } else {
                this.kmlCoordinates += this.places[i][0] + "," + this.places[i][1] + " "
                        + this.places[0][0] + "," + this.places[0][1] + "\n";
            }
        }
    }

    /**
     * This function is to get the complete kml map.
     */
    public String getKmlMap() {
        System.out.println("come here!");
        getKmlCoordinates();
        this.map =
               "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"
                    +   "  <Document>\n"
                     +  "    <name>Paths</name>\n"
                      + "    <Style id=\"yellowLineGreenPoly\">\n"
                       +"      <LineStyle>\n"
                       +"        <color>7f00ffff</color>\n"
                       +"        <width>4</width>\n"
                       +"      </LineStyle>\n"
                       +"      <PolyStyle>\n"
                       +"        <color>7f00ff00</color>\n"
                       +"      </PolyStyle>\n"
                       +"    </Style>\n"
                       +"    <Placemark>\n"
                       +"      <name>Absolute Extruded</name>\n"
                       +"      <LineString>\n"
                       +"        <extrude>1</extrude>\n"
                       +"        <tessellate>1</tessellate>\n"
                       +"        <altitudeMode>absolute</altitudeMode>\n"
                       +"        <coordinates> "
                                   + kmlCoordinates
                       +"        </coordinates>\n"
                       +"      </LineString>\n"
                       +"    </Placemark>\n"
                       +"  </Document>\n"
                       +"</kml>";
        System.out.println("kml:\n" + this.map);
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
