package com.tripco.t03.planner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collections;
import static org.junit.Assert.*;

/*
  This class contains tests for the Trip class.
 */
@RunWith(JUnit4.class)
public class TestTrip {

  public Trip emptyTrip = new Trip();

  @Test
  public void testEmptyTrip() {
      assertNull(emptyTrip.title = null);
      assertNull(emptyTrip.options.units = null);
      assertNull(emptyTrip.options.unitName = null);
      assertNull(emptyTrip.places = null);
      assertNull(emptyTrip.distances = null);
      assertNull(emptyTrip.map = null);
  }

  public Trip tripTitle;

  @Test
  public void testTitleTrip() {
      Option test_opt = new Option("miles");
      ArrayList<Place> testPlaces = new ArrayList<>();
      testPlaces.add(new Place("P1", "one", 41.000155556, -109.05));

      tripTitle = new Trip("title", test_opt, testPlaces);
      assertEquals(tripTitle.title, "title");
      assertEquals(tripTitle.options.units, "miles");
      assertNotNull(tripTitle.places);
  }

  Trip trip;
  Option opt;
  ArrayList<Place> places;

  // Setup to be done before every test in TestPlan
  @Before
  public void initialize() {
    opt = new Option("miles");
    places = new ArrayList<>();
    places.add(new Place("P1", "one", 41.000155556, -109.05));
    places.add(new Place("P2", "two", 41.00055556, -102.05166667));
    places.add(new Place("P3", "three", 18, -104));
    places.add(new Place("P4", "four", 39, 116));
  }

  @Test
  public void testTrue() {
    // assertTrue checks if a statement is true
    assertTrue(true);
  }

  @Test
  public void testDistances() {
      trip = new Trip(opt, places);
      ArrayList<Integer> expectedDistances = new ArrayList<>();
      Collections.addAll(expectedDistances, 366, 1593, 7726, 6225);
      // Call the equals() method of the first object on the second object.
      trip.plan();
      for(int i = 0; i< expectedDistances.size(); i++) {
          assertEquals(expectedDistances.get(i), trip.distances.get(i), 1);
      }
  }

  @Test
  public void testMap() {
      trip = new Trip(opt, places);
      trip.plan();
      assertEquals(trip.map,"<svg width=\"1920\" height=\"960\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:svg=\"http://www.w3.org/2000/svg\"><!-- Created with SVG-edit - http://svg-edit.googlecode.com/ --> <g> <g id=\"svg_4\"> <svg id=\"svg_1\" height=\"960\" width=\"1920\" xmlns:svg=\"http://www.w3.org/2000/svg\" xmlns=\"http://www.w3.org/2000/svg\"> <g id=\"svg_2\"> <title>Layer 1</title> <rect fill=\"rgb(119, 204, 119)\" stroke=\"black\" x=\"0\" y=\"0\" width=\"1920\" height=\"960\" id=\"svg_3\"/> </g> </svg> </g> <g id=\"svg_9\"> <svg id=\"svg_5\" height=\"480\" width=\"960\" y=\"240\" x=\"480\" xmlns:svg=\"http://www.w3.org/2000/svg\" xmlns=\"http://www.w3.org/2000/svg\"> <g id=\"svg_6\"> <title>Layer 2</title> <polygon points=\"0,0 960,0 960,480 0,480\" stroke-width=\"12\" stroke=\"brown\" fill=\"none\" id=\"svg_8\"/> <polyline points=\"0,0 960,480 480,0 0,480 960,0 480,480 0,0\" fill=\"none\" stroke-width=\"4\" stroke=\"blue\" id=\"svg_7\"/> </g> </svg> </g> </g> </svg>");
  }
}

