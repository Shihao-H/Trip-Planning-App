package com.tripco.t03.planner;

import java.util.ArrayList;
import java.util.Arrays;

public class TwoOpt {
    private int minTripDistance;
    private Integer[] optTrip;
    private Integer[] sortedIndexes;
    private Integer[][] distanceGrid;
    private Integer[] tripDistances;
    private Integer[] legDistance;

    /**
     * Constructor for NearestNeighbor Object.
     * @param sortedIndexes Integer array of sorted place indices.
     * @param distanceGrid 2D Integer array of all the distances.
     */
    public TwoOpt(Integer [] sortedIndexes, Integer[][] distanceGrid){
        this.sortedIndexes = sortedIndexes;
        this.distanceGrid = distanceGrid;
        this.optTrip = new Integer[this.sortedIndexes.length];
        this.minTripDistance = Integer.MAX_VALUE;
        this.tripDistances = new Integer[this.sortedIndexes.length];
        this.legDistance = new Integer[tripDistances.length];
    }

    /**
     * Getter method.
     */
    public void getOptimalTrip(Integer[] result){
        System.arraycopy(this.optTrip, 0, result, 0, result.length);
    }

    /**
     * Getter method.
     */
    public void getLegDistances(Integer[] result){
        System.arraycopy(tripDistances, 0, result, 0, result.length);
    }

    /**
     * Getter Method.
     * @return Integer the total distance for the optimal trip.
     */
    public Integer getTotalDistance(){
        return this.minTripDistance;
    }


    public void opt2Reverse2(Integer i,Integer k,Integer[] indexes)
    {
        while(i < k)
        {
            int temp = indexes[i];
            indexes[i] = indexes[k];
            indexes[k] = temp;
            i++; k--;
        }
    }

    public void TwoOpt ()
    {
        int n = this.sortedIndexes.length;
        Place[] initialPath = new Place[n];
//        for(int k=0;k<n;k++)
//        {
//            initialPath[k]=this.places.get(k);
//        }
//        String units=this.options.units;
//        String unitName=this.options.unitName;
//        Double uniRadius=this.options.unitRadius;
//        DistanceGrid grid=new DistanceGrid(initialPath,units,unitName,uniRadius);
//        grid.buildGrid();
//        int[] indexes = new int[n];
//        for(int k=0;k<n;k++)
//        {
//            indexes[k]=k;
//        }
        if (n > 4) {
            boolean improvement = true;
            while (improvement) {
                improvement = false;
                for (int i = 0; i <= n - 3; i++)
                {
                    for (int j = i + 2; j <= n - 1; j++)
                    {
                        int o1, o2, d1, d2;
                        if (j == n - 1)
                        {
                            o1 = sortedIndexes[i];
                            o2 = sortedIndexes[i+1];
                            d1 = sortedIndexes[j];
                            d2 = sortedIndexes[0];
                        }
                        else
                        {
                            o1 = sortedIndexes[i];
                            o2 = sortedIndexes[i+1];
                            d1 = sortedIndexes[j];
                            d2 = sortedIndexes[j+1];
                        }
                        int delta = -this.distanceGrid[o1][o2] - this.distanceGrid[d1][d2] + this.distanceGrid[o1][d1] + this.distanceGrid[o2][d2];
                        if (delta < 0)
                        {
                            opt2Reverse2(i + 1, j, sortedIndexes);
                            improvement = true;
                        }
                    }
                }
            }
        }
//        updatePlaces(indexes,initialPath);
    }

}
