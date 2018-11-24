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
        boolean[] arr2 = new boolean[]{true,true,true,true};
        bool1=Uti.unvisitedCityLeft(arr2);
        bool2=false;
        Assert.assertEquals(bool1, bool2);
    }

    @Test
    public void testgetMin(){
        Uti = new Utility();
        long[] arr = new long[]{20,30,10,40};
        boolean[] arr2 = new boolean[]{true,false,false,true};
        long[] arr3 = new long[]{0,0,0,0};
        int k=0;
        int g=Uti.getMin(arr,arr2,arr3,k);
        int z=2;
        Assert.assertEquals(g,z);
    }

    @Test
    public void testStartNear(){
        Uti = new Utility();
        int[] arr3 = Uti.StartNear(0,grid,4);
        int[] arr4 = new int[]{0,3,2,1};
        Assert.assertArrayEquals(arr3, arr4);
    }
}
