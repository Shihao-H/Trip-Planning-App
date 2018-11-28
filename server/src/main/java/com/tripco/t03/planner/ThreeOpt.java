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
     * reverses array.
     * @param arr Integer array.
     */
    public void reverseArray(int[] arr)
    {
        for(int i = 0; i < arr.length / 2; i++)
        {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }

    /**
     * section array.
     * @param i Integer.
     * @param j Integer.
     * @param arr Integer array.
     */
    public int[] section(int i, int j, int[] arr)
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
     * replace part of original array.
     * @param i1 Integer.
     * @param j1 Integer.
     * @param temp Integer array.
     * @param bestpath Integer array.
     */
    public void replace(int i1, int j1, int[] temp, int[] bestpath)
    {
        int index=0;
        for(int k = i1; k <= j1; k++)
        {
            bestpath[k]=temp[index];
            index++;
        }
    }




    /**
     * combine two arrays.
     * @param a Integer array,
     * @param b Integer array.
     */
    public int[] combine(int[] a1,int[] b1)
    {
        int[] newPath = new int[a1.length+b1.length];
        for(int i=0;i<a1.length;i++){
            newPath[i]=a1[i];
        }
        int cout=0;
        for(int j=a1.length;j<newPath.length;j++)
        {
            newPath[j]=b1[cout];
            cout++;
        }
        return newPath;
    }


}
