package com.tco.misc;

import com.tco.requests.Places;

/* TEMPORARY COMMENT BLOCK - REMOVE BEFORE MERGE!
 * nearestNeighborWithOptimization(cities) {
 * for each starting city (as time permits) O(n)
 * add the starting city to the tour and remove from the list of unvisited cities
 * while there are unvisited cities remaining - O(n)
 * add the nearest unvisited city from the last city to the tour - O(n)
 * improve the tour with 2-opt or 3-opt (if time permits) - O(n?)
 * return the tour with the shortest distance
 * }
 */

public class OneOptimizer extends TourOptimizer {
    
    private Places currentTour;
    private double earthRadius;
    private long startTime;
    private double responseTime;
    private DistanceCalculator calculator;
    

    @Override
    public Places construct(Places places, Double radius, String formula, Double response) {
        if (places == null || places.size() < 3) {
            return places;
        }

        this.currentTour = new Places();
        this.currentTour.addAll(places);
        this.earthRadius = radius;
        this.responseTime = response * 1000; 
        this.startTime = System.currentTimeMillis();

        return currentTour;
    }

    @Override
    public void improve() {

    }


}