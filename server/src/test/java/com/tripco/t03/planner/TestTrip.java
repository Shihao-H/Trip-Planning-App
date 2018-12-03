package com.tripco.t03.planner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


@RunWith(JUnit4.class)
public class TestTrip {

  private Trip trip;
  private Option opt;
  private Option userDefined;
  private Option withOptimize;
  private ArrayList<Place> places;
  private ArrayList<Place> places2;
  private String title;
  private ArrayList<Long> distances;

    // Setup to be done before every test in TestPlan
    @Before
    public void initialize() {
        opt = new Option("miles");
        userDefined = new Option("user defined", "yards", 3959.0);
        withOptimize = new Option("kilometers", "short");
        places = new ArrayList<>();
        places.add(new Place("P1", "one", 41.000155556, -109.05));
        places.add(new Place("P2", "two", 41.00055556, -102.05166667));
        places.add(new Place("P3", "three", 18.0, -104.0));
        places.add(new Place("P4", "four", 39.0, 116.0));
        distances = new ArrayList<>();
        distances.add(0L);
        distances.add(0L);
        distances.add(0L);
        distances.add(0L);
        title = "poop";
    }

    @Test
    public void testNullConstructor(){
        trip = new Trip();
        
        assertEquals(trip.options, null);
    }
    
    @Test
    public void testConstructor3(){
        trip = new Trip(title, opt, places, distances);
        
        assertEquals(trip.places.size(), trip.distances.size());
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
  public void testDistances() throws Exception {
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
  public void testUserDefined() throws Exception {
      places = new ArrayList<>();
      places.add(new Place("P01", "one", 41.000155556, -109.05));
      places.add(new Place("P02", "two", 41.00055556, -102.05166667));
      places.add(new Place("P03", "three", 18.0, -104.0));
      places.add(new Place("P04", "four", 39.0, 116.0));
      trip = new Trip(userDefined, places);

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
  
  @Test
    public void testWithOptimization() throws Exception {
        trip = new Trip(withOptimize, places);
        trip.plan();
        
        assertEquals(trip.distances.size(), trip.places.size());
  }
  
  @Test
    public void testWithKML() throws Exception {
        opt.map = "kml";
        trip = new Trip(opt, places);
        trip.plan();
        
        assertTrue(trip.map.contains("kml") || trip.map.contains("KML"));
  }
  
  @Test
    public void testSetPlace(){
        trip = new Trip(opt, places);
        int before = trip.places.size();
        Place[] newPlaces = new Place[1];
        trip.setPlace(newPlaces);
        
        assertNotEquals(trip.places.size(), before);
  }
  
  @Test
    public void testSetDistances(){
        trip = new Trip(opt, places);
        ArrayList<Long> dist = new ArrayList<>();
        dist.add(10L);
        trip.setDistances(dist);
        
        assertEquals(1, trip.distances.size());
  }

    @Test
    public void testfill(){
        trip = new Trip(opt, places);
        int[] arr=trip.fill();
        int []arr2=new int[]{0,1,2,3};
        Assert.assertArrayEquals(arr, arr2);
    }

    @Test
    public void testupdatelist(){
        trip = new Trip(opt, places);
        int[] arr=new int[]{0,1,3,2};
        trip.updateList(arr);
        places2 = new ArrayList<>();
        places2.add(new Place("P01", "one", 41.000155556, -109.05));
        places2.add(new Place("P02", "two", 41.00055556, -102.05166667));
        places2.add(new Place("P04", "four", 39.0, 116.0));
        places2.add(new Place("P03", "three", 18.0, -104.0));
        for(int i=0;i<4;i++)
        {
            assertTrue(places.get(i).equals(places.get(i)));
        }
    }
}



