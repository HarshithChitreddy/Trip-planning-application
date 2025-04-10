package com.tco.misc;

import com.tco.requests.Place;
import com.tco.requests.Places;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TestThreeOptimizer {

    private Places places;

    @BeforeEach
    public void setUp() {
        places = new Places();
        places.add(createPlace("36.3932", "25.4615"));     // Santorini
        places.add(createPlace("39.7392", "-104.9903"));   // Denver
        places.add(createPlace("34.0522", "-118.2437"));   // LA
    }

    private Place createPlace(String lat, String lon) {
        Place place = new Place();
        place.put("latitude", lat);
        place.put("longitude", lon);
        return place;
    }

    @Test
    @DisplayName("reddy17: ThreeOptimizer should initialize without errors")
    public void testConstructorWorks() throws BadRequestException {
        ThreeOptimizer optimizer = new ThreeOptimizer(places, 6371.0, "vincenty", 1.0);
        assertNotNull(optimizer.getOptimizedTour());
    }

    @Test
    @DisplayName("reddy17: improve() should run safely")
    public void testImproveMethodSafe() throws BadRequestException {
        ThreeOptimizer optimizer = new ThreeOptimizer(places, 6371.0, "vincenty", 1.0);
        assertDoesNotThrow(optimizer::improve);
    }

    @Test
    @DisplayName("reddy17: getOptimizedTour should return valid list")
    public void testGetOptimizedTourNotNull() throws BadRequestException {
        ThreeOptimizer optimizer = new ThreeOptimizer(places, 6371.0, "vincenty", 1.0);
        assertNotNull(optimizer.getOptimizedTour());
        assertEquals(3, optimizer.getOptimizedTour().size());
    }

    @Test
    @DisplayName("reddy17: improve() should reduce total tour distance")
    public void testImproveReducesDistance() throws BadRequestException {
        ThreeOptimizer optimizer = new ThreeOptimizer(places, 6371.0, "vincenty", 1.0);
        long before = getTotalDistance(optimizer.getOptimizedTour(), optimizer);
        optimizer.improve();
        long after = getTotalDistance(optimizer.getOptimizedTour(), optimizer);
        assertTrue(after <= before, "Tour should be optimized (distance should not increase)");
    }

    @Test
    @DisplayName("reddy17: Already optimal tour should remain unchanged")
    public void testImproveAlreadyOptimal() throws BadRequestException {
        Places orderedPlaces = new Places();
        orderedPlaces.add(createPlace("0", "0"));
        orderedPlaces.add(createPlace("0", "1"));
        orderedPlaces.add(createPlace("1", "1"));

        ThreeOptimizer optimizer = new ThreeOptimizer(orderedPlaces, 6371.0, "vincenty", 1.0);
        Places before = new Places();
        before.addAll(optimizer.getOptimizedTour());

        optimizer.improve();

        assertEquals(before, optimizer.getOptimizedTour(), "Tour should remain unchanged if already optimal");
    }

    @Test
    @DisplayName("reddy17: improve() should handle duplicate places")
    public void testImproveWithDuplicates() throws BadRequestException {
        Places dup = new Places();
        dup.add(createPlace("1", "1"));
        dup.add(createPlace("1", "1"));
        dup.add(createPlace("1", "1"));

        ThreeOptimizer optimizer = new ThreeOptimizer(dup, 6371.0, "vincenty", 1.0);
        assertDoesNotThrow(optimizer::improve);
        assertEquals(3, optimizer.getOptimizedTour().size());
    }

    @Test
    @DisplayName("reddy17: improve() should do nothing on empty input")
    public void testImproveWithEmptyPlaces() throws BadRequestException {
        Places empty = new Places();
        ThreeOptimizer optimizer = new ThreeOptimizer(empty, 6371.0, "vincenty", 1.0);
        assertDoesNotThrow(optimizer::improve);
        assertEquals(0, optimizer.getOptimizedTour().size());
    }

    @Test
    @DisplayName("reddy17: improve() should do nothing on 1-place input")
    public void testImproveWithOnePlace() throws BadRequestException {
        Places one = new Places();
        one.add(createPlace("0", "0"));
        ThreeOptimizer optimizer = new ThreeOptimizer(one, 6371.0, "vincenty", 1.0);
        assertDoesNotThrow(optimizer::improve);
        assertEquals(1, optimizer.getOptimizedTour().size());
    }

    @Test
    @DisplayName("reddy17: improve() should exit early on short response time")
    public void testImproveTimeout() throws BadRequestException {
        ThreeOptimizer optimizer = new ThreeOptimizer(places, 6371.0, "vincenty", 0.000001);
        assertDoesNotThrow(optimizer::improve);
    }

    @Test
    @DisplayName("reddy17: constructor should throw BadRequestException for invalid formula")
    public void testInvalidFormulaThrows() {
        assertThrows(BadRequestException.class, () -> {
            new ThreeOptimizer(places, 6371.0, "invalidFormula", 1.0);
        });
    }

    private long getTotalDistance(Places tour, ThreeOptimizer optimizer) {
        long total = 0;
        for (int i = 0; i < tour.size(); i++) {
            Place from = tour.get(i);
            Place to = tour.get((i + 1) % tour.size());
            total += optimizer.getCalculator().between(from, to, 6371.0);
        }
        return total;
    }
}
