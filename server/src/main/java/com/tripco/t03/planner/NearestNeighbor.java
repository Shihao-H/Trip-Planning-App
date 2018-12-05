package com.tripco.t03.planner;

class NearestNeighbor {
    public int[] index;
    public long[][] disGrid;
    public int len;
    public Utility tool;

    /**
     * NearestNeighbor Constructor.
     * @param arr int array.
     * @param arr2 double long.
     */
    public NearestNeighbor(int[] arr,long[][] arr2)
    {
        this.index=arr;
        this.disGrid=arr2;
        this.len=index.length;
        this.tool=new Utility();
    }

    /**
     * NearestNeighbor calling function.
     * @param disGrid double long.
     */
    public void near(long[][] disGrid)
    {
        int shortestStart = 0;
        long pathDis = Long.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            long temp = nearDisEach(i, disGrid);
            if (pathDis > temp) {
                shortestStart = i;
                pathDis = temp;
            }
        }
        this.index = tool.StartNear(shortestStart, disGrid,len);
    }

    /**
     * Get nearest neighbor distance for each point.
     * @param head int.
     * @param disGrid double long.
     */
    public long nearDisEach(int head, long[][] disGrid)
    {
        int[] loc = tool.StartNear(head, disGrid,len);
        return tool.findDis(loc, disGrid);
    }

}
