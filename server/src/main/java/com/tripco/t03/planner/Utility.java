
package com.tripco.t03.planner;

public class Utility {
    /**
     * opt2Reverse array.
     ** @param route Integer array.
     * @param i1 Integer.
     * @param k Integer.
     */
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
    /**
     * Find the distance for index array.
     * @param arr Integer array.
     * @param disGrid double long array.
     */
    public long findDis(int[] arr, long[][] disGrid) {
        int size=arr.length;
        long dis = 0;
        for (int i = 0; i < arr.length; i++) {
            dis+=disGrid[arr[i]][arr[(i+1)%size]];
        }
        return dis;
    }

}

