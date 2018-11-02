package com.tripco.t03.planner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class TestNearestNeighbor {
    private Integer[][] distanceGrid = {{0, 115, 32, 99, 666, 202, 13},
                                        {1500, 0, 55, 600, 22, 8, 19},
                                        {101, 22, 0, 66, 9, 12, 88},
                                        {22, 150, 66, 0, 902, 33, 12},
                                        {2520, 999, 666, 333, 0, 1, 3},
                                        {33, 66, 99, 88, 77, 0, 66},
                                        {55, 44, 33, 22, 11, 13, 0}};
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

        Assert.assertTrue(temp[0] != null);
    }

    @Test
    public void testGetTotalDistance() {
        NearestNeighbor nn = new NearestNeighbor(sortedPlaces, distanceGrid);
        nn.nearestNeighbor();
        int distance = nn.getTotalDistance();

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
    public void testGetLegDistance(){
         NearestNeighbor nn = new NearestNeighbor(sortedPlaces, distanceGrid);
         nn.nearestNeighbor();
         Integer[] legDistances = new Integer[7];
                 nn.getLegDistances(legDistances);
         Integer[] expected = {1, 33, 13, 22, 66, 22, 22};

         Assert.assertEquals(Arrays.toString(legDistances), Arrays.toString(expected));
     }

}
