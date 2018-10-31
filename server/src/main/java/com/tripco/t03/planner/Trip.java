package com.tripco.t03.planner;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * The Trip class supports TFFI so it can easily be converted to/from Json by Gson.
 *
 */

public class Trip {

    // The variables in this class should reflect TFFI.
    public int version = 3;
    public String type = "trip";
    public String title;
    public Option options;
    public ArrayList<Place> places;
    public ArrayList<Integer> distances;
    public String map;
    /**
     * Default constructor.
     */
    public Trip(){
        this.title = null;
        this.options = new Option();
        this.places = null;
        this.distances = null;
        this.map = svg();
    }

    /**
     * @param options Option Object.
     * @param places ArrayList of Place.
     * Constructor without title.
     */
    public Trip(Option options, ArrayList<Place> places){
        this.title = null;
        this.options = options;
        this.places = places;
        this.map = svg();
    }

    public Trip(String title, Option options, ArrayList<Place> places, ArrayList<Integer> distances){
        this.title =title;
        this.options=options;
        this.places=places;
        this.distances=distances;
        this.map = svg();
    }
    /**
     * @param title String user defined title for trip.
     * @param options Option Object.
     * @param places ArrayList of Place objects.
     * Constructor with title.
     */
    public Trip(String title, Option options, ArrayList<Place> places){
        this.title=title;
        this.options = options;
        this.places = places;
        this.map = svg();
    }

    //    /** The top level method that does planning.
//     * At this point it just adds the map and distances for the places in order.
//     * It might need to reorder the places in the future.
//     */
//    public void plan() {
//        this.distances = legDistances();
//        if(this.options.optimization.equalsIgnoreCase("short")){
//            Optimize opt = new Optimize(this);
//            Trip optTrip = opt.getOptimalTrip();
//            this.title = optTrip.title;
//            this.options = optTrip.options;
//            this.places = optTrip.places;
//            this.map = optTrip.map;
//            svg();
//        }
//        setRoute();
//    }
    public void plan() {
        optimize();
        this.map=svg();
        this.distances = legDistances();
        setRoute();
    }

    /**
     * Adds the route to the existing map.
     */
    public void setRoute() {

        LineDistance ld = new LineDistance(this.places);
        String route = ld.getCoordinates();
        String fileLines = this.map.substring(0, this.map.length()-16) + route + this.map.substring(this.map.length()-16);
        this.map = fileLines;
    }

