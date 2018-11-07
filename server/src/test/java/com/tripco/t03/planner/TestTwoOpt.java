package com.tripco.t03.planner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class TestTwoOpt {
    
    public TwoOpt opt;
    public Integer[] trip;
    public Integer[] expected;
    public Integer[][] distanceGrid;
    public int minDistance;
    public Integer[] expectedLeg;
    
    @Before
    public void setup(){
        trip = new Integer[]{0, 2, 1, 3};
        distanceGrid = new Integer[][]{{0, 5, 8, 4},
                                       {5, 0, 4, 8},
                                       {8, 4, 0, 5},
                                       {4, 8, 5, 0}};
        minDistance = 24;
        expected = new Integer[]{0, 1, 2, 3};
        expectedLeg = new Integer[]{5, 4, 5, 4};
    }
    
    @Test
    public void testConstructor(){
        opt = new TwoOpt(trip, distanceGrid);
    
        Assert.assertNotNull(opt);
    }
    
    @Test
    public void testTwoOpt(){
        opt = new TwoOpt(trip, distanceGrid);
        Integer[] result = new Integer[trip.length];
        opt.twoOpt(result);
    
        Assert.assertEquals(Arrays.toString(result), Arrays.toString(expected));
    }
    
    @Test
    public void testTwoOptReverse(){
        Integer[] reverseExpected = new Integer[]{3, 2, 1, 0};
        opt = new TwoOpt(trip, distanceGrid);
        opt.opt2Reverse2(0, 3, expected);
        
        Assert.assertEquals(Arrays.toString(expected), Arrays.toString(reverseExpected));
    }
    
    @Test
    public void testGetTotalDistance() {
        opt = new TwoOpt(trip, distanceGrid);
        Integer[] result = new Integer[trip.length];
        opt.twoOpt(result);
    
        int distance = opt.getTotalDistance();
        Assert.assertEquals(distance, 18);
    }
    
    @Test
    public void testGetSortedArray(){
        opt = new TwoOpt(trip, distanceGrid);
        Integer[] result = new Integer[trip.length];
        opt.getSortedIndices(result);
        
        Assert.assertEquals(Arrays.toString(result), Arrays.toString(trip));
    }
    
    @Test
    public void testTwoOptLegDistances(){
        opt = new TwoOpt(trip, distanceGrid);
        Integer[] result = new Integer[trip.length];
        opt.twoOpt(expected);
        opt.getTwoOptLegDistances(result);
        
        Assert.assertEquals(Arrays.toString(result), Arrays.toString(expectedLeg));
    }
    
    @Test
    public void testTwoOptNoChange(){
        opt = new TwoOpt(expected, distanceGrid);
        Integer[] result = new Integer[trip.length];
        opt.twoOpt(result);
        
        Assert.assertEquals(Arrays.toString(result), Arrays.toString(expected));
    }
}
