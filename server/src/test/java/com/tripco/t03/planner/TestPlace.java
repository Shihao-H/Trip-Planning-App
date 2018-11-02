package com.tripco.t03.planner;

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

  Place place;

  // Setup to be done before every test in TestPlan
  @Before
  public void initialize() {
    place = new Place("dnvr", "Denver", 1.0, 0.0);
  }

  @Test
  public void testEmptyPlace() {
    Place p = new Place();

    assertNull(p.name);
  }

  @Test
  public void testPlace() {
    Place testPlace = new Place("dnvr", "Denver", 1.0, 0.0);
    assertTrue(testPlace.equals(place));
  }
  
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

  @Test
  public void testSetAttributeType() {
    place.setAttributeType("tired");

    assertNotEquals(place.type, "and old");
  }

  @Test
  public void testSetAttributeElevation() {
    place.setAttributeElevation("too high");

    assertNotNull(place.elevation);
  }

  @Test
  public void testSetAttributeContinent() {
    place.setAttributeContinent("not here");

    assertFalse(place.continent.equals("here"));
  }

  @Test
  public void testSetAttributeCountry(){
    place.setAttributeCountry("This class is sucking my soul out");

    assertTrue(place.country.length() == 33);
  }

  @Test
  public void testSetAttributeRegion(){
    place.setAttributeRegion("Do I get my diploma after this?");

    assertFalse(place.region.equals("yes"));
  }

  @Test
  public void testSetAttributeMunicipality(){
    place.setAttributeMunicipality("Too old for this");

    assertNotNull(place.municipality);
  }

  @Test
  public void testToString() {
    assertEquals(place.toString(), "Id: dnvr, Name: Denver, Latitude: 1.000000, Longitude: 0.000000");
  }
}