    /**
     * Creates an SVG containing the background and the legs of the trip.
     */
    public String svg() {
        String fileLines = "data:image/svg+xml," ;
        try {
            InputStream thisSVGwillNOTwin =Trip.class.getResourceAsStream("/CObackground.svg");
            if(thisSVGwillNOTwin != null){
//                System.out.println("There might be some hope.");
            }else{
//                System.out.println("GIVE UP NOW AND GO HOME!");
            }
            BufferedReader buffy = new BufferedReader(new InputStreamReader(thisSVGwillNOTwin));
            if(buffy.ready()){
//                System.out.println("It found it.....");
                while(buffy.ready()){
                    fileLines+= buffy.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
//            System.out.println("POOP ON BUFFY INPUT STREAM!!");
        }
        return fileLines;
    }

    /**
     * Returns the distances between consecutive places,
     * including the return to the starting point to make a round trip.
     * @return ArrayList<Integer>
     */
    private ArrayList<Integer> legDistances() {

        ArrayList<Integer> dist = new ArrayList<>();

        if(this.options.units.equals("user defined")){
            for (int counter = 0; counter < places.size() - 1; counter++) {
                Distance distance = new Distance(places.get(counter), places.get(counter + 1), options.units, options.unitName, options.unitRadius);
                distance.setDistance();
                dist.add(distance.distance);
            }
            Distance distance = new Distance(places.get(places.size() - 1), places.get(0), options.units, options.unitName, options.unitRadius);
            distance.setDistance();
            dist.add(distance.distance);
        }else {
            for (int counter = 0; counter < places.size() - 1; counter++) {
                Distance distance = new Distance(places.get(counter), places.get(counter + 1), options.units);
                distance.setDistance();
                dist.add(distance.distance);
            }
            Distance distance = new Distance(places.get(places.size() - 1), places.get(0), options.units);
            distance.setDistance();
            dist.add(distance.distance);
        }
        return dist;
    }

    public void optimize(){
        if(places.size() == 0){
            return;
        }
        if(options == null || options.optimization == null){
            return;
        }
        else if("none".equals(options.optimization)){
            return;
        }
        else if("short".equals(options.optimization)){
            nearestN();
        }
        else if("shorter".equals(options.optimization)){
            nearestN();
            TwoOpt();
        }
        else if("shortest".equals(options.optimization)){
            nearestN();
            TwoOpt();
            ThreeOpt();
        }

    }

    public void nearestN(){

        int[] loc = new int[places.size()];
        fillArray(loc);
        int tripDist = 100000000; //ugly hardcode but it is neat compared to a pre-compute
        int shortestT = 0; //sets the current shortest start to be
        for(int i = 0; i < places.size(); i++){ //loop through all the best start locations
            loc[0] = i;
            loc[i] = 0;//set up the new start location

            //call the help method
            int hold = setNearest(loc);//returns length of trip and reorganizes the array
            if(hold < tripDist){
                tripDist = hold;//store the new shortest trip length
                shortestT = i;//set the shortest trip to remember the best starting place
            }
            //reset array
            fillArray(loc);
        }

        //set up the best nearest N
        ArrayList<Place> newPlaces = new ArrayList<Place>();
        loc[0] = shortestT;
        loc[shortestT] = 0;
        setNearest(loc);

        for(int i = 0; i < loc.length;i++){

            newPlaces.add(places.get(loc[i]));

        }

        this.places = newPlaces;

    }

//two opt algorithm

//three opt algorithm


//helper methods

//calcDist already exists above

    public int setNearest(int[] P){
        int nearest = 1;//set the first nearest
        int curbest = 100000000;//set the best as the first option
        int totaldist = 0;
        int hold = 0;
        //0 in P is already "correct" find the thing that needs to go in the next place and repeat place
        for(int i = 1; i < P.length-1;i++){//for each location

            for(int k = i; k < P.length;k++){//find the closest place
                //find the nearest
                hold =  calDist(places.get(P[i-1]),places.get(P[k]));
                if(curbest > hold){
                    curbest = hold;
                    nearest = k;
                }
            }
            //swap the best option into the slot
            hold = P[nearest];//hold the new nearest
            P[nearest] = P[i];//put the i + 1 in nearest
            P[i] = hold;//put nearest in i + 1
            totaldist += curbest; //add the new dist to the total
            curbest = 1000000000;//sloppy reset of value
            nearest = -1;
            hold = -1;//should be unnessesary
        }
        //add the distance of the first to last place to complete the circle
        totaldist += calDist(places.get(P[0]),places.get(P[P.length-1]));

        return totaldist;
    }

    public void fillArray(int[] array){
        for(int i = 0;i < array.length;i++){
            array[i] = i;
        }
    }


    public int calDist(Place origin, Place destination){
        Distance dis=new Distance(origin, destination, this.options.units,
                this.options.unitName, this.options.unitRadius);
        dis.setDistance();
        return dis.distance;
    }


    public void opt2Reverse(int i,int k,Place[] route)
    {
        while(i < k) {
            Place temp = route[i];
            route[i] = route[k];
            route[k] = temp;
            i++; k--;
        }
        this.places =new ArrayList<>(Arrays.asList(route));
    }

    public void opt2Reverse2(int i,int k,int[] indexes)
    {
        while(i < k) {
            int temp = indexes[i];
            indexes[i] = indexes[k];
            indexes[k] = temp;
            i++; k--;
        }
    }

    public void updatePlaces(int[] indexes,Place[] initialPath)
    {
        Place[] copy = new Place[initialPath.length];
        for(int k=0;k<copy.length;k++)
        {
            copy[k]=initialPath[k];
        }
        for(int j=0;j<initialPath.length;j++)
        {
            initialPath[j]=copy[indexes[j]];
        }
        this.places =new ArrayList<>(Arrays.asList(initialPath));
    }

    public void TwoOpt ()
    {
        final int n = places.size();
//        Place[] bestPath = new Place[n];
//        for(int k=0;k<n;k++)
//        {
//            bestPath[k]=this.places.get(k);
//        }
        Place[] initialPath = new Place[n];
        for(int k=0;k<n;k++)
        {
            initialPath[k]=this.places.get(k);
        }
        String units=this.options.units;
        String unitName=this.options.unitName;
        Double uniRadius=this.options.unitRadius;
        DistanceGrid grid=new DistanceGrid(initialPath,units,unitName,uniRadius);
        grid.buildGrid();
        int[] indexes = new int[n];
        for(int k=0;k<n;k++)
        {
            indexes[k]=k;
        }
        if (n > 4) {
            boolean improvement = true;
            while (improvement) {
                improvement = false;
                for (int i = 0; i <= n - 3; i++)
                {
                    for (int j = i + 2; j <= n - 1; j++)
                    {
//                        Place o1, o2, d1, d2;
                        int o1, o2, d1, d2;
                        if (j == n - 1)
                        {
//                            o1 = bestPath[i];
//                            o2 = bestPath[i+1];
//                            d1 = bestPath[j];
//                            d2 = bestPath[0];
                            o1 = indexes[i];
                            o2 = indexes[i+1];
                            d1 = indexes[j];
                            d2 = indexes[0];
                        }
                        else
                        {
//                            o1 = bestPath[i];
//                            o2 = bestPath[i+1];
//                            d1 = bestPath[j];
//                            d2 = bestPath[j+1];
                              o1 = indexes[i];
                              o2 = indexes[i+1];
                              d1 = indexes[j];
                              d2 = indexes[j+1];
                        }
//                        int delta = -calDist(o1, o2) - calDist(d1, d2) + calDist(o1, d1) + calDist(o2, d2);
                          int delta = -grid.distanceGrid[o1][o2] - grid.distanceGrid[d1][d2] + grid.distanceGrid[o1][d1] + grid.distanceGrid[o2][d2];
                        if (delta < 0)
                        {
//                            opt2Reverse(i + 1, j, bestPath);
                            opt2Reverse2(i + 1, j, indexes);
                            improvement = true;
                        }
                    }
                }
            }
        }
        updatePlaces(indexes,initialPath);
    }

    public int findminIndex(int[] delta)
    {
        int min = delta[0];
        int index=0;
        for(int i = 0; i < delta.length; i++)
        {
            if(min > delta[i])
            {
                min = delta[i];
                index=i;
            }
        }
        return index;
    }

    public int[] Section(int i, int j, int[] places)
    {
        int[] section = new int[j-i+1];
        int cout=0;
        for(int k = i; k <= j; k++)
        {
            section[cout]=places[k];
            cout++;
        }
        return section;
    }

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

    public int[] Replace(int i, int j, int[] temp, int[] bestpath)
    {
        int index=0;
        for(int k = i; k <= j; k++)
        {
            bestpath[k]=temp[index];
            index++;
        }
        return bestpath;
    }

    public void ThreeOpt()
    {
        final int n = places.size();
//        Place[] bestPath = new Place[n];
//        for(int k=0;k<n;k++)
//        {
//            bestPath[k]=this.places.get(k);
//        }
        Place[] initialPath = new Place[n];
        for(int k=0;k<n;k++)
        {
            initialPath[k]=this.places.get(k);
        }
        String units=this.options.units;
        String unitName=this.options.unitName;
        Double uniRadius=this.options.unitRadius;
        DistanceGrid grid=new DistanceGrid(initialPath,units,unitName,uniRadius);
        grid.buildGrid();
        int[] indexes = new int[n];
        for(int k=0;k<n;k++)
        {
            indexes[k]=k;
        }
        if (n > 6) {
            boolean improvement = true;
            while (improvement) {
                improvement = false;
                for(int i=0;i<=n-3;i++){
                    for (int j = i+1; j <= n-2; j++) {
                        for (int k = j + 1; k <= n - 1; k++) {
//                            Place o1, o2, d1, d2, p1, p2;
                            int o1, o2, d1, d2, p1, p2;
                            if (k == n - 1) {
//                                o1 = bestPath[i];
//                                o2 = bestPath[i + 1];
//                                d1 = bestPath[j];
//                                d2 = bestPath[j+1];
//                                p1 = bestPath[k];
//                                p2 = bestPath[0];

                                o1 = indexes[i];
                                o2 = indexes[i + 1];
                                d1 = indexes[j];
                                d2 = indexes[j+1];
                                p1 = indexes[k];
                                p2 = indexes[0];

                            } else {
//                                o1 = bestPath[i];
//                                o2 = bestPath[i + 1];
//                                d1 = bestPath[j];
//                                d2 = bestPath[j + 1];
//                                p1 = bestPath[k];
//                                p2 = bestPath[k+1];
                                o1 = indexes[i];
                                o2 = indexes[i + 1];
                                d1 = indexes[j];
                                d2 = indexes[j + 1];
                                p1 = indexes[k];
                                p2 = indexes[k+1];
                            }
                            int[] delta = new int[7];
                            delta[0]= -grid.distanceGrid[o1][o2] -grid.distanceGrid[d1][d2] + grid.distanceGrid[o1][d1] + grid.distanceGrid[o2][d2];
                            delta[1]= -grid.distanceGrid[d1][d2] -grid.distanceGrid[p1][p2] + grid.distanceGrid[d1][p1] + grid.distanceGrid[d2][p2];
                            delta[2]= -grid.distanceGrid[o1][o2] -grid.distanceGrid[p1][p2] + grid.distanceGrid[o1][p1] + grid.distanceGrid[o2][p2];
                            delta[3]= -grid.distanceGrid[o1][o2] -grid.distanceGrid[d1][d2] -grid.distanceGrid[p1][p2] + grid.distanceGrid[o1][d1] + grid.distanceGrid[o2][p1]+ grid.distanceGrid[d2][p2];
                            delta[4]= -grid.distanceGrid[o1][o2] -grid.distanceGrid[d1][d2] -grid.distanceGrid[p1][p2] + grid.distanceGrid[o1][p1] + grid.distanceGrid[d2][o2]+ grid.distanceGrid[d1][p2];
                            delta[5]= -grid.distanceGrid[o1][o2] -grid.distanceGrid[d1][d2] -grid.distanceGrid[p1][p2] + grid.distanceGrid[o1][d2] + grid.distanceGrid[p1][d1]+ grid.distanceGrid[o2][p2];
                            delta[6]= -grid.distanceGrid[o1][o2] -grid.distanceGrid[d1][d2] -grid.distanceGrid[p1][p2] + grid.distanceGrid[o1][d2] + grid.distanceGrid[p1][o2]+ grid.distanceGrid[d1][p2];
                            int minIndex = findminIndex(delta);
                            if (delta[minIndex] < 0) {
                                if(minIndex==0) {
//                                    opt2Reverse(i + 1, j, bestPath);
                                    opt2Reverse2(i + 1, j, indexes);
                                }
                                else if(minIndex==1) {
//                                    opt2Reverse(j + 1, k, bestPath);
                                    opt2Reverse2(j + 1, k, indexes);
                                }
                                else if(minIndex==2) {
//                                    opt2Reverse(i + 1, k, bestPath);
                                    opt2Reverse2(i + 1, k, indexes);
                                }
                                else if(minIndex==3)
                                {
//                                    opt2Reverse(i + 1, j, bestPath);
                                    opt2Reverse2(i + 1, j, indexes);
//                                    opt2Reverse(j + 1, k, bestPath);
                                    opt2Reverse2(j + 1, k, indexes);
                                }
                                else if(minIndex==4)
                                {
                                    int[] temp=Section(j+1,k,indexes);
                                    temp=ReverseArray(temp);
                                    int[] temp2=Section(i+1,j,indexes);
                                    temp=Combine(temp,temp2);
                                    indexes=Replace(i+1,k,temp,indexes);
//                                    this.places =new ArrayList<>(Arrays.asList(bestPath));
                                }
                                else if(minIndex==5)
                                {
                                    int[] temp=Section(i+1,j,indexes);
                                    temp=ReverseArray(temp);
                                    int[] temp2=Section(j+1,k,indexes);
                                    temp=Combine(temp2,temp);
                                    indexes=Replace(i+1,k,temp,indexes);
//                                    this.places =new ArrayList<>(Arrays.asList(bestPath));
                                }
                                else {
                                    int[] temp=Section(i+1,j,indexes);
                                    int[] temp2=Section(j+1,k,indexes);
                                    temp=Combine(temp2,temp);
                                    indexes=Replace(i+1,k,temp,indexes);
//                                    this.places =new ArrayList<>(Arrays.asList(bestPath));
                                }
                                improvement = true;
                            }
                        }
                    }
                }
            }
        }
        updatePlaces(indexes,initialPath);
    }
}