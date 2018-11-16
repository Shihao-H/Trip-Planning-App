package com.tripco.t03.planner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class TestThreeOpt {

    private ThreeOpt opt;
    private int[] arr;
    private long[][] grid;


    @Before
    public void setup(){
        grid = new long[][]{{0L, 5L, 8L, 4L},
                {5L, 0L, 4L, 8L},
                {8L, 4L, 0L, 5L},
                {4L, 8L, 5L, 0L}};
        arr = new int[]{0, 1, 2, 3};
    }

    @Test
    public void testConstructor(){
        opt = new ThreeOpt(arr, grid);
        Assert.assertNotNull(opt);
    }

    @Test
    public void testReverse(){
        opt = new ThreeOpt(trip, distanceGrid);
        Integer[] result = new Integer[trip.length];
        opt.twoOpt(result);

        Assert.assertEquals(Arrays.toString(result), Arrays.toString(expected));
    }

    @Test
    public void testSection(){
        Integer[] reverseExpected = new Integer[]{3, 2, 1, 0};
        opt = new TwoOpt(trip, distanceGrid);
        opt.opt2Reverse2(0, 3, expected);

        Assert.assertEquals(Arrays.toString(expected), Arrays.toString(reverseExpected));
    }

}
