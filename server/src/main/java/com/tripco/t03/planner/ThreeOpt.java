package com.tripco.t03.planner;

public class ThreeOpt {
    public int[] index;
    public long[][] disGrid;
    public int len;
    public Utility tool;


    /**
     * Create a ThreeOpt object.
     * Declares and initializes ThreeOpt objects
     */
    public ThreeOpt(int[] arr,long[][] arr2)
    {
        this.index=arr;
        this.disGrid=arr2;
        this.len=index.length;
        this.tool=new Utility();
    }


    /**
     * Reverses array.
     * @param arr Integer array.
     */
    public void ReverseArray(int[] arr)
    {
        for(int i = 0; i < arr.length / 2; i++)
        {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }

    /**
     * Section array.
     * @param i Integer.
     * @param j Integer.
     * @param arr Integer array.
     */
    public int[] Section(int i, int j, int[] arr)
    {
        int[] section = new int[j-i+1];
        int cout=0;
        for(int k = i; k <= j; k++)
        {
            section[cout]=arr[k];
            cout++;
        }
        return section;
    }


    /**
     * Replace part of original array.
     * @param i Integer.
     * @param j Integer.
     * @param temp Integer array.
     * @param bestpath Integer array.
     */
    public void Replace(int i, int j, int[] temp, int[] bestpath)
    {
        int index=0;
        for(int k = i; k <= j; k++)
        {
            bestpath[k]=temp[index];
            index++;
        }
    }

    /**
     * Combine two arrays.
     * @param a Integer array,
     * @param b Integer array.
     */
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

    /**
     * Combine two arrays.
     * @param disGrid double long array.
     */
    public void threeOpt(long[][] disGrid)
    {
        int shortestStart = 0;
        long pathDis=Long.MAX_VALUE;
        for(int i=0;i<len;i++)
        {
            long temp=Opt3DisEach(i,disGrid);

            if(pathDis>temp)
            {
                shortestStart=i;
                pathDis = temp;
            }
        }
        this.index=tool.StartNear(shortestStart,disGrid,len);
        threeAlg(this.index,disGrid);
    }

    /**
     * Combine two arrays.
     * @param head Integer,
     * @param disGrid double long array.
     */
    public long Opt3DisEach(int head,long [][] disGrid)
    {
        int[] arr=tool.StartNear(head,disGrid,len);
        threeAlg(arr,disGrid);
        return tool.findDis(arr,disGrid);

    }


    }

}