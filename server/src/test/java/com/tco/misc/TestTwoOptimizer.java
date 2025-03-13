package com.tco.misc;

import com.tco.requests.Places;
import com.tco.requests.Place;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TestTwoOptimizer {

    private TwoOptimizer twoOptimizer;
    private Places places;

    @BeforeEach
    public void setUp() {
        twoOptimizer = new TwoOptimizer();
        places = new Places();

        places.add(createPlace("36.3932", "25.4615")); 
        places.add(createPlace("39.7392", "-104.9903")); 
        places.add(createPlace("34.0522", "-118.2437")); 
    }

    private Place createPlace(String lat, String lng) {
        Place place = new Place(); 
        place.put("latitude", lat);
        place.put("longitude", lng);
        return place;
    }

    @Test
    @DisplayName("HarshithChitreddy: Test improve() runs without exceptions")
    public void testImproveRunsWithoutErrors() {
        assertDoesNotThrow(() -> twoOptimizer.improve(), "Improve should execute without throwing exceptions.");
    }

    @Test
    @DisplayName("HarshithChitreddy: Test improve() multiple times")
    public void testImproveMultipleExecutions() {
        twoOptimizer.improve();
        assertDoesNotThrow(() -> twoOptimizer.improve(), "Improve should handle repeated execution safely.");
    }

    @Test
    @DisplayName("HarshithChitreddy: Test improve() with empty Places list")
    public void testImproveWithEmptyList() {
        Places emptyPlaces = new Places();
        twoOptimizer.construct(emptyPlaces, 6371.0, "haversine", 1.0);
        assertDoesNotThrow(() -> twoOptimizer.improve(), "Improve should not fail when places list is empty.");
    }

    @Test
    @DisplayName("HarshithChitreddy: Test improve() with only two places")
    public void testImproveWithTwoPlaces() {
        Places minimalPlaces = new Places();
        minimalPlaces.add(createPlace("48.8566", "2.3522"));  
        minimalPlaces.add(createPlace("51.5074", "-0.1278")); 

        twoOptimizer.construct(minimalPlaces, 6371.0, "haversine", 1.0);
        assertDoesNotThrow(() -> twoOptimizer.improve(), "Improve should not modify a tour with only two locations.");
    }

    @Test
    @DisplayName("HarshithChitreddy: Test improve() after valid construct() call")
    public void testImproveAfterConstruct() {
        twoOptimizer.construct(places, 6371.0, "vincenty", 1.5);
        assertDoesNotThrow(() -> twoOptimizer.improve(), "Improve should run safely after a valid construct call.");
    }

    @Test
    @DisplayName("HarshithChitreddy: Test improve() does not alter places if no swaps are needed")
    public void testImproveNoChangesNeeded() {
        twoOptimizer.construct(places, 6371.0, "vincenty", 1.0);
        Places originalTour = new Places();
        originalTour.addAll(places);

        twoOptimizer.improve();

        assertEquals(originalTour, places, "Improve should not alter the tour if no swaps improve the route.");
    }
}
