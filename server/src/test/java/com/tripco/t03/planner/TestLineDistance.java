package com.tripco.t03.planner;

import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestLineDistance {

    private LineDistance testLineDistance;
    private ArrayList<Place> placesPositive;
    private ArrayList<Place> placesNegative;
    private ArrayList<Place> placesDefault;
    private LineDistance ld;
    private ArrayList<Place> places;
    private String expectedMap;

    @Before
    public void setUp() {
        Place canada = new Place("Canada", "Big Rock Brewery", 50.984444, -113.954722);
        Place australia = new Place("Australia", "Nail Brewing Australia", -31.904894, 115.930847);
        Place unitedStates = new Place("United States", "Odell Brewing Company", 40.5894674, -105.0631819);

        placesPositive = new ArrayList<>();
        placesNegative = new ArrayList<>();
        placesDefault = new ArrayList<>();

        placesPositive.add(australia);
        placesPositive.add(canada);
        placesNegative.add(canada);
        placesNegative.add(australia);
        placesDefault.add(canada);
        placesDefault.add(unitedStates);
        places = new ArrayList<>();
        places.add(new Place("orig", "Montrose", 38.478321, -107.876175));
        places.add(new Place("dest1", "Durango ", 37.270500, -107.878700));
        places.add(new Place("dest2", "Denver ", 39.742043, -104.991531));
        places.add(new Place("dest3", "Fairplay ", 39.2247125, -106.00196180));
        places.add(new Place("dest4", "Fort Collins ", 40.585258, -105.084419));
        places.add(new Place("dest5", "Four Corners ", 36.9927152491, -109.040));
        places.add(new Place("dest6", "Pueblo", 38.276463, -104.604607));
        places.add(new Place("dest7", "3 Corners CO/UT/WY ", 41.000659, -109.050075));
        expectedMap =   "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"
                        + "  <Document>\n    <name>Paths</name>\n"
                        + "    <Style id=\"yellowLineGreenPoly\">\n"
                        + "      <LineStyle>\n        <color>7f00ffff</color>\n"
                        + "        <width>4</width>\n      </LineStyle>\n"
                        + "      <PolyStyle>\n        <color>7f00ff00</color>\n"
                        + "      </PolyStyle>\n    </Style>\n"
                        + "    <Placemark>\n      <name>Absolute Extruded</name>\n"
                        + "      <LineString>\n        <extrude>1</extrude>\n"
                        + "        <tessellate>1</tessellate>\n"
                        + "        <altitudeMode>absolute</altitudeMode>\n"
                        + "        <coordinates> -107.876175,38.478321 -107.8787,37.2705\n"
                        + "-107.8787,37.2705 -104.991531,39.742043\n"
                        + "-104.991531,39.742043 -106.0019618,39.2247125\n"
                        + "-106.0019618,39.2247125 -105.084419,40.585258\n"
                        + "-105.084419,40.585258 -109.04,36.9927152491\n"
                        + "-109.04,36.9927152491 -104.604607,38.276463\n"
                        + "-104.604607,38.276463 -109.050075,41.000659\n"
                        + "-109.050075,41.000659 -107.876175,38.478321\n"
                        + "        </coordinates>\n      </LineString>\n"
                        + "    </Placemark>\n  </Document>\n</kml>";
    
    }
    
    @Test
    public void testNullConstructor(){
        ld =new LineDistance();
        
        assertNull(ld.getCoordinates());
    }
    
    @Test
    public void testLD(){
        ld = new LineDistance(places);
        
        assertEquals(ld.getCoordinates().length, places.size());
    }
    
    @Test
    public void testGetMap(){
        ld = new LineDistance(places);
        ld.check(places.get(0).longitude, places.get(2).longitude, 0, 2);
        String result = ld.getKmlMap();
        
        assertEquals(expectedMap, result);
    }
    
    @Test
    public void testCheckPositive() {
        String expected = "<polyline points=\"841,346 1211,110\" "
                + "fill=\"none\" stroke-width=\"1.25\" stroke=\"#203060\" />"
                + "<polyline points=\"-183,346 187,110\" fill=\"none\" "
                + "stroke-width=\"1.25\" stroke=\"#203060\" />";
        testLineDistance = new LineDistance(placesPositive);
        testLineDistance.check(testLineDistance.places[0][0], testLineDistance.places[1][0], 0, 1);
        assertEquals(expected, testLineDistance.lines);
    }

    @Test
    public void testCheckNegative() {
        String expected = "<polyline points=\"1211,110 841,346\" "
                + "fill=\"none\" stroke-width=\"1.25\" stroke=\"#203060\" />"
                + "<polyline points=\"187,110 -183,346\" fill=\"none\" "
                + "stroke-width=\"1.25\" stroke=\"#203060\" />";
        testLineDistance = new LineDistance(placesNegative);
        testLineDistance.check(testLineDistance.places[0][0], testLineDistance.places[1][0], 0, 1);
        assertEquals(expected, testLineDistance.lines);
    }

    @Test
    public void testCheckDefault() {
        String expected = "<polyline points=\"187,110 213,140\" "
                + "fill=\"none\" stroke-width=\"1.25\" stroke=\"#203060\" />";
        testLineDistance = new LineDistance(placesDefault);
        testLineDistance.check(testLineDistance.places[0][0], testLineDistance.places[1][0], 0, 1);
        assertEquals(expected, testLineDistance.lines);
    }
    
    @Test
    public void testGetBackground() throws IOException {
        ld = new LineDistance(places);

        assertTrue(expectedMap.length() < ld.getBackground().length());
    }
    
    @Test
    public void testGetSVGMap() throws IOException {
        ld = new LineDistance(places);
        String section = "<svg width=\"1024\" height=\"512\" xmlns=\"http://www.w3.org/2000/svg\""
                         + " xmlns:svg=\"http://www.w3.org/2000/svg\"><g>";
        
        assertTrue(ld.getSvgMap().contains(section));
    }
    
    @Test
    public void testGetDefaultMap() throws IOException {
        JsonObject json =  new JsonObject();
        json.getAsJsonObject(LineDistance.getDefaultMap());
        
        assertNotNull(json);
    }
}
