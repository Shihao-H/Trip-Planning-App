package com.tripco.t03.planner;

class TwoOpt {
    public int[] index;
    public long[][] disGrid;
    public int len;
    public Utility tool;


    /**
     * TwoOpt constructor.
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
     * Calling function of twoOpt.
     * @param disGrid long array.
     */
    public void twoOpt(long[][] disGrid)
    {
        int shortestStart = 0;
        long pathDis = Long.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            long temp = opt2DisEach(i, disGrid);

            if (pathDis > temp) {
                shortestStart = i;
                pathDis = temp;
            }
        }
        this.index = tool.StartNear(shortestStart, disGrid,len);
        twoAlg(this.index);
    }

    /**
     * Each staring point of TwoOpt algorithm.
     * @param head Integer,
     * @param disGrid double long array.
     */
    public long opt2DisEach(int head, long[][] disGrid)
    {
        int[] loc = tool.StartNear(head, disGrid,len);
        twoAlg(loc);
        return tool.findDis(loc, disGrid);
    }


    /**
     * case0.
     * @param arr int array.
     * @param i1 int.
     * @param j1 int.
     * @param improment boolean.
     */
    public boolean case0(int[] arr, int i1, int j1,boolean improvement)
    {
        int o1 = arr[i1];
        int o2 = arr[i1 + 1];
        int d1 = arr[j1];
        int d2 = arr[(j1 + 1)%(arr.length)];
        long delta =-disGrid[o1][o2]-disGrid[d1][d2]+disGrid[o1][d1]+disGrid[o2][d2];
        if (delta < 0) {
            tool.opt2Reverse(arr, i1 + 1, j1);
            improvement = true;
        }
        return improvement;
    }

    /**
     * For loops.
     * @param arr int array.
     * @param n1 int.
     * @param improvement boolean.
     */
    public boolean loop(int[] arr, int n1, boolean improvement)
    {
        for (int i = 0; i <= n1 - 3; i++) {
            for (int j = i + 2; j <= n1 - 1; j++) {
                improvement=case0(arr,i,j,improvement);
            }
        }
        return improvement;
    }

    /**
     * Main two-opt algorithm.
     * @param arr int array.
     */
    public void twoAlg(int[] arr) {
        int n1 = arr.length;
        if (n1 > 4) {
            boolean improvement = true;
            while (improvement) {
                improvement = false;
                improvement=loop(arr, n1,improvement);
            }
        }
    }
}
