package com.tripco.t03.planner;

class TwoOpt {
    public int[] index;
    public long[][] disGrid;
    public int len;
    public Utility tool;


    /**
     * TwoOpt constructor
     * @param arr int array.
     * @param arr2 double long.
     */
    public TwoOpt(int[] arr,long[][] arr2)
    {
        this.index=arr;
        this.disGrid=arr2;
        this.len=index.length;
        this.tool=new Utility();
    }

    /**
     * Calling function of twoOpt
     * @param disGrid long array.
     */
    public void twoOpt(long[][] disGrid)
    {
        int shortestStart = 0;
        long pathDis = Long.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            long temp = Opt2DisEach(i, disGrid);

            if (pathDis > temp) {
                shortestStart = i;
                pathDis = temp;
            }
        }
        this.index = tool.StartNear(shortestStart, disGrid,len);
        twoAlg(this.index, disGrid);
    }

    /**
     * Find the index of the minimum value of an array.
     * Add up the distance.
     * @param numbers long array.
     * @param visit boolean array.
     * @param total long array.
     * @param k1 integer.
     */
    public long Opt2DisEach(int head, long[][] disGrid)
    {
        int[] loc = tool.StartNear(head, disGrid,len);
        twoAlg(loc, disGrid);
        return tool.findDis(loc, disGrid);
    }


    /**
     * Find the index of the minimum value of an array.
     * Add up the distance.
     * @param numbers long array.
     * @param visit boolean array.
     * @param total long array.
     * @param k1 integer.
     */
    public void twoAlg(int[] arr, long[][] disGrid) {
        int n = arr.length;
        if (n > 4) {
            boolean improvement = true;
            while (improvement) {
                improvement = false;
                for (int i = 0; i <= n - 3; i++) {
                    for (int j = i + 2; j <= n - 1; j++) {
                        int o1, o2, d1, d2;
                        o1 = arr[i];
                        o2 = arr[i + 1];
                        d1 = arr[j];
                        d2 = arr[(j + 1)%n];
                        long delta = -disGrid[o1][o2] - disGrid[d1][d2] + disGrid[o1][d1] + disGrid[o2][d2];
                        if (delta < 0) {
                            tool.opt2Reverse(arr, i + 1, j);
                            improvement = true;
                        }
                    }
                }
            }
        }
    }
}
