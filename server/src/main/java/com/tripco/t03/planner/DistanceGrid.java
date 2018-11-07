
package com.tripco.t03.planner;

import java.util.ArrayList;

    class DistanceGrid {
        Long[][] distanceGrid;
        private Integer[] indexKey;
        private ArrayList<Place> locations;
        private String units;
        private Double userDefinedRadius;

        /**
         * Default constructor.
         */
        DistanceGrid(){
            this.distanceGrid = null;
        }

        /**
         * Constructor for non user defined units.
         * @param location Array of places.
         * @param units String type of units.
         */
        DistanceGrid(ArrayList<Place> location, String units, Integer[] key){
            this.locations = new ArrayList<>();
            this.locations.addAll(location);
            this.units = units;
            this.distanceGrid = new Long[locations.size()][locations.size()];
            this.indexKey=key;
            this.setSamePlace();
        }

        /**
         * Constructor for user defined units.
         * @param locations Array of places.
         * @param units String type of units.
         * @param udRadius Double radius of Earth in user defined units.
         */
        DistanceGrid(ArrayList<Place> locations, String units, Double udRadius, Integer[] key){
            this.locations = new ArrayList<>();
            this.locations.addAll(locations);
            this.units = units;
            this.userDefinedRadius = udRadius;
            this.distanceGrid = new Long[locations.size()][locations.size()];
            this.indexKey = key;
            this.setSamePlace();
        }

        /**
         * Builds 2D array of Distance objects.
         */
        void buildGrid() {
            int row = 0;
            while (row < this.distanceGrid.length) {
                int column = row+1;
                while (column < this.distanceGrid[row].length) {
                    if (this.distanceGrid[row][column] == null) {
                        Place origin = this.locations.get(indexKey[row]);
                        Place destination = this.locations.get(indexKey[column]);
                        if (this.units.equalsIgnoreCase("user defined")) {
                            long temp = Calculate.optDistance(origin, destination,
                                                           this.userDefinedRadius);
                            this.distanceGrid[row][column] = temp;
                        } else {
                            Long temp = Calculate.calcDistance(origin, destination,
                                                               this.units);
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
                distanceGrid[i][i] = Long.MAX_VALUE;
            }
        }
    }

