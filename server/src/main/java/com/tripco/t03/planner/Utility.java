package com.tripco.t03.planner;

public class Utility {

    public int[] opt2Reverse(int[] route, int i1, int k) {
        while (i1 < k) {
            int temp = route[i1];
            route[i1] = route[k];
            route[k] = temp;
            i1++;
            k--;
        }
        return route;
    }



}
