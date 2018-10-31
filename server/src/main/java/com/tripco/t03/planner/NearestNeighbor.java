package com.tripco.t03.planner;

import java.util.Arrays;

public class NearestNeighbor {

    private boolean[] notVisited;
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
    public NearestNeighbor(Integer [] sortedIndexes, Integer[][] distanceGrid){
        this.sortedIndexes = sortedIndexes;
        this.distanceGrid = distanceGrid;
        this.optTrip = new Integer[this.sortedIndexes.length];
        this.minTripDistance = Integer.MAX_VALUE;
        this.tripDistances = new Integer[this.sortedIndexes.length];
        this.notVisited = new boolean[sortedIndexes.length];
        this.legDistance = new Integer[tripDistances.length];
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

    /**
     * Calls Nearest neighbor for all starting points.
     * Sets the shortest trip to optTrip.
     * Sets the shortest trip leg distances to tripDistance.
     * Sets the total distance to minTripDistance.
     */
    public void nearestNeighbor(){
        Integer[] nextTrip = new Integer[this.sortedIndexes.length];
        for(int i = 0; i< sortedIndexes.length; i++){
            int newDistance = innerLoop(0, i, nextTrip);
            if(newDistance < this.minTripDistance){
                this.minTripDistance = newDistance;
                copyArray(this.legDistance, this.tripDistances);
                copyArray(nextTrip, this.optTrip);
            }
        }
    }

    private void copyArray(Integer[] from, Integer[] to){
        if(from.length == to.length) {
            System.arraycopy(from, 0, to, 0, from.length);
        }
    }
    /**
     * Recursive helper method.
     * Recursively finds a trip using nearest neighbor.
     * @param counter 1st empty element in current trip.
     * @param currentLocation   last element in array.
     * @param temp Integer array of the current trip being buiilt.
     * @return int the total distance integer.
     */
    private int innerLoop(int counter, int currentLocation, Integer[] temp) {

        temp[counter] = currentLocation;
        this.notVisited[currentLocation] = false;
        if(counter < temp.length-1) {

            int next = findNearestPlace(currentLocation);

            this.notVisited[next] = false;
            this.legDistance[counter] = distanceGrid[currentLocation][next];
            return distanceGrid[currentLocation][next] + innerLoop(counter + 1, next, temp);
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
        Integer minDist = Integer.MAX_VALUE;
        int result = 0;
        for(int i = 0; i < sortedIndexes.length; i++ ) {
            if(i != from) {
                if ((minDist > distanceGrid[from][i]) &&(notVisited[i])){
                    minDist = distanceGrid[from][i];
                    result = i;
                }
            }
        }
        return result;
    }
}
