package com.tco.misc;
import com.tco.requests.Places;
public abstract class TourOptimizer {
    private Places currentTour;
    private double earthRadius;
    private long startTime;
    private double responseTime;
    private DistanceCalculator calculator;

    public Places construct(Places places, Double radius, String formula, Double response){
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

    public abstract void improve();
}