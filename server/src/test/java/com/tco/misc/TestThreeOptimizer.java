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
}
