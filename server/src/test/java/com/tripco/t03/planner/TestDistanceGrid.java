package com.tripco.t03.planner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestDistanceGrid {

    private DistanceGrid distGrid;
    private Place[] locations;

    @Before
    public void setUp(){
        Place one = new Place("01","one", 12.989898, 102.656565);
        Place two = new Place("02", "two", -55.656363, 101.555555);
        Place three = new Place("03", "three", -99.202020, -20.020202);
        Place four = new Place("04", "four", 5.353535, -120.969696);
        locations = new Place[4];
        locations[0] = one; locations[1] = two; locations[2] = three; locations[3] = four;
    }

    @Test
    public void testDefaultConstructor(){
        distGrid = new DistanceGrid();

        Assert.assertNull(distGrid.distanceGrid);
    }

    @Test
    public void testConstructor(){
        distGrid = new DistanceGrid(locations, "miles");

        Assert.assertNotNull(distGrid);
    }

    @Test
    public void testUserDefinedConstructor(){
        distGrid = new DistanceGrid(locations, "user defined", "ugh", 92.0000);
        DistanceGrid notEqual = new DistanceGrid(locations, "nautical miles");

        Assert.assertNotEquals(distGrid, notEqual);
    }

    @Test
    public void testBuildGrid(){
        distGrid = new DistanceGrid(locations, "miles");
        distGrid.buildGrid(0,0);

        Assert.assertNotNull(distGrid.distanceGrid[0][1]);
    }

    @Test
    public void testUserDefinedBuildGrid(){
        distGrid = new DistanceGrid(locations, "user defined", "ugh", 92.0000);
        distGrid.buildGrid(0,0);

        Assert.assertNotNull(distGrid.distanceGrid[0][1]);
    }
}
