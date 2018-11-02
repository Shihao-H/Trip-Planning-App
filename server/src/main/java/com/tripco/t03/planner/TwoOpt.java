package com.tripco.t03.planner;

public class TwoOpt {
    private int minTripDistance;
    private Integer[] optTrip;
    private Integer[] sortedIndexes;
    private Integer[][] distanceGrid;
    private Integer[] tripDistances;

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
    }

    /**
     * Getter method.
     * @param result Integer array.
     */
    public void getTwoOptTrip(Integer[] result){
        System.arraycopy(this.optTrip, 0, result, 0, result.length);
    }

    /**
     * Getter method.
     * @param result Integer array.
     */
    public void getTwoOptLegDistances(Integer[] result){
        System.arraycopy(tripDistances, 0, result, 0, result.length);
    }

    /**
     * Getter Method.
     * @return Integer the total distance for the optimal trip.
     */
    public Integer getTotalDistance(){
        return this.minTripDistance;
    }


    /**
     * Reverses array.
     * @param place1 Integer.
     * @param place2 Integer.
     * @param indices Integer array.
     */
    public void opt2Reverse2(Integer place1,Integer place2,Integer[] indices)
    {
        while(place1 < place2)
        {
            int temp = indices[place1];
            indices[place1] = indices[place2];
            indices[place2] = temp;
            place1++;
            place2--;
        }
    }

    /**
     *Optimizes a list of Integers with two opt.
     */
    public void twoOpt() {
        int n = this.sortedIndexes.length;
        if (n > 4) {
            boolean improvement = true;
            while (improvement) {
                improvement = false;
                for (int i = 0; i <= n - 3; i++) {
                    for (int j = i + 2; j <= n - 1; j++) {
                        int o1, o2, d1, d2;
                        if (j == n - 1) {
                            o1 = sortedIndexes[i];
                            o2 = sortedIndexes[i+1];
                            d1 = sortedIndexes[j];
                            d2 = sortedIndexes[0];
                        }
                        else {
                            o1 = sortedIndexes[i];
                            o2 = sortedIndexes[i+1];
                            d1 = sortedIndexes[j];
                            d2 = sortedIndexes[j+1];
                        }
                        int delta = -this.distanceGrid[o1][o2] - this.distanceGrid[d1][d2] +
                                this.distanceGrid[o1][d1] + this.distanceGrid[o2][d2];
                        if (delta < 0) {
                            opt2Reverse2(i + 1, j, sortedIndexes);
                            improvement = true;
                        }
                    }
                }
            }
        }
    }
}