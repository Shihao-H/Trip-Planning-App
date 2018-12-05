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
     * 3opt case1 of 7 cases.
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
     * 3opt case2 of 7 cases.
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
     * 3opt case3 of 7 cases.
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

    /**
     * Calling function of 3-opt.
     * @param disGrid double long array.
     */
    public void threeOpt(long[][] disGrid)
    {
        int shortestStart = 0;
        long pathDis=Long.MAX_VALUE;
        for(int i=0;i<len;i++)
        {
            long temp=opt3DisEach(i,disGrid);

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
     * Calling function of 3-opt for each starting point.
     * @param head Integer,
     * @param disGrid double long array.
     */
    public long opt3DisEach(int head,long [][] disGrid)
    {
        int[] arr=tool.StartNear(head,disGrid,len);
        threeAlg(arr,disGrid);
        return tool.findDis(arr,disGrid);

    }

    /**
     * 3-opt main algorithm.
     * @param arr Integer,
     * @param disGrid double long array.
     */
    public void threeAlg(int[] arr,long [][] disGrid)
    {
        int n1=arr.length;
        if (n1 > 6) {
            boolean improvement = true;
            while (improvement) {
                improvement = false;
                for(int i=0;i<n1-2;i++){
                    for (int j = i+1; j < n1-1; j++) {
                        for (int k = j + 1; k < n1 ; k++) {
                            int o1, o2, d1, d2, p1, p2;
                            o1 = arr[i];
                            o2 = arr[i + 1];
                            d1 = arr[j];
                            d2 = arr[j + 1];
                            p1 = arr[k];
                            p2 = arr[(k+1)%n1];
                            long three = disGrid[o1][o2] + disGrid[d1][d2] + disGrid[p1][p2];
                            long two = disGrid[o1][o2] + disGrid[p1][p2];
                            long delta3=disGrid[o1][d1]+disGrid[o2][p1]+disGrid[d2][p2]-three;
                            if (delta3 < 0) {
                                tool.opt2Reverse(arr, i + 1, j);
                                tool.opt2Reverse(arr, j + 1, k);
                                improvement = true;
                                continue;
                            }
                            long delta4=disGrid[o1][p1]+disGrid[d2][o2]+disGrid[d1][p2]-three;
                            if (delta4 < 0) {
                                opt3Case1(i,j,k,arr);
                                improvement = true;
                                continue;
                            }
                            long delta5=disGrid[o1][d2]+disGrid[p1][d1]+disGrid[o2][p2]-three;
                            if (delta5 < 0) {
                                opt3Case2(i,j,k,arr);
                                improvement = true;
                                continue;
                            }

                            long delta6=disGrid[o1][d2]+disGrid[p1][o2]+disGrid[d1][p2]-three;
                            if (delta6 < 0) {
                                opt3Case3(i,j,k,arr);
                                improvement = true;
                                continue;
                            }
                            long delta0 = disGrid[o1][p1] + disGrid[o2][p2] - two;
                            if (delta0 < 0) {
                                tool.opt2Reverse(arr, i + 1, k);
                                improvement = true;
                                continue;
                            }
                            long delta1 = two + disGrid[o1][d1] + disGrid[o2][d2]- two;
                            if (delta1 < 0) {
                                tool.opt2Reverse(arr, i + 1, j);
                                improvement = true;
                                continue;
                            }
                            long delta2 = two + disGrid[d1][p1] + disGrid[d2][p2]- two;
                            if (delta2 < 0) {
                                tool.opt2Reverse(arr, j + 1, k);
                                improvement = true;
                                continue;
                            }
                        }
                    }
                }
            }
        }
    }
}
