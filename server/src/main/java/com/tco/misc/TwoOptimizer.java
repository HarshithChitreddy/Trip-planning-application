package com.tco.misc;

import com.tco.requests.Place;
import com.tco.requests.Places;

public class TwoOptimizer extends TourOptimizer {
    private Places currentTour;
    private double earthRadius;
    private long startTime;
    private double responseTime;
    private DistanceCalculator calculator;

    public TwoOptimizer(Places places, double radius, double responseSeconds, DistanceCalculator calculator) {
        if (places == null || places.size() < 3) {
            this.currentTour = places;
        } else {
            this.currentTour = new Places();
            this.currentTour.addAll(places);
        }

        this.earthRadius = radius;
        this.responseTime = responseSeconds * 1000; 
        this.calculator = calculator;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void improve() {
        if (currentTour == null || currentTour.size() < 3 || calculator == null) {
            return;
        }

        boolean improved;
        do {
            improved = false;
            for (int i = 0; i <= currentTour.size() - 3; i++) {
                if (System.currentTimeMillis() - startTime > responseTime) {
                    return;
                }

                for (int j = i + 2; j <= currentTour.size() - 1; j++) {
                    if (shouldSwap(i, j)) {
                        performSwap(i, j);
                        improved = true;
                    }
                }
            }
        } while (improved);
    }

    private boolean shouldSwap(int i, int j) {
        Place p1 = currentTour.get(i);
        Place p2 = currentTour.get((i + 1) % currentTour.size());
        Place p3 = currentTour.get(j);
        Place p4 = currentTour.get((j + 1) % currentTour.size());

        double currentDistance = calculator.between(p1, p2, earthRadius) +
                                 calculator.between(p3, p4, earthRadius);

        double newDistance = calculator.between(p1, p3, earthRadius) +
                             calculator.between(p2, p4, earthRadius);

        return newDistance < currentDistance;
    }

    private void performSwap(int i, int j) {
        int left = i + 1;
        int right = j;

        while (left < right) {
            Place temp = currentTour.get(left);
            currentTour.set(left, currentTour.get(right));
            currentTour.set(right, temp);
            left++;
            right--;
        }
    }

    public Places getOptimizedTour() {
        return currentTour;
    }
}
