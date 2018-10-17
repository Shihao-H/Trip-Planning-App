package com.tripco.t03.planner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

public class TestLineDistance {

    private LineDistance ld;
    private ArrayList<Place> places;
    private String expectedXY;
    @Before
    public void setUp(){
        places = new ArrayList<>();
        places.add(new Place("orig", "Montrose", 38.478321, -107.876175));
        places.add(new Place("dest1", "Durango ", 37.270500, -107.878700));
        places.add(new Place("dest2", "Denver ", 39.742043, -104.991531));
        places.add(new Place("dest3", "Fairplay ", 39.2247125, -106.00196180));
        places.add(new Place("dest4", "Fort Collins ", 40.585258, -105.084419));
        places.add(new Place("dest5", "Four Corners ", 36.9927152491, -109.040));
        places.add(new Place("dest6", "Pueblo", 38.276463, -104.604607));
        places.add(new Place("dest7", "3 Corners CO/UT/WY ", 41.000659, -109.050075));
        expectedXY = " d=\"M 202.466230,483.966416 L 202.107547,697.954553 L 612.033001,260.134208 L 468.871225,351.729167 " +
                "L 598.872267,110.810857 L 37.141630,747.169330 L 667.201625,519.671000 L 37.002659,37.086946 z\"" +
                " style=\"fill:none;fill-rule:evenodd;stroke:red;stroke-width:2.5\" ";
    }

    @Test
    public void testLD(){
        ld = new LineDistance(places);

        assertEquals(places, ld.places);
    }

    @Test
    public void testGetXY(){
        ld = new LineDistance(places);
        String result = ld.getCoordinates();

        assertEquals(expectedXY, result);
    }
}
