package com.tripco.t03.planner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class TestNearestNeighbor {
    private Long[][] distanceGrid = {{0L, 115L, 32L, 99L, 666L, 202L, 13L},
                                        {1500L, 0L, 55L, 600L, 22L, 8L, 19L},
                                        {101L, 22L, 0L, 66L, 9L, 12L, 88L},
                                        {22L, 150L, 66L, 0L, 902L, 33L, 12L},
                                        {2520L, 999L, 666L, 333L, 0L, 1L, 3L},
                                        {33L, 66L, 99L, 88L, 77L, 0L, 66L},
                                        {55L, 44L, 33L, 22L, 11L, 13L, 0L}};
    private Integer[] sortedPlaces;

    @Before
    public void setup(){
        sortedPlaces = new Integer[7];
        for(int i = 0; i < sortedPlaces.length; i++){
            sortedPlaces[i] = i;
        }
    }

    @Test
    public void testNearestNeighborConstructor(){
        NearestNeighbor nn = new NearestNeighbor(sortedPlaces, distanceGrid);

        Assert.assertNotNull(nn);
    }

    @Test
    public void testNearestNeighborMethod(){
        NearestNeighbor nn = new NearestNeighbor(sortedPlaces, distanceGrid);
        nn.nearestNeighbor();
        Integer[] temp = new Integer[7];
        nn.getOptimalTrip(temp);
    
        Assert.assertNotNull(temp[0]);
    }

    @Test
    public void testGetTotalDistance() {
        NearestNeighbor nn = new NearestNeighbor(sortedPlaces, distanceGrid);
        nn.nearestNeighbor();
        long distance = nn.getTotalDistance();

        Assert.assertEquals(179, distance);
    }

    @Test
    public void testGetOptimalTrip(){
        Integer[] trip = new Integer[7];
        Integer[] expected = {4, 5, 0, 6, 3, 2, 1};
        NearestNeighbor nn = new NearestNeighbor(sortedPlaces, distanceGrid);
        nn.nearestNeighbor();
        nn.getOptimalTrip(trip);

        Assert.assertEquals(Arrays.toString(trip), Arrays.toString(expected));
    }
    
     @Test
    public void testGetLegDistance() {
         NearestNeighbor nn = new NearestNeighbor(sortedPlaces, distanceGrid);
         nn.nearestNeighbor();
         Long[] legDistances = new Long[7];
         nn.getLegDistances(legDistances);
         Integer[] expected = {1, 33, 13, 22, 66, 22, 22};
    
         Assert.assertEquals(Arrays.toString(legDistances), Arrays.toString(expected));
     }
}
