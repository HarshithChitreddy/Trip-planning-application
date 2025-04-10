package com.tco.misc;

import com.tco.requests.Place;
import com.tco.requests.Places;

public class ThreeOptimizer extends TourOptimizer{
    private Places currentTour;
    private double earthRadius;
    private long startTime;
    private double responseTime;
    private DistanceCalculator calculator;
    
    public ThreeOptimizer(Places places, double radius, double responseSeconds, DistanceCalculator calculator){
        if (currentTour == null || currentTour.size() < 3 || calculator == null) {
            return;
        }
    }

    @Override
    public void improve(){

    }

    public Places getOptimizedTour(){
        return currentTour;
    }
}
