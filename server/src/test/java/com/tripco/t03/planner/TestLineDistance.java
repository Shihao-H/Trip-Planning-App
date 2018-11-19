package com.tripco.t03.planner;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class TestLineDistance {

    private LineDistance testLineDistance;
    private ArrayList<Place> placesPositive;
    private ArrayList<Place> placesNegative;
    private ArrayList<Place> placesDefault;

    private String lines;

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
    }

    @Test
    public void testCheckPositive() {
        String expected = "<polyline points=\"841,346 1211,110\" "
                + "fill=\"none\" stroke-width=\"4\" stroke=\"#203060\" />"
                + "<polyline points=\"-183,346 187,110\" fill=\"none\" "
                + "stroke-width=\"4\" stroke=\"#203060\" />";
        testLineDistance = new LineDistance(placesPositive);
        testLineDistance.check(testLineDistance.places[0][0], testLineDistance.places[1][0], 0, 1);
        assertEquals(expected, testLineDistance.lines);
    }

    @Test
    public void testCheckNegative() {
        String expected = "<polyline points=\"1211,110 841,346\" "
                + "fill=\"none\" stroke-width=\"4\" stroke=\"#203060\" />"
                + "<polyline points=\"187,110 -183,346\" fill=\"none\" "
                + "stroke-width=\"4\" stroke=\"#203060\" />";
        testLineDistance = new LineDistance(placesNegative);
        testLineDistance.check(testLineDistance.places[0][0], testLineDistance.places[1][0], 0, 1);
        assertEquals(expected, testLineDistance.lines);
    }

    @Test
    public void testCheckDefault() {
        String expected = "<polyline points=\"187,110 213,140\" "
                + "fill=\"none\" stroke-width=\"4\" stroke=\"#203060\" />";
        testLineDistance = new LineDistance(placesDefault);
        testLineDistance.check(testLineDistance.places[0][0], testLineDistance.places[1][0], 0, 1);
        assertEquals(expected, testLineDistance.lines);
    }
}
