package com.tco.misc;

import com.tco.requests.Place;
import com.tco.requests.Places;

public class ThreeOptimizer extends TourOptimizer {

    private Places currentTour;
    private double earthRadius;
    private long startTime;
    private double responseTime;
    private DistanceCalculator calculator;

    public ThreeOptimizer(Places places, double radius, String formula, double response) throws BadRequestException {
        this.currentTour = (places == null || places.size() < 3) ? places : new Places();
        if (places != null && places.size() >= 3) {
            this.currentTour.addAll(places);
        }

        this.earthRadius = radius;
        this.responseTime = response * 1000;
        this.calculator = new CalculatorFactory().get(formula); 
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void improve() {
        // To be implemented in next pr
        System.out.println("ThreeOptimizer improve() method defined.");
    }

    public Places getOptimizedTour() {
        return currentTour;
    }
}
