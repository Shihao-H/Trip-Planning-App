package com.tripco.t03.planner;


public class Optimize {
    private Trip trip;
    private Place[] longitude;
    private Distance[][] optimizeArray;
    private Distance[] optTrip;
    private int totalDistance;

    public Optimize(Trip trip){
        this.totalDistance = 0;
        this.trip = trip;
        this.longitude = new Place[trip.places.size()];
        this.optimizeArray = new Distance[trip.places.size()][trip.places.size()];
        setLong();
        buildOptArray(0, 0);
        this.optTrip = nearestNeighborInit(0,0, this.optimizeArray.length-1);

    }

    /**
     * Finds the optimal trip.
     * @return Distance[] with optimal trip.
     */
    public Distance[] getOptimalTrip(){
        return optTrip;
    }

    /**
     * Returns the total distance for the trip.
     * @return int.
     */
    public int getTripDistance(){
        return this.totalDistance;
    }

    /**
     * Helper method to calculate the total distance of a trip.
     * @param input Distance[].
     * @return int.
     */
    private int getTotalDistance(Distance[] input){
        int total = 0;
        for (Distance dist: input
             ) {
            total = total + dist.distance;
        }
        return total;
    }

    /**
     * Method to find optimal trip with nearest neighbor algorithm.
     * @param count int used to keep track of column in helper class.
     * @param begin int index of the 1st element.
     * @param end int index of the last element.
     * @return Distance[] with the shortest path.
     */
    private Distance[] nearestNeighborInit(int count, int begin, int end) {
        Distance[] optResult = new Distance[this.optimizeArray.length];
        if(begin < end) {
            int mid = (begin + end) / 2;
            Distance[] left = nearestNeighborInit(begin, begin, mid);
            Distance[] right = nearestNeighborInit(mid+1, mid+1, end);
            int leftDistance = getTotalDistance(left);
            int rightDistance = getTotalDistance(right);
            if(leftDistance < rightDistance) {
                this.totalDistance = leftDistance;
                return left;
            }else{
                this.totalDistance = rightDistance;
                return right;
            }
        }else{
            int optTripIndex = 0;
            int index = nearestNeighbor(count, 0, this.optimizeArray[begin].length-1, optResult);

            System.out.print("::::::::::::::::::NNInit: else: count: " + count + ", index: " + index + ", optTripIndex: " + optTripIndex + ":::::::::::::::::::::::::\n");

            optResult[optTripIndex] = this.optimizeArray[count][index];
            optTripIndex++;
            while(optTripIndex < optResult.length-1){
                count = index;
                index = nearestNeighbor(count, 0, this.optimizeArray[count].length-1, optResult);
                optResult[optTripIndex] = this.optimizeArray[count][index];
                optTripIndex++;
            }
            optResult[optTripIndex] = new Distance(optResult[optTripIndex-1].destination, optResult[0].origin, optResult[0].units);
            optResult[optTripIndex].setDistance();
        }
        return optResult;
    }

    /**
     * Recursive helper method, recursively picks the element from the row.
     * @param count int keeps track of column.
     * @param begin 1st element in array.
     * @param end last element in array.
     * @param check Distance[] used to check if a Place is already in the trip.
     * @return int either the row index of the element to add to the trip, or -1 if there isn't one.
     */
    private int nearestNeighbor(int count, int begin, int end, Distance[] check) {
        //find smallest distance such that destination is not in optTrip
        if(end > begin) {
            int mid = (begin + end) / 2;
            int a = nearestNeighbor(count, begin, mid, check);
            int b =nearestNeighbor(count, mid+1, end, check);
            if((a == -1) || (b == -1)){
                return Math.max(a, b);
            }else {
                if(this.optimizeArray[count][a].distance < this.optimizeArray[count][b].distance){
                    return a;
                }else{
                    return b;
                }
            }
        }else{
            if(this.optimizeArray[count][begin].distance == 0) {
                return -1;
            }else if(isUsed(check, count, begin)){
                return -1;
            }else{
                return begin;
            }
        }
    }

    private boolean isUsed(Distance[] dist, int column, int row){
        String destination = this.optimizeArray[column][row].destination.name;
        String origin = this.optimizeArray[column][row].origin.name;
        for (Distance distObj: dist
             ) {
            if (distObj != null){
                if(distObj.origin.name.equalsIgnoreCase(destination)){
                   return true;
                }
            }

        }
        return false;
    }
    /**
     * Builds the 2D array of Distances.
     * @param i int column index.
     * @param j int row index.
     */
    private void buildOptArray(int i, int j){
            if(i < longitude.length){
                if(j < longitude.length){
                    if(trip.options.units.equalsIgnoreCase("user defined")){
                        optimizeArray[i][j] = new Distance(longitude[i], longitude[j], trip.options.units, trip.options.unitName, trip.options.unitRadius);
                        optimizeArray[i][j].setDistance();
                    }else {
                        optimizeArray[i][j] = new Distance(longitude[i], longitude[j], trip.options.units);
                        optimizeArray[i][j].setDistance();
                    }
                    buildOptArray(i, j+1);
                }else{
                    buildOptArray(i+1, 0);
                }
            }
    }

    /**
     * Builds an sorted array from the ArrayList places.
     */
    private void setLong(){
        copyArray();
        sortLong(0, longitude.length-1);
    }

    /**
     * Copies ArrayList to an array.
     */
    private void copyArray(){
        int i = 0;
        for (Place place: this.trip.places
             ) {
            longitude[i] = place;
            i++;
        }
    }

    /**
     * Sorts an Array of Distances by increasing longitude.
     * @param left lower index.
     * @param right upper index.
     */
    private void sortLong(int left, int right){
        if(right > left) {
            int mid = (right + left) / 2;
            sortLong(left, mid);
            sortLong(mid+1, right);
            mergeLong(left, mid, right);
        }
    }

    /**
     * Helper method called by sortLong to merge the sorted array.
     * @param begin lower index.
     * @param mid mid index.
     * @param end upper index.
     */
    private void mergeLong(int begin, int mid, int end){
        if((begin<mid)&&(longitude[mid].longitude < longitude[begin].longitude)){
            Place temp = longitude[begin];
            longitude[begin]=longitude[mid];
            longitude[mid]=temp;
            mergeLong(begin+1, mid, end);
        }else if((begin==mid)&&(mid<end)){
            mergeLong(begin, mid+1, end);
        }else if((begin < end)){
            mergeLong(begin+1, mid, end);
        }
    }

}
