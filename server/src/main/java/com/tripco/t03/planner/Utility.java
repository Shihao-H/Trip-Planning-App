
package com.tripco.t03.planner;

public class Utility {
    /**
     * opt2Reverse array.
     ** @param route Integer array.
     * @param i1 Integer.
     * @param k1 Integer.
     */
    public int[] opt2Reverse(int[] route, int i1, int k1) {
        while (i1 < k1) {
            int temp = route[i1];
            route[i1] = route[k1];
            route[k1] = temp;
            i1++;
            k1--;
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

    /**
     * Check whether there is still unvisited place left or not.
     * @param visit boolean array.
     */
    public boolean unvisitedCityLeft(boolean[] visit) {
        boolean flag = false;
        for (int i = 0; i < visit.length; i++) {
            if (!visit[i]){
                return true;}
        }
        return flag;
    }

    /**
     * Find the distance for index array.
     * @param numbers long array.
     * @param visit boolean array.
     * @param total long array.
     * @param k integer.
     */
    private int getMin(long[] numbers, boolean[] visit, long[] total, int k) {
        long minValue = -1;
        int i, index = -1;

        for (i = 0; i < numbers.length; i++) {
            if (!visit[i]) {
                minValue = numbers[i];
                index = i;
                break;
            }
        }

        while (i < numbers.length) {
            if (numbers[i] < minValue) {
                if (!visit[i]) {
                    minValue = numbers[i];
                    index = i;
                }
            }
            i++;
        }

        visit[index] = true;
        total[k] += minValue;
        return index;
    }

}

