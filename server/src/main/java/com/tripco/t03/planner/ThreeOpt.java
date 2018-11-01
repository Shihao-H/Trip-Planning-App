package com.tripco.t03.planner;

public class ThreeOpt {
    private Integer[] sortedIndexes;
    private Integer[][] distanceGrid;

    /**
     * Constructor for NearestNeighbor Object.
     * @param sortedIndexes Integer array of sorted place indices.
     * @param distanceGrid 2D Integer array of all the distances.
     */
    public ThreeOpt(Integer [] sortedIndexes, Integer[][] distanceGrid){
        this.sortedIndexes = sortedIndexes;
        this.distanceGrid = distanceGrid;
    }

    public void opt2Reverse2(int i,int k,int[] indexes)
    {
        while(i < k) {
            int temp = indexes[i];
            indexes[i] = indexes[k];
            indexes[k] = temp;
            i++; k--;
        }
    }

    public int findminIndex(int[] delta)
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

    public int[] Section(int i, int j, int[] places)
    {
        int[] section = new int[j-i+1];
        int cout=0;
        for(int k = i; k <= j; k++)
        {
            section[cout]=places[k];
            cout++;
        }
        return section;
    }

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

    public int[] Combine(int[] a,int[] b)
    {
        int[] newPath = new int[a.length+b.length];
        for(int i=0;i<a.length;i++){
            newPath[i]=a[i];
        }
        int cout=0;
        for(int j=a.length;j<newPath.length;j++)
        {
            newPath[j]=b[cout];
            cout++;
        }
        return newPath;
    }

    public int[] Replace(int i, int j, int[] temp, int[] bestpath)
    {
        int index=0;
        for(int k = i; k <= j; k++)
        {
            bestpath[k]=temp[index];
            index++;
        }
        return bestpath;
    }

    public void ThreeOpt()
    {
        final int n = places.size();
        Place[] initialPath = new Place[n];
        for(int k=0;k<n;k++)
        {
            initialPath[k]=this.places.get(k);
        }
        String units=this.options.units;
        String unitName=this.options.unitName;
        Double uniRadius=this.options.unitRadius;
        DistanceGrid grid=new DistanceGrid(initialPath,units,unitName,uniRadius);
        grid.buildGrid();
        int[] indexes = new int[n];
        for(int k=0;k<n;k++)
        {
            indexes[k]=k;
        }
        if (n > 6) {
            boolean improvement = true;
            while (improvement) {
                improvement = false;
                for(int i=0;i<=n-3;i++){
                    for (int j = i+1; j <= n-2; j++) {
                        for (int k = j + 1; k <= n - 1; k++) {
//                            Place o1, o2, d1, d2, p1, p2;
                            int o1, o2, d1, d2, p1, p2;
                            if (k == n - 1) {
                                o1 = indexes[i];
                                o2 = indexes[i + 1];
                                d1 = indexes[j];
                                d2 = indexes[j+1];
                                p1 = indexes[k];
                                p2 = indexes[0];
                            } else {
                                o1 = indexes[i];
                                o2 = indexes[i + 1];
                                d1 = indexes[j];
                                d2 = indexes[j + 1];
                                p1 = indexes[k];
                                p2 = indexes[k+1];
                            }
                            int[] delta = new int[7];
                            delta[0]= -grid.distanceGrid[o1][o2] -grid.distanceGrid[d1][d2] + grid.distanceGrid[o1][d1] + grid.distanceGrid[o2][d2];
                            delta[1]= -grid.distanceGrid[d1][d2] -grid.distanceGrid[p1][p2] + grid.distanceGrid[d1][p1] + grid.distanceGrid[d2][p2];
                            delta[2]= -grid.distanceGrid[o1][o2] -grid.distanceGrid[p1][p2] + grid.distanceGrid[o1][p1] + grid.distanceGrid[o2][p2];
                            delta[3]= -grid.distanceGrid[o1][o2] -grid.distanceGrid[d1][d2] -grid.distanceGrid[p1][p2] + grid.distanceGrid[o1][d1] + grid.distanceGrid[o2][p1]+ grid.distanceGrid[d2][p2];
                            delta[4]= -grid.distanceGrid[o1][o2] -grid.distanceGrid[d1][d2] -grid.distanceGrid[p1][p2] + grid.distanceGrid[o1][p1] + grid.distanceGrid[d2][o2]+ grid.distanceGrid[d1][p2];
                            delta[5]= -grid.distanceGrid[o1][o2] -grid.distanceGrid[d1][d2] -grid.distanceGrid[p1][p2] + grid.distanceGrid[o1][d2] + grid.distanceGrid[p1][d1]+ grid.distanceGrid[o2][p2];
                            delta[6]= -grid.distanceGrid[o1][o2] -grid.distanceGrid[d1][d2] -grid.distanceGrid[p1][p2] + grid.distanceGrid[o1][d2] + grid.distanceGrid[p1][o2]+ grid.distanceGrid[d1][p2];
                            int minIndex = findminIndex(delta);
                            if (delta[minIndex] < 0) {
                                if(minIndex==0) {
                                    opt2Reverse2(i + 1, j, indexes);
                                }
                                else if(minIndex==1) {
                                    opt2Reverse2(j + 1, k, indexes);
                                }
                                else if(minIndex==2) {
                                    opt2Reverse2(i + 1, k, indexes);
                                }
                                else if(minIndex==3)
                                {
                                    opt2Reverse2(i + 1, j, indexes);
                                    opt2Reverse2(j + 1, k, indexes);
                                }
                                else if(minIndex==4)
                                {
                                    int[] temp=Section(j+1,k,indexes);
                                    temp=ReverseArray(temp);
                                    int[] temp2=Section(i+1,j,indexes);
                                    temp=Combine(temp,temp2);
                                    indexes=Replace(i+1,k,temp,indexes);
                                }
                                else if(minIndex==5)
                                {
                                    int[] temp=Section(i+1,j,indexes);
                                    temp=ReverseArray(temp);
                                    int[] temp2=Section(j+1,k,indexes);
                                    temp=Combine(temp2,temp);
                                    indexes=Replace(i+1,k,temp,indexes);
                                }
                                else {
                                    int[] temp=Section(i+1,j,indexes);
                                    int[] temp2=Section(j+1,k,indexes);
                                    temp=Combine(temp2,temp);
                                    indexes=Replace(i+1,k,temp,indexes);
                                }
                                improvement = true;
                            }
                        }
                    }
                }
            }
        }
        updatePlaces(indexes,initialPath);
    }
}
