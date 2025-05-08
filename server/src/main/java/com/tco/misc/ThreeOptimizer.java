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
        this.responseTime = response * 1000; // convert to milliseconds
        this.calculator = new CalculatorFactory().get(formula); 
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

            for (int i = 0; i < currentTour.size() - 2; i++) {
                for (int j = i + 1; j < currentTour.size() - 1; j++) {
                    for (int k = j + 1; k < currentTour.size(); k++) {

                        if (System.currentTimeMillis() - startTime > responseTime) {
                            return;
                        }

                        if (tryImproveSegment(i, j, k)) {
                            improved = true;
                        }
                    }
                }
            }
        } while (improved);
    }

    private boolean tryImproveSegment(int i, int j, int k) {
        long originalDistance = segmentDistance(i, j, k);
        reverseSegment(i + 1, k);
        long newDistance = segmentDistance(i, j, k);

        if (newDistance < originalDistance) {
            return true;
        } else {
            reverseSegment(i + 1, k); // Undo the change
            return false;
        }
    }

    private long segmentDistance(int i, int j, int k) {
        Place A = currentTour.get(i);
        Place B = currentTour.get(j);
        Place C = currentTour.get(k);
        Place next = currentTour.get((k + 1) % currentTour.size());

        return calculator.between(A, B, earthRadius) +
               calculator.between(B, C, earthRadius) +
               calculator.between(C, next, earthRadius);
    }

    private void reverseSegment(int start, int end) {
        while (start < end) {
            Place temp = currentTour.get(start);
            currentTour.set(start, currentTour.get(end));
            currentTour.set(end, temp);
            start++;
            end--;
        }
    }

    public Places getOptimizedTour() {
        return currentTour;
    }

    public DistanceCalculator getCalculator() {
        return calculator;
    }
}
