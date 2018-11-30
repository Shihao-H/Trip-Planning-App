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
     * @param i1 Integer.
     * @param j1 Integer.
     * @param arr Integer array.
     */
    public int[] section(int i1, int j1, int[] arr)
    {
        int[] section = new int[j1-i1+1];
        int cout=0;
        for(int k = i1; k <= j1; k++)
        {
            section[cout]=arr[k];
            cout++;
        }
        return section;
    }


    /**
     * Replace part of original array.
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
     * Combine two arrays.
     * @param a1 Integer array,
     * @param b1 Integer array.
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


    /**
     * 3opt case1 of 7 cases
     * @param i1 int,
     * @param j1 int,
     * @param k1 int,
     * @param arr double int array.
     */
    public void opt3Case1(int i1, int j1, int k1, int[] arr)
    {
        int[] temp = section(i1 + 1, j1, arr);
        int[] temp2 = section(j1 + 1, k1, arr);
        reverseArray(temp2);
        int []temp3 = combine(temp2, temp);
        replace(i1 + 1, k1, temp3, arr);
    }

    /**
     * 3opt case2 of 7 cases
     * @param i1 int,
     * @param j1 int,
     * @param k1 int,
     * @param arr double int array.
     */
    public void opt3Case2(int i1, int j1, int k1, int[] arr)
    {
        int[] temp = section(i1 + 1, j1, arr);
        reverseArray(temp);
        int[] temp2 = section(j1 + 1, k1, arr);
        int[]temp3 = combine(temp2, temp);
        replace(i1 + 1, k1, temp3, arr);
    }

    /**
     * 3opt case3 of 7 cases
     * @param i1 int,
     * @param j1 int,
     * @param k1 int,
     * @param arr double int array.
     */
    public void opt3Case3(int i1, int j1, int k1, int[] arr)
    {
        int[] temp = section(i1 + 1, j1, arr);
        int[] temp2 = section(j1 + 1, k1, arr);
        int []temp3 = combine(temp2, temp);
        replace(i1 + 1, k1, temp3, arr);
    }

}