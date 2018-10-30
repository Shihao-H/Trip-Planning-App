package com.tripco.t03.planner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestMergeSortPlace {
    private ArrayList<Place> places = new ArrayList<>();

    @Before
    public void setup(){
        places.add(new Place("01", "one", 20.0000, 99.33333));
        places.add(new Place("02", "two", 20.0000, 25.99999));
        places.add(new Place("03", "three", 20.0000, 140.66666));
        places.add(new Place("04", "four", 20.0000, -110.77777));
        places.add(new Place("05", "five", 20.0000, 2.555555));
        places.add(new Place("06", "six", 20.0000, -0.55555));
    }

    @Test
    public void testMergeSort(){
        Integer[] result = MergeSortPlace.sort( places);

        Assert.assertEquals(places.size(), result.length);
    }
}
