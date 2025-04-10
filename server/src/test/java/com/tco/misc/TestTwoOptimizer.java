package com.tco.misc;

import com.tco.requests.Places;
import com.tco.requests.Place;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TestTwoOptimizer {

    private Places places;
    private DistanceCalculator mockCalculator;

    private static class MockDistanceCalculator implements DistanceCalculator {
        @Override
        public long between(GeographicCoordinate p1, GeographicCoordinate p2, double radius) {
            double lat1 = p1.latRadians();
            double lon1 = p1.lonRadians();
            double lat2 = p2.latRadians();
            double lon2 = p2.lonRadians();
            return (long) Math.sqrt(Math.pow(lat1 - lat2, 2) + Math.pow(lon1 - lon2, 2));
        }
    }

    @BeforeEach
    public void setUp() {
        mockCalculator = new MockDistanceCalculator();

        places = new Places();
        places.add(createPlace("36.3932", "25.4615"));
        places.add(createPlace("39.7392", "-104.9903"));
        places.add(createPlace("34.0522", "-118.2437"));
    }

    private Place createPlace(String lat, String lon) {
        Place place = new Place();
        place.put("latitude", lat);
        place.put("longitude", lon);
        return place;
    }

    @Test
    @DisplayName("reddy17: Improve should run without errors for valid inputs")
    public void testImproveRunsWithoutErrors() {
        TwoOptimizer optimizer = new TwoOptimizer(places, 6371.0, 1.0, mockCalculator);
        assertDoesNotThrow(optimizer::improve);
    }

    @Test
    @DisplayName("reddy17: Improve should work when run multiple times")
    public void testImproveMultipleExecutions() {
        TwoOptimizer optimizer = new TwoOptimizer(places, 6371.0, 1.0, mockCalculator);
        optimizer.improve();
        assertDoesNotThrow(optimizer::improve);
    }

    @Test
    @DisplayName("reddy17: Improve should handle empty places list")
    public void testImproveWithEmptyList() {
        Places emptyPlaces = new Places();
        TwoOptimizer optimizer = new TwoOptimizer(emptyPlaces, 6371.0, 1.0, mockCalculator);
        assertDoesNotThrow(optimizer::improve);
    }

    @Test
    @DisplayName("reddy17: Improve should not fail with only two places")
    public void testImproveWithTwoPlaces() {
        Places twoPlaces = new Places();
        twoPlaces.add(createPlace("48.8566", "2.3522"));
        twoPlaces.add(createPlace("51.5074", "-0.1278"));
        TwoOptimizer optimizer = new TwoOptimizer(twoPlaces, 6371.0, 1.0, mockCalculator);
        assertDoesNotThrow(optimizer::improve);
    }

    @Test
    @DisplayName("reddy17: Improve should not alter optimal tour")
    public void testImproveNoChangesNeeded() {
        Places tourCopy = new Places();
        tourCopy.addAll(places);
        TwoOptimizer optimizer = new TwoOptimizer(places, 6371.0, 1.0, mockCalculator);
        optimizer.improve();
        assertEquals(tourCopy, optimizer.getOptimizedTour());
    }

    @Test
    @DisplayName("lennoxxx: Optimizer should initialize places correctly")
    public void testConstructorInitializesToursCorrectly() {
        TwoOptimizer optimizer = new TwoOptimizer(places, 4720.0, 1.0, mockCalculator);
        assertEquals(3, optimizer.getOptimizedTour().size());
    }

    @Test
    @DisplayName("reddy17: Improve should stop early due to response time limit")
    public void testImproveStopsDueToResponseTime() {
        TwoOptimizer optimizer = new TwoOptimizer(places, 6371.0, 0.00001, mockCalculator);
        optimizer.improve();
        assertNotNull(optimizer.getOptimizedTour());
    }

    @Test
    @DisplayName("reddy17: Improve should do nothing when calculator is null")
    public void testImproveWithNullCalculator() {
        TwoOptimizer optimizer = new TwoOptimizer(places, 6371.0, 1.0, null);
        optimizer.improve();
        assertNotNull(optimizer.getOptimizedTour());
    }

    @Test
    @DisplayName("reddy17: Improve should handle duplicate places gracefully")
    public void testImproveWithDuplicatePlaces() {
        places.add(createPlace("36.3932", "25.4615"));
        TwoOptimizer optimizer = new TwoOptimizer(places, 6371.0, 1.0, mockCalculator);
        assertDoesNotThrow(optimizer::improve);
    }

    @Test
    @DisplayName("reddy17: Optimize should reduce total distance if not optimal")
    public void testTourDistanceReduces() {
        Places reversed = new Places();
        reversed.add(createPlace("1", "1"));
        reversed.add(createPlace("0", "1"));
        reversed.add(createPlace("0", "0"));
        reversed.add(createPlace("1", "0")); 
    
        TwoOptimizer optimizer = new TwoOptimizer(reversed, 6371.0, 1.0, mockCalculator);
    
        long before = getTotalDistance(optimizer.getOptimizedTour(), mockCalculator);
        optimizer.improve();
        long after = getTotalDistance(optimizer.getOptimizedTour(), mockCalculator);
    
        assertTrue(after <= before, "Total tour distance should reduce after optimization");
    }
    
    private long getTotalDistance(Places places, DistanceCalculator calculator) {
        long total = 0;
        for (int i = 0; i < places.size(); i++) {
            Place from = places.get(i);
            Place to = places.get((i + 1) % places.size());
            total += calculator.between(from, to, 6371.0);
        }
        return total;
    }
    

    @Test
    @DisplayName("reddy17: Optimize should not change already optimal tour")
    public void testTourOrderUnchangedWhenOptimal() {
        Places square = new Places();
        square.add(createPlace("0", "0"));
        square.add(createPlace("0", "1"));
        square.add(createPlace("1", "1"));
        square.add(createPlace("1", "0"));
    
        TwoOptimizer optimizer = new TwoOptimizer(square, 6371.0, 1.0, mockCalculator);
    
        Places before = new Places();
        before.addAll(optimizer.getOptimizedTour());
    
        optimizer.improve();
        Places after = optimizer.getOptimizedTour();
    
        assertEquals(before.toString(), after.toString(), "Tour should remain unchanged when already optimal");
    }
    @Test
    @DisplayName("reddy17: Optimize should handle many places without crashing")
    public void testWithManyPlaces() {
        Places many = new Places();
        for (int i = 0; i < 50; i++) {
            many.add(createPlace(String.valueOf(i), String.valueOf(i)));
        }
    
        TwoOptimizer optimizer = new TwoOptimizer(many, 6371.0, 2.0, mockCalculator);
        assertDoesNotThrow(optimizer::improve, "Should handle many places without throwing errors");
    }
    @Test
    @DisplayName("reddy17: Optimize handles identical locations gracefully")
    public void testWithIdenticalLocations() {
        Places identical = new Places();
        for (int i = 0; i < 5; i++) {
            identical.add(createPlace("10", "10"));
        }
    
        TwoOptimizer optimizer = new TwoOptimizer(identical, 6371.0, 1.0, mockCalculator);
        assertDoesNotThrow(optimizer::improve);
        assertEquals(5, optimizer.getOptimizedTour().size());
    }            
}
