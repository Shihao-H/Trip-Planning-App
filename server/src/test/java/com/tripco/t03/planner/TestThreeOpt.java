package com.tripco.t03.planner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        opt = new ThreeOpt(arr, grid);
        opt.reverseArray(opt.index);
        int[] arr2 = new int[]{3, 2, 1, 0};
        Assert.assertArrayEquals(arr2, opt.index);
    }

    @Test
    public void testSection(){
        opt = new ThreeOpt(arr, grid);
        int [] arr4=opt.section(0,2,opt.index);
        int [] arr5=new int[]{0,1,2};
        Assert.assertArrayEquals(arr4, arr5);
    }

    @Test
    public void testReplace(){
        opt = new ThreeOpt(arr, grid);
        int [] arr4=new int[]{1,2,3,4};
        int [] arr5=new int[]{1,1};
        int [] arr6=new int[]{1,1,3,4};
        opt.replace(0,1,arr5,arr4);
        Assert.assertArrayEquals(arr4, arr6);
    }

    @Test
    public void testCombine(){
        opt = new ThreeOpt(arr, grid);
        int [] arr4=new int[]{1,2,3,4};
        int [] arr5=new int[]{1,1};
        int [] arr6=new int[]{1,2,3,4,1,1};
        int [] arr7=opt.combine(arr4,arr5);
        Assert.assertArrayEquals(arr6, arr7);
    }

    @Test
    public void testopt3Case1(){
        opt = new ThreeOpt(arr, grid);
        int [] arr6=new int[]{1,2,3,4,5,6};
        int [] arr7=new int[]{1,5,4,2,3,6};
        opt.opt3Case1(0,2,4, arr6);
        Assert.assertArrayEquals(arr6, arr7);
    }
}
