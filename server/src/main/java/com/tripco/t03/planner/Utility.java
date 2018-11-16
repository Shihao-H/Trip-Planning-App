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

    public long findDis(int[] arr, long[][] disGrid) {
        int size=arr.length;
        long Dis = 0;
        for (int i = 0; i < arr.length; i++) {
            Dis+=disGrid[arr[i]][arr[(i+1)%size]];
        }
        return Dis;
    }



}
