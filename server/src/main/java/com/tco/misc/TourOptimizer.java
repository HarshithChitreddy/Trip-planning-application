package com.tco.misc;
import com.tco.requests.Places;
 
import com.tco.requests.Place;
 
public abstract class TourOptimizer {
 
    CalculatorFactory calculatorFactory = new CalculatorFactory();
    DistanceCalculator calculator;
    Places placesFinal;
   int[] indexBestPlaces;
   
   
    public Places construct(Places places, Double radius, String formula, Double response) throws BadRequestException{
        long startTime = System.currentTimeMillis();
        int placesSize = places.size();
        int[] bestTour = new int[placesSize];
        this.calculator = calculatorFactory.get(formula);
 
        for(Place startingPlace : places){
 
            if(System.currentTimeMillis() - startTime > response){
                break;
                }  
            int currentCity = places.indexOf(startingPlace);
            int[] currentTour = nearestNeighbor(places, currentCity, radius);
            long currentDistance = sumOfDistance(currentTour,places, radius);
            long bestDistance = Long.MAX_VALUE;
            if(currentDistance < bestDistance){
 
                bestDistance = currentDistance;
                bestTour = currentTour;
            }
 
       
    }

    int[] reorderedTour = reorderTour(bestTour);
    return convertToPlaces(reorderedTour,places);
}
 
 
 
private Places convertToPlaces(int[] bestTour,Places places){
    Places tour = new Places();
    this.indexBestPlaces = bestTour;
    for(int i = 0; i < places.size();i++){
       
        tour.add(places.get(bestTour[i]));
 
    }
    this.placesFinal = tour;
    return tour;
}

private int[] reorderTour(int[] bestTour) {
    int index = 0;
    int[] newOrder = new int[bestTour.length];
    int tracker = 0;
    
    // find original starting places
    for (int i = 0; i < bestTour.length; ++i) {
        if (bestTour[i] == 0) {
            index = i;
            break;
        }
    }

    while (tracker < bestTour.length) {
        newOrder[tracker] = bestTour[index % bestTour.length];
        ++tracker;
        ++index;
    }

    return newOrder;

}
 
private long sumOfDistance(int[] currentTour, Places places, Double radius){
    long result = 0l;
 
    for(int i = 0; i < currentTour.length; i++){
        int current = currentTour[i];
        int next = currentTour[(i+1) % currentTour.length];
        result += calculator.between(places.get(current), places.get(next),radius);
 
    }
    return result;
}
 
 
 
 
    private int[] nearestNeighbor(Places places, int startingPlace, Double radius){
 
        int placesSize = places.size();
        boolean[] visited = new boolean[placesSize];  
        int[] tour = new int[placesSize];  
        long[][] distanceMatrix = calculateDistanceMatrix(places, radius);  
        int currentCity = startingPlace;  
        tour[0] = currentCity;  
        visited[currentCity] = true;  
   
        int citiesVisited = 1;
   
        while (citiesVisited < placesSize) {  
            long minDistance = Long.MAX_VALUE;
            int nearestCity = -1;
   
           
            for (int i = 0; i < placesSize; i++) {
                if (!visited[i] && distanceMatrix[currentCity][i] < minDistance) {
                    minDistance = distanceMatrix[currentCity][i];
                    nearestCity = i;
                }
            }
   
           
            visited[nearestCity] = true;
            tour[citiesVisited] = nearestCity;
            currentCity = nearestCity;  
            citiesVisited++;  
        }
   
        return tour;  
    }
 
 
 
    private long[][] calculateDistanceMatrix(Places places, Double radius){
        Place[][] placesMatrix = new Place[places.size()][places.size()];
        long[][] distanceMatrix = new long[places.size()][places.size()+1];
        for(int i = 0; i < places.size(); i++){
           
                distanceMatrix[i][0] = calculator.between(places.get(0),places.get(places.size()-1),radius);
               
            for(int j = 1; j < places.size(); j++){
               
                placesMatrix[i][j] = places.get(j);
                distanceMatrix[i][j] = calculator.between(places.get(i),places.get(j),radius);
            }
        }
       
        return distanceMatrix;
    }
    public void improve(){};
 
    public Places getPlaces() {
        return placesFinal;
    }
    public int[] getIndexBestTour(){
        return indexBestPlaces;
    
    }
   
}