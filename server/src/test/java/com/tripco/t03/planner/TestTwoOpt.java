package com.tripco.t03.planner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class TestTwoOpt {
    
    private TwoOpt opt;
    private Integer[] trip;
    private Integer[] expected;
    private Long[][] distanceGrid;
    private Long[] expectedLeg;
    
    @Before
    public void setup(){
        trip = new Integer[]{0, 2, 1, 3};
        distanceGrid = new Long[][]{{0L, 5L, 8L, 4L},
                                       {5L, 0L, 4L, 8L},
                                       {8L, 4L, 0L, 5L},
                                       {4L, 8L, 5L, 0L}};
        expected = new Integer[]{0, 1, 2, 3};
        expectedLeg = new Long[]{5L, 4L, 5L, 4L};
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
    
        long distance = opt.getTotalDistance();
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
        Long[] result = new Long[trip.length];
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
