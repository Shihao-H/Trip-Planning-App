package com.tripco.t03.planner;

import java.util.Arrays;

class NearestNeighbor {

    private boolean[] notVisited;
    private long minTripDistance;
    private Integer[] optTrip;
    private Integer[] sortedIndexes;
    private Long[][] distanceGrid;
    private Long[] tripDistances;
    private Long[] legDistance;

    /**
     * Constructor for NearestNeighbor Object.
     * @param sortedIndexes Integer array of sorted place indices.
     * @param distanceGrid 2D Integer array of all the distances.
     */
    NearestNeighbor(Integer[] sortedIndexes, Long[][] distanceGrid){
        this.sortedIndexes = sortedIndexes;
        this.distanceGrid = distanceGrid;
        this.optTrip = new Integer[this.sortedIndexes.length];
        this.minTripDistance = Long.MAX_VALUE;
        this.tripDistances = new Long[this.sortedIndexes.length];
        this.notVisited = new boolean[sortedIndexes.length];
        this.legDistance = new Long[tripDistances.length];
        setNotVisited();
    }

    /**
     * Sets notVisited equal to original sorted Array.
     */
    private void setNotVisited(){
        Arrays.fill(this.notVisited, true);
    }

    /**
     * Getter method.
     */
    void getOptimalTrip(Integer[] result){
        System.arraycopy(this.optTrip, 0, result, 0, result.length);
    }

    /**
     * Getter method.
     */
    void getLegDistances(Long[] result){
        System.arraycopy(this.tripDistances, 0, result, 0, result.length);
    }

    /**
     * Getter Method.
     * @return Integer the total distance for the optimal trip.
     */
    Long getTotalDistance(){
        return this.minTripDistance;
    }
    
    /**
     * Calls Nearest neighbor for all starting points.
     * Sets the shortest trip to optTrip.
     * Sets the shortest trip leg distances to tripDistance.
     * Sets the total distance to minTripDistance.
     */
    void nearestNeighbor(){
        Integer[] nextTrip = new Integer[this.sortedIndexes.length];
        for(int i = 0; i< this.sortedIndexes.length; i++){
            long newDistance = innerLoop(0, i, nextTrip);
            if(newDistance < this.minTripDistance){
                this.minTripDistance = newDistance;
                System.arraycopy(this.legDistance, 0, this.tripDistances, 0,
                                 this.tripDistances.length);
                System.arraycopy(nextTrip, 0, this.optTrip,
                                 0, this.optTrip.length);
            }
        }
    }

    /**
     * Recursive helper method.
     * Recursively finds a trip using nearest neighbor.
     * @param counter 1st empty element in current trip.
     * @param currentLocation   last element in array.
     * @param temp Integer array of the current trip being built.
     * @return int the total distance integer.
     */
    private Long innerLoop(int counter, int currentLocation, Integer[] temp) {
        temp[counter] = currentLocation;
        this.notVisited[currentLocation] = false;
        if(counter < temp.length-1) {
            int next = findNearestPlace(currentLocation);
            this.notVisited[next] = false;
            this.legDistance[counter] = distanceGrid[currentLocation][next];
            return this.distanceGrid[currentLocation][next] + innerLoop(counter + 1, next,
                                                                       temp);
        }else {
            this.legDistance[counter] = this.distanceGrid[currentLocation][temp[0]];
            setNotVisited();
            return this.distanceGrid[currentLocation][temp[0]];
        }
    }

    /**
     * Locates the nearest place to the current that has not been visited.
     * @param from the place traveling from.
     * @return startPlace to the nearest place.
     */
    private int findNearestPlace(int from){
        long minDist = Long.MAX_VALUE;
        int result = 0;
        int i = from+1;
        int mod = this.sortedIndexes.length;
        for(i = i%mod; i != from; i= (i+1)%mod) {
            if(isMin(minDist, from, i)) {
                result = i;
                minDist = getDistance(from, i);
            }
        }
        return result;
    }
    
    /**
     * Helper method to get distance.
     * @param row int.
     * @param col int.
     * @return long.
     */
    private long getDistance(int row, int col){
        return this.distanceGrid[row][col];
    }
    
    /**
     * Helper method.
     * @param currentMin long.
     * @param row int.
     * @param col int.
     * @return boolean, true if new distance is minimum.
     */
    private boolean isMin(Long currentMin, int row, int col){
        return ((currentMin > getDistance(row, col)) &&(this.notVisited[col]));
    }
}