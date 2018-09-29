package com.tripco.t03.planner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

public class TestLineDistance {

    private LineDistance ld;
    private ArrayList<Place> places;
    private ArrayList<Double> expectedXY;
    @Before
    public void setUp(){
        places = new ArrayList<>();
        expectedXY = new ArrayList<>();
        places.add(new Place("orig", "Montrose", 38.478321, -107.876175));
        places.add(new Place("dest1", "Durango ", 37.270500, -107.878700));
        places.add(new Place("dest2", "Denver ", 39.742043, -104.991531));
        places.add(new Place("dest3", "Fairplay ", 39.2247125, -106.00196180));
        places.add(new Place("dest4", "Fort Collins ", 40.585258, -105.084419));
        places.add(new Place("dest5", "Four Corners ", 36.9927152491, -109.040));
        places.add(new Place("dest6", "Pueblo", 38.276463, -104.604607));
        places.add(new Place("dest7", "3 Corners CO/UT/WY ", 41.000659, -109.050075));
        expectedXY.add(202.466230); expectedXY.add(483.966416); expectedXY.add(202.107547); expectedXY.add(697.954553);
        expectedXY.add(612.033001); expectedXY.add(260.134208); expectedXY.add(468.871225); expectedXY.add(351.729167);
        expectedXY.add(598.872267); expectedXY.add(110.810857); expectedXY.add(37.141630); expectedXY.add(747.169330);
        expectedXY.add(667.201625); expectedXY.add(519.671000); expectedXY.add(37.002659); expectedXY.add(37.086158);
    }

    @Test
    public void testLD(){
        ld = new LineDistance(places);

        assertEquals(places, ld.places);
    }

    private boolean equals(ArrayList<Double> arr2){
        for(int i = 0; i < this.expectedXY.size(); i++) {
            if (Math.abs(this.expectedXY.get(i) - arr2.get(i)) > 1.0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testGetXY(){
        ld = new LineDistance(places);
        ArrayList<Double> result = ld.getCoordinates();

        Assert.assertTrue(equals(result));
    }
}
