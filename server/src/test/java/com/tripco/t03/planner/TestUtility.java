package com.tripco.t03.planner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestUtility {
    private Utility Uti;
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
        Uti = new Utility();
        Assert.assertNotNull(Uti);
    }

    @Test
    public void test2optReverse(){
        Uti = new Utility();
        int [] arr3=Uti.opt2Reverse(arr,1,3);
        int[] arr2 = new int[]{0, 3, 2, 1};
        Assert.assertArrayEquals(arr2, arr3);
    }

    @Test
    public void testfindDis(){
        Uti = new Utility();
        long ori=Uti.findDis(arr,grid);
        long fi=18L;
        Assert.assertEquals(ori,fi);
    }
    @Test
    public void testunvisitedcityleft(){
        Uti = new Utility();
        boolean[] arr = new boolean[]{true,true,false,true};
        boolean bool1=Uti.unvisitedCityLeft(arr);
        boolean bool2=true;
        Assert.assertEquals(bool1, bool2);
    }

}
