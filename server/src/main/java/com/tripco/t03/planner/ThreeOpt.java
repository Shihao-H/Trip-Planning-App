package com.tripco.t03.planner;

import java.util.ArrayList;

public class ThreeOpt {
    public int[] index;
    public long[][] disGrid;
    public int len;
    public Utility tool;

    public ThreeOpt(int[] arr,long[][] arr2)
    {
        this.index=arr;
        this.disGrid=arr2;
        this.len=index.length;
        this.tool=new Utility();
    }

//    public long Opt3DisEach(int head,long [][] disGrid)
//    {
//        int[] arr=tool.StartNear(head,disGrid,len);
//        threeAlg(arr,disGrid);
//        return tool.findDis(arr,disGrid);
//
//    }
//
//    public int[] Replace(int i, int j, int[] temp, int[] bestpath)
//    {
//        int index=0;
//        for(int k = i; k <= j; k++)
//        {
//            bestpath[k]=temp[index];
//            index++;
//        }
//        return bestpath;
//    }
//
//    public int findminIndex(long[] delta)
//    {
//        long min = delta[0];
//        int index=0;
//        for(int i = 0; i < delta.length; i++)
//        {
//            if(min > delta[i])
//            {
//                min = delta[i];
//                index=i;
//            }
//        }
//        return index;
//    }
    /**
     * Reverses array.
     * @param arr Integer array.
     */
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

//    public int[] Combine(int[] a,int[] b)
//    {
//        int[] newPath = new int[a.length+b.length];
//        for(int i=0;i<a.length;i++){
//            newPath[i]=a[i];
//        }
//        int cout=0;
//        for(int j=a.length;j<newPath.length;j++)
//        {
//            newPath[j]=b[cout];
//            cout++;
//        }
//        return newPath;
//    }
//
//    public void threeOpt(long[][] disGrid)
//    {
//        int shortestStart = 0;
//        long pathDis=Long.MAX_VALUE;
//        for(int i=0;i<len;i++)
//        {
//            long temp=Opt3DisEach(i,disGrid);
//
//            if(pathDis>temp)
//            {
//                shortestStart=i;
//                pathDis = temp;
//            }
//        }
//        int []indexes=tool.StartNear(shortestStart,disGrid,len);
//        threeAlg(indexes,disGrid);
//    }
//
//    private void threeAlg(int[] arr,long [][] disGrid)
//    {
//        int n=arr.length;
//        if (n > 6) {
//            boolean improvement = true;
//            while (improvement) {
//                improvement = false;
//                for(int i=0;i<n-2;i++){
//                    for (int j = i+1; j < n-1; j++) {
//                        for (int k = j + 1; k < n ; k++) {
//                            int o1, o2, d1, d2, p1, p2;
//                            if (k == n - 1) {
//                                o1 = arr[i];
//                                o2 = arr[i + 1];
//                                d1 = arr[j];
//                                d2 = arr[j+1];
//                                p1 = arr[k];
//                                p2 = arr[0];
//
//                            } else
//                            {
//                                o1 = arr[i];
//                                o2 = arr[i + 1];
//                                d1 = arr[j];
//                                d2 = arr[j + 1];
//                                p1 = arr[k];
//                                p2 = arr[k+1];
//                            }
//                            long[] delta = new long[7];
//                            delta[0]= -disGrid[o1][o2] -disGrid[d1][d2] + disGrid[o1][d1] + disGrid[o2][d2];
//                            delta[1]= -disGrid[d1][d2] -disGrid[p1][p2] + disGrid[d1][p1] + disGrid[d2][p2];
//                            delta[2]= -disGrid[o1][o2] -disGrid[p1][p2] + disGrid[o1][p1] + disGrid[o2][p2];
//                            delta[3]= -disGrid[o1][o2] -disGrid[d1][d2] -disGrid[p1][p2] + disGrid[o1][d1] + disGrid[o2][p1]+ disGrid[d2][p2];
//                            delta[4]= -disGrid[o1][o2] -disGrid[d1][d2] -disGrid[p1][p2] + disGrid[o1][p1] + disGrid[d2][o2]+ disGrid[d1][p2];
//                            delta[5]= -disGrid[o1][o2] -disGrid[d1][d2] -disGrid[p1][p2] + disGrid[o1][d2] + disGrid[p1][d1]+ disGrid[o2][p2];
//                            delta[6]= -disGrid[o1][o2] -disGrid[d1][d2] -disGrid[p1][p2] + disGrid[o1][d2] + disGrid[p1][o2]+ disGrid[d1][p2];
//                            int minIndex = findminIndex(delta);
//                            if (delta[minIndex] < 0) {
//                                if(minIndex==0) {
//                                    tool.opt2Reverse(arr, i + 1, j);
//                                }
//                                else if(minIndex==1) {
//                                    tool.opt2Reverse(arr, j + 1, k);
//                                }
//                                else if(minIndex==2) {
//                                    tool.opt2Reverse(arr, i + 1, k);
//                                }
//                                else if(minIndex==3)
//                                {
//                                    tool.opt2Reverse(arr, i + 1, j);
//                                    tool.opt2Reverse(arr,j + 1, k);
//                                }
//                                else if(minIndex==4)
//                                {
//                                    int[] temp=Section(j+1,k,arr);
//                                    temp=ReverseArray(temp);
//                                    int[] temp2=Section(i+1,j,arr);
//                                    temp=Combine(temp,temp2);
//                                    arr=Replace(i+1,k,temp,arr);
////                                    this.places =new ArrayList<>(Arrays.asList(bestPath));
//                                }
//                                else if(minIndex==5)
//                                {
//                                    int[] temp=Section(i+1,j,arr);
//                                    temp=ReverseArray(temp);
//                                    int[] temp2=Section(j+1,k,arr);
//                                    temp=Combine(temp2,temp);
//                                    arr=Replace(i+1,k,temp,arr);
////                                    this.places =new ArrayList<>(Arrays.asList(bestPath));
//                                }
//                                else {
//                                    int[] temp=Section(i+1,j,arr);
//                                    int[] temp2=Section(j+1,k,arr);
//                                    temp=Combine(temp2,temp);
//                                    arr=Replace(i+1,k,temp,arr);
////                                    this.places =new ArrayList<>(Arrays.asList(bestPath));
//                                }
//                                improvement = true;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

}