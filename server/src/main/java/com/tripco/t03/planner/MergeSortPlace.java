package com.tripco.t03.planner;

public class MergeSortPlace {

    /**
     * Sorts an Array of Distances by increasing longitude.
     * @param toSort Place array to be sorted.
     */
    public static Place[] sort(Place[] toSort){
        Place[] sorted = new Place[toSort.length];
        return divide(0, toSort.length-1, toSort, sorted);
    }

    /**
     * Helper method to divide the Array for sorting.
     * @param left index of leftmost element to be sorted.
     * @param right index of rightmost element to be sorted.
     * @param toSort Array of places to be sorted.
     * @param sorted Array of places that are sorted.
     * @return an sorted Array of places.
     */
    private static Place[] divide(int left, int right, Place[] toSort, Place[] sorted){
        if(right > left) {
            int mid = (right + left) / 2;
            Place[] left = divide(left, mid, toSort, sorted);
            Place[] right = divide(mid+1, right, toSort, sorted);
            return mergeLong(left, mid+1, right, left, right);
        }else{
            Place[] temp = new Place[1];
            temp[0] = toSort[left];
            return temp;
        }
    }

    /**
     * Helper method called by divide to merge the sorted array.
     * @param begin lower index.
     * @param mid mid index.
     * @param end upper index.
     * @param left Array of places left on the left.
     * @param right Array of places on the right.
     * @return Place[] sorted Array of places.
     */
    private static Place[] mergeLong(int begin, int mid, int end, Place[] left, Place[] right) {
        int leftSize = (mid-begin);
        int rightSize = (end-mid)+1;
        Place[] sorted = new Place[leftSize+rightSize];
        int leftIndex = 0;
        int rightIndex = 0;
        int sortIndex = 0;

        while((leftIndex < leftSize) && (rightIndex < rightSize)){
            if(left[leftIndex].longitude <= right[rightIndex].longitude){
                sorted[sortIndex] = left[leftIndex];
                leftIndex += 1;
            }else{
                sorted[sortIndex] = right[rightIndex];
                rightIndex += 1;
            }
            sortIndex++;
        }
        Place[] temp =  finalMerge(rightIndex, sortIndex, rightSize, right, sorted);
        return finalMerge(leftIndex, sortIndex, leftSize, left, temp);
    }

    /**
     * Helper method that eliminates duplicating code for merging the last of the left and right Arrays
     * @param fromIndex index for the from Array.
     * @param toIndex index for the to array.
     * @param size size of the from array.
     * @param from Array to merge from.
     * @param to Array to merge to.
     * @return Place[] a sorted array.
     */
    private static Place[] finalMerge(int fromIndex, int toIndex, int size, Place[] from, Place[] to){
        while(fromIndex < size){
            to[toIndex] =  from[fromIndex];
            toIndex++;
            fromIndex++;
        }
        return to;
    }

}
