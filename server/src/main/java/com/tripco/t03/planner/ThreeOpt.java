package com.tripco.t03.planner;

public class ThreeOpt {
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
    public ThreeOpt(Integer [] sortedIndexes, Integer[][] distanceGrid){
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
    public void getThreeOptTrip(Integer[] result){
        System.arraycopy(this.optTrip, 0, result, 0, result.length);
    }

    /**
     * Getter method.
     * @param result Integer array.
     */
    public void getThreeOptLegDistances(Integer[] result){
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
     * @param indexes Integer array.
     */
    public void opt2Reverse2(Integer place1,Integer place2,Integer[] indexes)
    {
        while(place1 < place2) {
            int temp = indexes[place1];
            indexes[place1] = indexes[place2];
            indexes[place2] = temp;
            place1++;
            place2--;
        }
    }

    /**
     * Finds min index.
     * @param delta Integer array.
     * @return Integer.
     */
    public Integer findminIndex(Integer[] delta)
    {
        int min = delta[0];
        int index=0;
        for(int i = 0; i < delta.length; i++)
        {
            if(min > delta[i])
            {
                min = delta[i];
                index=i;
            }
        }
        return index;
    }

    /**
     * Gets section.
     * @param i Integer.
     * @param j Integer.
     * @param places Integer array.
     * @return Integer array.
     */
    public Integer[] Section(Integer i, Integer j, Integer[] places)
    {
        Integer[] section = new Integer[j-i+1];
        int cout=0;
        for(int k = i; k <= j; k++)
        {
            section[cout]=places[k];
            cout++;
        }
        return section;
    }

    /**
     * Reverses array.
     * @param arr Integer array.
     * @return Integer array.
     */
    public Integer[] ReverseArray(Integer[] arr)
    {
        for(int i = 0; i < arr.length / 2; i++)
        {
            Integer temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
        return arr;
    }

    /**
     * Combines arrays.
     * @param a Integer array.
     * @param b Integer array.
     * @return Integer array.
     */
    public Integer[] Combine(Integer[] a,Integer[] b)
    {
        Integer[] newPath = new Integer[a.length+b.length];
        System.arraycopy(a, 0, newPath, 0, a.length);
        int cout=0;
        for(int j=a.length;j<newPath.length;j++)
        {
            newPath[j]=b[cout];
            cout++;
        }
        return newPath;
    }

    /**
     * Replace method.
     * @param i Integer.
     * @param j Integer.
     * @param temp Integer array.
     * @param bestpath Integer array.
     * @return Integer Array.
     */
    public Integer[] Replace(Integer i, Integer j, Integer[] temp, Integer[] bestpath)
    {
        int index=0;
        for(int k = i; k <= j; k++)
        {
            bestpath[k]=temp[index];
            index++;
        }
        return bestpath;
    }

    /**
     * OPtimizes trip with thrre opt.
     */
    public void threeOpt()
    {
        final int n = this.sortedIndexes.length;
        if (n > 6) {
            boolean improvement = true;
            while (improvement) {
                improvement = false;
                for(int i=0;i<=n-3;i++){
                    for (int j = i+1; j <= n-2; j++) {
                        for (int k = j + 1; k <= n - 1; k++) {
                            int o1, o2, d1, d2, p1, p2;
                            if (k == n - 1) {
                                o1 = this.sortedIndexes[i];
                                o2 = this.sortedIndexes[i + 1];
                                d1 = this.sortedIndexes[j];
                                d2 = this.sortedIndexes[j+1];
                                p1 = this.sortedIndexes[k];
                                p2 = this.sortedIndexes[0];
                            } else {
                                o1 = this.sortedIndexes[i];
                                o2 = this.sortedIndexes[i + 1];
                                d1 = this.sortedIndexes[j];
                                d2 = this.sortedIndexes[j + 1];
                                p1 = this.sortedIndexes[k];
                                p2 = this.sortedIndexes[k+1];
                            }
                            Integer[] delta = new Integer[7];
                            delta[0]= -this.distanceGrid[o1][o2] -this.distanceGrid[d1][d2] + this.distanceGrid[o1][d1] + this.distanceGrid[o2][d2];
                            delta[1]= -this.distanceGrid[d1][d2] -this.distanceGrid[p1][p2] + this.distanceGrid[d1][p1] + this.distanceGrid[d2][p2];
                            delta[2]= -this.distanceGrid[o1][o2] -this.distanceGrid[p1][p2] + this.distanceGrid[o1][p1] + this.distanceGrid[o2][p2];
                            delta[3]= -this.distanceGrid[o1][o2] -this.distanceGrid[d1][d2] -this.distanceGrid[p1][p2] + this.distanceGrid[o1][d1] + this.distanceGrid[o2][p1]+ this.distanceGrid[d2][p2];
                            delta[4]= -this.distanceGrid[o1][o2] -this.distanceGrid[d1][d2] -this.distanceGrid[p1][p2] + this.distanceGrid[o1][p1] + this.distanceGrid[d2][o2]+ this.distanceGrid[d1][p2];
                            delta[5]= -this.distanceGrid[o1][o2] -this.distanceGrid[d1][d2] -this.distanceGrid[p1][p2] + this.distanceGrid[o1][d2] + this.distanceGrid[p1][d1]+ this.distanceGrid[o2][p2];
                            delta[6]= -this.distanceGrid[o1][o2] -this.distanceGrid[d1][d2] -this.distanceGrid[p1][p2] + this.distanceGrid[o1][d2] + this.distanceGrid[p1][o2]+ this.distanceGrid[d1][p2];
                            Integer minIndex = findminIndex(delta);
                            if (delta[minIndex] < 0) {
                                if(minIndex==0) {
                                    opt2Reverse2(i + 1, j, sortedIndexes);
                                }
                                else if(minIndex==1) {
                                    opt2Reverse2(j + 1, k, sortedIndexes);
                                }
                                else if(minIndex==2) {
                                    opt2Reverse2(i + 1, k, sortedIndexes);
                                }
                                else if(minIndex==3)
                                {
                                    opt2Reverse2(i + 1, j, sortedIndexes);
                                    opt2Reverse2(j + 1, k, sortedIndexes);
                                }
                                else if(minIndex==4)
                                {
                                    Integer[] temp=Section(j+1,k,sortedIndexes);
                                    temp=ReverseArray(temp);
                                    Integer[] temp2=Section(i+1,j,sortedIndexes);
                                    temp=Combine(temp,temp2);
                                    sortedIndexes=Replace(i+1,k,temp,sortedIndexes);
                                }
                                else if(minIndex==5)
                                {
                                    Integer[] temp=Section(i+1,j,sortedIndexes);
                                    temp=ReverseArray(temp);
                                    Integer[] temp2=Section(j+1,k,sortedIndexes);
                                    temp=Combine(temp2,temp);
                                    sortedIndexes=Replace(i+1,k,temp,sortedIndexes);
                                }
                                else {
                                    Integer[] temp=Section(i+1,j,sortedIndexes);
                                    Integer[] temp2=Section(j+1,k,sortedIndexes);
                                    temp=Combine(temp2,temp);
                                    sortedIndexes=Replace(i+1,k,temp,sortedIndexes);
                                }
                                improvement = true;
                            }
                        }
                    }
                }
            }
        }
    }
}