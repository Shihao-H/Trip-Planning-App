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

    public boolean unvisitedCityLeft(boolean[] visit) {
        boolean flag = false;
        for (int i = 0; i < visit.length; i++) {
            if (!visit[i])
                return true;
        }
        return flag;
    }

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

    public int[] StartNear(int head, long[][] disGrid, int n) {
        int count = 0;
        int[] arr = new int[n];
        arr[0] = head;
        count++;
        long total[] = new long[n];
        boolean[] visit = new boolean[n];
        visit[head] = true;
        while (unvisitedCityLeft(visit)) {
            int index = getMin(disGrid[head], visit, total, head);
            head = index;
            arr[count] = index;
            count++;
        }
        return arr;
    }
}
