//package com.tripco.t03.planner;
//
//public class DistanceGrid {
//
////    public Distance[][] distanceGrid;
//    public int[][] distanceGrid;
//    private Place[] locations;
//    private String units;
//    private String userDefinedUnitName;
//    private Double userDefinedRadius;
//
//    /**
//     * Default constructor.
//     */
//    public DistanceGrid(){
//        this.distanceGrid = null;
//    }
//
//    /**
//     * Constructor for non user defined units.
//     * @param location Array of places.
//     * @param units String type of units.
//     */
//    public DistanceGrid(Place[] location, String units){
//        this.locations=location;
//        this.units = units;
////        this.distanceGrid = new Distance[locations.length][locations.length];
//        this.distanceGrid = new int[locations.length][locations.length];
//    }
//
//
//    /**
//     * Constructor for user defined units.
//     * @param locations Array of places.
//     * @param units String type of units.
//     * @param udUnitName String name of user defined units.
//     * @param udRadius Double radius of Earth in user defined units.
//     */
//    public DistanceGrid(Place[] locations, String units, String udUnitName, Double udRadius){
//        this.locations = locations;
//        this.units = units;
//        this.userDefinedUnitName = udUnitName;
//        this.userDefinedRadius = udRadius;
////        this.distanceGrid = new Distance[locations.length][locations.length];
//        this.distanceGrid = new int[locations.length][locations.length];
//    }
//
//
//    public void buildGrid(){
//        for(int j=0;j<=this.locations.length-1;j++)
//        {
//            for(int i=0;i<=j;i++)
//            {
//                if(i==j)
//                {
//                    distanceGrid[i][j]=0;
//                    continue;
//                }
//                else
//                {
//                    distanceGrid[i][j]=calDist(locations[i],locations[j]);
//                    distanceGrid[j][i]=distanceGrid[i][j];
//                }
//            }
//        }
//    }
//
//    public int calDist(Place origin, Place destination){
//        Distance dis=new Distance(origin, destination, this.units,
//                this.userDefinedUnitName, this.userDefinedRadius);
//        dis.setDistance();
//        return dis.distance;
//    }

package com.tripco.t03.planner;

import java.util.ArrayList;

    public class DistanceGrid {

        public Integer[][] distanceGrid;
        private Integer[] indexKey;
        private ArrayList<Place> locations;
        private String units;
        private Double userDefinedRadius;

        /**
         * Default constructor.
         */
        public DistanceGrid(){
            this.distanceGrid = null;
        }

        /**
         * Constructor for non user defined units.
         * @param location Array of places.
         * @param units String type of units.
         */
        public DistanceGrid(ArrayList<Place> location, String units, Integer[] key){
            this.locations = new ArrayList<>();
            this.locations.addAll(location);
            this.units = units;
            this.distanceGrid = new Integer[locations.size()][locations.size()];
            this.indexKey=key;
            this.setSamePlace();
        }

        /**
         * Constructor for user defined units.
         * @param locations Array of places.
         * @param units String type of units.
         * @param udRadius Double radius of Earth in user defined units.
         */
        public DistanceGrid(ArrayList<Place> locations, String units, Double udRadius, Integer[] key){
            this.locations = new ArrayList<>();
            this.locations.addAll(locations);
            this.units = units;
            this.userDefinedRadius = udRadius;
            this.distanceGrid = new Integer[locations.size()][locations.size()];
            this.indexKey = key;
            this.setSamePlace();
        }

        /**
         * Builds 2D array of Distance objects.
         */
        public void buildGrid() {
            int row = 0;
            while (row < this.distanceGrid.length) {
                int column = row+1;
                while (column < this.distanceGrid[row].length) {
                    if (this.distanceGrid[row][column] == null) {
                        Place origin = this.locations.get(indexKey[row]);
                        Place destination = this.locations.get(indexKey[column]);
                        if (this.units.equalsIgnoreCase("user defined")) {
                            int temp = Calculate.optDistance(origin, destination, this.userDefinedRadius);
                            this.distanceGrid[row][column] = temp;
                        } else {
                            int temp = Calculate.calcDistance(origin, destination, this.units);
                            this.distanceGrid[row][column] = temp;
                        }
                        distanceGrid[column][row] = this.distanceGrid[row][column];
                    }
                    column++;
                }
                row++;

            }
        }

        /**
         * Helper method for setting the Distance objects with the same origin and destination to null.
         */
        private void setSamePlace(){
            for (int i = 0; i < this.distanceGrid.length; i++) {
                distanceGrid[i][i] = Integer.MAX_VALUE;
            }
        }
    }

