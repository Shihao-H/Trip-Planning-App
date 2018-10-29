package com.tripco.t03.planner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMergeSortPlace {
    private Place[] places;
    private Place[] expected;
    @Before
    public void setup(){
        places = expected = new Place[6];
        places[0] = expected[4] = new Place("01", "one", 20.0000, 99.33333);
        places[1] = expected[3] = new Place("02", "two", 20.0000, 25.99999);
        places[2] = expected[5] = new Place("03", "three", 20.0000, 140.66666);
        places[3] = expected[0] = new Place("04", "four", 20.0000, -110.77777);
        places[4] = expected[2] = new Place("05", "five", 20.0000, 2.555555);
        places[5] = expected[1] = new Place("06", "six", 20.0000, -0.55555);
    }

    @Test
    public void testMergeSort(){
        Place [] result = MergeSortPlace.sort( places);

        Assert.assertEquals(expected[0], result[0]);
    }
}
