package com.tripco.t03.planner;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/*
  This class contains tests for the Trip class.
 */
@RunWith(JUnit4.class)
public class TestPlace {
  
//  Place p = new Place();
//
//  @Test
//  public void testEmptyPlace() {
//    assertNull(p.id);
//    assertNull(p.name);
//    assertEquals(p.latitude, '\0', 1);
//    assertEquals(p.longitude, '\0', 1);
//  }
  
  Place place;

  // Setup to be done before every test in TestPlan
  @Before
  public void initialize() {
    place = new Place("dnvr", "Denver", 1.0, 0.0);
  }

//  @Test
//  public void testPlace() {
//    Place testPlace = new Place("dnvr", "Denver", 1.0, 0.0);
//    assertTrue(testPlace.equals(place));
//  }
  
  @Test
  public void testGetName() {
    assertEquals(place.getName(), "Denver");
  }

  @Test
  public void testGetLat() {
    assertEquals(place.getLatitude(), 1.0, 1);
  }

  @Test
  public void testGetLong() {
    assertEquals(place.getLongitude(), 0.0, 1);
  }

//  @Test
//  public void testToString() {
//    assertEquals(place.toString(), "Id: dnvr, Name: Denver, Latitude: 1.000000, Longitude: 0.000000");
//  }
}
