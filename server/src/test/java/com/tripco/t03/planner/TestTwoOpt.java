
package com.tripco.t03.planner;

 import org.junit.Assert;
 import org.junit.Before;
 import org.junit.Test;

 public class TestTwoOpt {

     private TwoOpt opt;
     private int[] trip;
     private int[] expected;
     private long[][] distanceGrid;
     public int len;
     public Utility tool;

     @Before
     public void setup(){
         trip = new int[]{0, 1, 2, 3, 4};
         distanceGrid = new long[][]{{0L, 5L, 8L, 4L, 3L},
                                     {5L, 0L, 4L, 8L, 1L},
                                     {8L, 4L, 0L, 5L, 2L},
                                     {4L, 8L, 5L, 0L, 2L},
                                     {3L, 1L, 2L, 2L, 0L}};
         expected = new int[]{0, 1, 2, 3, 4};
         this.len=trip.length;
         this.tool=new Utility();
     }

     @Test
     public void testConstructor(){
         opt = new TwoOpt(trip, distanceGrid);
         Assert.assertNotNull(opt);
     }

     @Test
     public void testtwoOpt(){
         opt = new TwoOpt(trip, distanceGrid);
         int[] result = new int[]{0,4,1,2,3};
         opt.twoOpt(distanceGrid);
         Assert.assertArrayEquals(opt.index,result);
     }

     @Test
     public void testopt2DisEach() {
         opt = new TwoOpt(trip, distanceGrid);
         long distance = opt.opt2DisEach(0,distanceGrid);
         Assert.assertEquals(distance, 17);
     }

}
