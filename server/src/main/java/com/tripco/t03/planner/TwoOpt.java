package com.tripco.t03.planner;

import java.util.ArrayList;
import java.util.Arrays;

public class TwoOpt {
    public void opt2Reverse2(int i,int k,int[] indexes)
    {
        while(i < k) {
            int temp = indexes[i];
            indexes[i] = indexes[k];
            indexes[k] = temp;
            i++; k--;
        }
    }

    public void TwoOpt ()
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
                            o1 = indexes[i];
                            o2 = indexes[i+1];
                            d1 = indexes[j];
                            d2 = indexes[0];
                        }
                        else
                        {
                            o1 = indexes[i];
                            o2 = indexes[i+1];
                            d1 = indexes[j];
                            d2 = indexes[j+1];
                        }
                        int delta = -grid.distanceGrid[o1][o2] - grid.distanceGrid[d1][d2] + grid.distanceGrid[o1][d1] + grid.distanceGrid[o2][d2];
                        if (delta < 0)
                        {
                            opt2Reverse2(i + 1, j, indexes);
                            improvement = true;
                        }
                    }
                }
            }
        }
        updatePlaces(indexes,initialPath);
    }

}
