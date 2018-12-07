package com.tripco.t03.planner;

import java.util.ArrayList;

public class MergeSortPlace {

    /**
     * Sorts an Array of Distances by increasing longitude.
     * @param toSort Place array to be sorted.
     */
    public static Integer[] sort(ArrayList<Place> toSort){
        return divide(0, toSort.size()-1, toSort);
    }

    /**
     * Helper method to divide the Array for sorting.
     * @param left index of leftmost element to be sorted.
     * @param right index of rightmost element to be sorted.
     * @param toSort Array of places to be sorted.
     * @return an sorted Array of indices for original Place ArrayList.
     */
    private static Integer[] divide(int left, int right, ArrayList<Place> toSort){
        if(right > left) {
            int mid = (right + left) / 2;
            Integer[] tempLeft = divide(left, mid, toSort);
            Integer[] tempRight = divide(mid+1, right, toSort);
            return mergeLong( tempLeft, tempRight, toSort);
        }else{
            Integer[] temp = new Integer[1];
            temp[0] = left;
            return temp;
        }
    }

    /**
     * Helper method called by divide to merge the sorted array.
     * @param left Array of places left on the left.
     * @param right Array of places on the right.
     * @return Integer[] Array of places indices.
     */
    private static Integer[] mergeLong(Integer[] left, Integer[] right, ArrayList<Place> toSort) {
        int leftSize = left.length;
        int rightSize = right.length;
        Integer[] sorted = new Integer[leftSize+rightSize];
        int leftIndex = 0;
        int rightIndex = 0;
        int sortIndex = 0;

        while((leftIndex < leftSize) && (rightIndex < rightSize)){
            if(toSort.get(left[leftIndex]).longitude <= toSort.get(right[rightIndex]).longitude){
                sorted[sortIndex] = left[leftIndex];
                leftIndex += 1;
            }else{
                sorted[sortIndex] = right[rightIndex];
                rightIndex += 1;
            }
            sortIndex++;
        }
        Integer[] temp = finalMerge(rightIndex, sortIndex, right, sorted);
        return finalMerge(leftIndex, sortIndex, left, temp);
    }

    /**
     * Helper method that eliminates duplicating code for merging the last of the left and right Arrays
     * @param fromIndex index for the from Array.
     * @param toIndex index for the to array.
     * @param from Array to merge from.
     * @param to Array to merge to.
     * @return Integer[] a sorted array.
     */
    private static Integer[] finalMerge(int fromIndex, int toIndex, Integer[] from, Integer[] to){
        while(fromIndex < from.length){
            to[toIndex] =  from[fromIndex];
            toIndex++;
            fromIndex++;
        }
        return to;
    }
}
