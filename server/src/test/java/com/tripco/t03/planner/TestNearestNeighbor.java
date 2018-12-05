package com.tripco.t03.planner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestNearestNeighbor {

    private NearestNeighbor opt;
    private int[] trip;
    public long[][] distanceGrid;
    public int len;
    public Utility tool;

    @Before
    public void setup(){
        distanceGrid = new long[][]{{0L, 115L, 32L, 99L, 666L, 202L, 13L},
                                    {1500L, 0L, 55L, 600L, 22L, 8L, 19L},
                                    {101L, 22L, 0L, 66L, 9L, 12L, 88L},
                                    {22L, 150L, 66L, 0L, 902L, 33L, 12L},
                                    {2520L, 999L, 666L, 333L, 0L, 1L, 3L},
                                    {33L, 66L, 99L, 88L, 77L, 0L, 66L},
                                    {55L, 44L, 33L, 22L, 11L, 13L, 0L}};
        trip = new int[]{0,1,2,3,4,5,6};
        this.len=trip.length;
        this.tool=new Utility();
    }
    
    @Test
    public void testNearestNeighborConstructor(){
        NearestNeighbor nn = new NearestNeighbor(trip, distanceGrid);
        Assert.assertNotNull(nn);
    }

    @Test
    public void testnear(){
        opt = new NearestNeighbor(trip, distanceGrid);
        int[] result = new int[]{4,5,0,6,3,2,1};
        opt.near(distanceGrid);
        Assert.assertArrayEquals(opt.index,result);
    }

    @Test
    public void testopt2DisEach() {
        opt = new NearestNeighbor(trip, distanceGrid);
        long distance = opt.nearDisEach(0,distanceGrid);
        Assert.assertEquals(distance, 234);
    }


}
    
