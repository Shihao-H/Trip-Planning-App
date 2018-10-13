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

  private Trip trip;
  private Option opt = new Option("user defined", "yards", 3959.0);
  private ArrayList<Place> places;

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
  public void testTitleTrip() {
      opt = new Option("miles");
      places = new ArrayList<>();
      places.add(new Place("P1", "one", 41.000155556, -109.05));
      trip = new Trip("title", opt, places);

      assertEquals(trip.title, "title");
  }

  @Test
  public void testDistances() {
      trip = new Trip(opt, places);
      ArrayList<Integer> expectedDistances = new ArrayList<>();
      Collections.addAll(expectedDistances, 366, 1593, 7726, 6225);
      // Call the equals() method of the first object on the second object.
      trip.plan();
      int expTotalDist = 0;
      int totalDistance = 0;
      for(int i = 0; i< expectedDistances.size(); i++) {
                  expTotalDist = expTotalDist+ expectedDistances.get(i);
                  totalDistance = totalDistance + trip.distances.get(i);
      }
      assertEquals( expTotalDist, totalDistance, 5);
  }

 /** @Test
  public void testMap() {
      trip = new Trip(opt, places);
      trip.plan();
      String map = "<svg width=\"1920\" height=\"960\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:svg=\"http://www.w3.org/2000/svg\"><!-- Created with SVG-edit - http://svg-edit.googlecode.com/ --> <g> <g id=\"svg_4\"> <svg id=\"svg_1\" height=\"960\" width=\"1920\" xmlns:svg=\"http://www.w3.org/2000/svg\" xmlns=\"http://www.w3.org/2000/svg\"> <g id=\"svg_2\"> <title>Layer 1</title> <rect fill=\"rgb(119, 204, 119)\" stroke=\"black\" x=\"0\" y=\"0\" width=\"1920\" height=\"960\" id=\"svg_3\"/> </g> </svg> </g> <g id=\"svg_9\"> <svg id=\"svg_5\" height=\"480\" width=\"960\" y=\"240\" x=\"480\" xmlns:svg=\"http://www.w3.org/2000/svg\" xmlns=\"http://www.w3.org/2000/svg\"> <g id=\"svg_6\"> <title>Layer 2</title> <polygon points=\"0,0 960,0 960,480 0,480\" stroke-width=\"12\" stroke=\"brown\" fill=\"none\" id=\"svg_8\"/> <polyline points=\"0,0 960,480 480,0 0,480 960,0 480,480 0,0\" fill=\"none\" stroke-width=\"4\" stroke=\"blue\" id=\"svg_7\"/> <path id=\"routeGoesHere\"> d=\"M 37.013285,37.176140 L 1028.564445,37.266597 L 753.087742,4110.391510 L 32004.703742,391.541200 z\" style=\"fill:none;fill-rule:evenodd;stroke:red;stroke-width:2.5\" </path></g> </svg> </g> </g> </svg>";


      assertEquals( map, trip.map);
  }*/

  @Test
  public void testUserDefined() {
      opt =  new Option("user defined", "yards", 3959.0);
      places = new ArrayList<>();
      places.add(new Place("P01", "one", 41.000155556, -109.05));
      places.add(new Place("P02", "two", 41.00055556, -102.05166667));
      places.add(new Place("P03", "three", 18, -104));
      places.add(new Place("P04", "four", 39, 116));
      trip = new Trip(opt, places);

      trip.plan();

      ArrayList<Integer> expectedDistances = new ArrayList<>();
      Collections.addAll(expectedDistances, 366, 1593, 7726, 6225);
      int expTotalDist = 0;
      int totalDistance = 0;
      for(int i = 0; i< expectedDistances.size(); i++) {
          expTotalDist = expTotalDist+ expectedDistances.get(i);
          totalDistance = totalDistance + trip.distances.get(i);
      }
      assertEquals( expTotalDist, totalDistance, 5);
  }
}

