package com.tripco.t03.planner;

import java.util.ArrayList;

public class ThreeOpt {
    public int[] index;
    public long[][] disGrid;
    public int len;

    public ThreeOpt(int[] arr,long[][] arr2)
    {
        this.index=arr;
        this.disGrid=arr2;
        this.len=index.length;
    }
    /**
     * Reverses array.
     * @param arr Integer array.
     */
    public int[] ReverseArray(int[] arr)
    {
        for(int i = 0; i < arr.length / 2; i++)
        {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
        return arr;
    }

    /**
     * Section array.
     * @param i Integer.
     * @param j Integer.
     * @param arr Integer array.
     */
    public int[] Section(int i, int j, int[] arr)
    {
        int[] section = new int[j-i+1];
        int cout=0;
        for(int k = i; k <= j; k++)
        {
            section[cout]=arr[k];
            cout++;
        }
        return section;
    }
}
