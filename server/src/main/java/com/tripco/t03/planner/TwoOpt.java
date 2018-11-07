package com.tripco.t03.planner;

class TwoOpt {
    
    private long totalDistance;
    private Integer[] sortedIndices;
    private Long[][] distanceGrid;
    private Long[] tripDistances;
    
    /**
     * Constructor for TwoOpt Object.
     * @param sortedIndices Integer array of sorted place indices.
     * @param distanceGrid 2D Integer array of all the distances.
     */
    TwoOpt(Integer[] sortedIndices, Long[][] distanceGrid){
        this.sortedIndices = new Integer[sortedIndices.length];
        System.arraycopy(sortedIndices, 0, this.sortedIndices,
                         0, this.sortedIndices.length);
        this.distanceGrid = distanceGrid;
        this.totalDistance = 0;
        this.tripDistances = new Long[this.sortedIndices.length];
    }
    
    /**
     * Getter.
     * @param result Integer[].
     */
    void getSortedIndices(Integer[] result){
        System.arraycopy(this.sortedIndices, 0, result, 0, result.length);
    }
    
    /**
     * Getter method.
     * @param result Integer array.
     */
    void getTwoOptLegDistances(Long[] result){
        System.arraycopy(tripDistances, 0, result, 0, result.length);
    }
    
    /**
     * Getter Method.
     * @return Integer the total distance for the optimal trip.
     */
    Long getTotalDistance(){
        for(Long leg: this.tripDistances){
            this.totalDistance += leg;
        }
        return this.totalDistance;
    }
    
    private void setLegDistances(){
        int jam = 0;
        for(int i = 0; i < sortedIndices.length - 1; i++, jam++){
            this.tripDistances[jam] = this.distanceGrid[sortedIndices[i]][sortedIndices[i + 1]];
        }
        this.tripDistances[jam] = this.distanceGrid[sortedIndices[jam]][sortedIndices[0]];
        
        
    }
    
    /**
     * Reverses array.
     * @param index1 Integer.
     * @param index2 Integer.
     * @param originalTrip Integer array.
     */
    void opt2Reverse2(Integer index1, Integer index2, Integer[] originalTrip)
    {
        while(index1 < index2) {
            int temp = originalTrip[index1];
            originalTrip[index1] = originalTrip[index2];
            originalTrip[index2] = temp;
            index1++;
            index2--;
        }
    }
    
    /**
     *Optimizes a list of Integers with two opt.
     */
    void twoOpt(Integer[] result) {
        int ned = this.sortedIndices.length - 1;
        
        boolean improvement = true;
        while (improvement) {
            improvement = false;
            for (int i = 0; i <= ned - 3; i++) {
                for (int j = i + 2; j <= ned - 1; j++) {
                    int o1 = sortedIndices[i];
                    int o2 = sortedIndices[i + 1];
                    int d1 = sortedIndices[j];
                    int d2 = sortedIndices[j + 1];
                    long delta =
                            ((-1)*this.distanceGrid[o1][o2]) - this.distanceGrid[d1][d2]
                            + this.distanceGrid[o1][d1] + this.distanceGrid[o2][d2];
                    if (delta < 0) {
                        opt2Reverse2(i + 1, j, sortedIndices);
                        improvement = true;
                    }
                }
            }
        }
        System.arraycopy(this.sortedIndices, 0, result, 0, result.length);
        setLegDistances();
    }
}
