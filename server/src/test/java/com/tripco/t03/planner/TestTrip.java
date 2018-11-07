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
        places.add(new Place("P3", "three", 18.0, -104.0));
        places.add(new Place("P4", "four", 39.0, 116.0));
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
      ArrayList<Long> expectedDistances = new ArrayList<>();
      Collections.addAll(expectedDistances, 366L, 1593L, 7726L, 6225L);
      // Call the equals() method of the first object on the second object.
      trip.plan();
      long expTotalDist = 0;
      long totalDistance = 0;
      for(int i = 0; i< expectedDistances.size(); i++) {
                  expTotalDist = expTotalDist+ expectedDistances.get(i);
                  totalDistance = totalDistance + trip.distances.get(i);
      }
      assertEquals( expTotalDist, totalDistance, 5);
  }

  @Test
  public void testUserDefined() {
      opt =  new Option("user defined", "yards", 3959.0);
      places = new ArrayList<>();
      places.add(new Place("P01", "one", 41.000155556, -109.05));
      places.add(new Place("P02", "two", 41.00055556, -102.05166667));
      places.add(new Place("P03", "three", 18.0, -104.0));
      places.add(new Place("P04", "four", 39.0, 116.0));
      trip = new Trip(opt, places);

      trip.plan();

      ArrayList<Long> expectedDistances = new ArrayList<>();
      Collections.addAll(expectedDistances, 366L, 1593L, 7726L, 6225L);
      long expTotalDist = 0;
      long totalDistance = 0;
      for(int i = 0; i< expectedDistances.size(); i++) {
          expTotalDist = expTotalDist+ expectedDistances.get(i);
          totalDistance = totalDistance + trip.distances.get(i);
      }
      assertEquals( expTotalDist, totalDistance, 5);
  }
}

