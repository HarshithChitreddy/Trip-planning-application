package com.tco.misc;

import com.tco.requests.Place;
import com.tco.requests.Places;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestOneOptimizer {

    private Places places;

    @BeforeEach
    public void setUp() {
        places = new Places();
    }

    private Place createPlace(String lat, String lon) {
        Place place = new Place();
        place.put("latitude", lat);
        place.put("longitude", lon);
        return place;
    }

    @Test
    @DisplayName("reddy17: OneOptimizer with 1 place returns original")
    public void testOnePlace() {
        places.add(createPlace("10.0", "10.0"));
        OneOptimizer optimizer = new OneOptimizer();
        Places result = optimizer.construct(places, 6371.0, "vincenty", 1.0);
        assertEquals(places, result);
    }

    @Test
    @DisplayName("reddy17: OneOptimizer with 2 places returns original")
    public void testTwoPlaces() {
        places.add(createPlace("10.0", "10.0"));
        places.add(createPlace("20.0", "20.0"));
        OneOptimizer optimizer = new OneOptimizer();
        Places result = optimizer.construct(places, 6371.0, "vincenty", 1.0);
        assertEquals(places, result);
    }

    @Test
    @DisplayName("reddy17: OneOptimizer with 3 places returns copy")
    public void testThreePlaces() {
        places.add(createPlace("10.0", "10.0"));
        places.add(createPlace("20.0", "20.0"));
        places.add(createPlace("30.0", "30.0"));
        OneOptimizer optimizer = new OneOptimizer();
        Places result = optimizer.construct(places, 6371.0, "vincenty", 1.0);
        assertEquals(places.size(), result.size());
    }

    @Test
    @DisplayName("reddy17: OneOptimizer with 4 places returns copy")
    public void testFourPlaces() {
        places.add(createPlace("10.0", "10.0"));
        places.add(createPlace("20.0", "20.0"));
        places.add(createPlace("30.0", "30.0"));
        places.add(createPlace("40.0", "40.0"));
        OneOptimizer optimizer = new OneOptimizer();
        Places result = optimizer.construct(places, 6371.0, "vincenty", 1.0);
        assertEquals(places.size(), result.size());
    }

    @Test
    @DisplayName("reddy17: OneOptimizer improve() runs safely")
    public void testImproveDoesNotThrow() {
        places.add(createPlace("10.0", "10.0"));
        places.add(createPlace("20.0", "20.0"));
        places.add(createPlace("30.0", "30.0"));
        OneOptimizer optimizer = new OneOptimizer();
        optimizer.construct(places, 6371.0, "vincenty", 1.0);
        assertDoesNotThrow(() -> optimizer.improve());
    }
}
