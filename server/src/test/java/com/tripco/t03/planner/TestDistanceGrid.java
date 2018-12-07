package com.tripco.t03.planner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestDistanceGrid {

    private DistanceGrid distGrid;
    private Integer[] locations;
    private ArrayList<Place> origTrip;

    @Before
    public void setUp(){
        origTrip = new ArrayList<>();
        Place one = new Place("01","one", 12.989898, 102.656565);
        Place two = new Place("02", "two", -55.656363, 101.555555);
        Place three = new Place("03", "three", -99.202020, -20.020202);
        Place four = new Place("04", "four", 5.353535, -120.969696);
        locations = new Integer[4];
        locations[0] = 1; locations[1] = 3; locations[2] = 0; locations[3] = 2;
        origTrip.add(three);
        origTrip.add(one);
        origTrip.add(four);
        origTrip.add(two);
    }

    @Test
    public void testDefaultConstructor(){
        distGrid = new DistanceGrid();

        Assert.assertNull(distGrid.distanceGrid);
    }

    @Test
    public void testConstructor(){
        distGrid = new DistanceGrid(origTrip, "miles", locations);

        Assert.assertNotNull(distGrid);
    }

    @Test
    public void testUserDefinedConstructor(){
        distGrid = new DistanceGrid(origTrip, "user defined",  92.0000, locations);
        DistanceGrid notEqual = new DistanceGrid(origTrip, "nautical miles", locations);

        Assert.assertNotEquals(distGrid, notEqual);
    }

    @Test
    public void testBuildGrid() throws Exception {
        distGrid = new DistanceGrid(origTrip, "miles", locations);
        distGrid.buildGrid();

        Assert.assertNotNull(distGrid.distanceGrid[0][1]);
    }

    @Test
    public void testUserDefinedBuildGrid() throws Exception {
        distGrid = new DistanceGrid(origTrip, "user defined", 92.0000, locations);
        distGrid.buildGrid();

        Assert.assertNotNull(distGrid.distanceGrid[0][1]);
    }

    @Test
    public void testKiloBuildGrid() throws Exception {
        distGrid = new DistanceGrid(origTrip, "kilometers", locations);
        distGrid.buildGrid();

        Assert.assertNotNull(distGrid.distanceGrid[0][1]);
    }

    @Test
    public void testNautMilesBuildGrid() throws Exception {
        distGrid = new DistanceGrid(origTrip, "nautical miles", locations);
        distGrid.buildGrid();

        Assert.assertNotNull(distGrid.distanceGrid[0][1]);
    }

    @Test
    public void testInvalidBuildGrid() throws Exception {
        String message = null;
        try {
            distGrid = new DistanceGrid(origTrip, "invalid", locations);
            distGrid.buildGrid();
        } catch(Exception e){
            message = e.getMessage();
        }
        Assert.assertEquals(message, "No valid units");
    }
}
