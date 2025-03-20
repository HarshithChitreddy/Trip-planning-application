package com.tco.misc;

import org.junit.jupiter.api.Test;

import com.tco.requests.Place;
import com.tco.requests.Places;

import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

public class TestNoOptimizer {
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
    @DisplayName("chrisc23: test case for one place")
    @Test
    public void testConstructReturnsOriginalPlacesForOnePlace() {
        
        places.add(createPlace("100.0", "100.0"));  
        NoOptimizer noOptimizer = new NoOptimizer();
        Places result = noOptimizer.construct(places, 10.0, "someFormula", 0.0);
        assertEquals(places, result);  
    }
    @DisplayName("chrisc23: test case for Two place")
    @Test
    public void testConstructReturnsOriginalPlacesForTwoPlaces() {
        
        places.add(createPlace("100.0", "100.0"));  
        places.add(createPlace("-100.0", "100.0"));
        NoOptimizer noOptimizer = new NoOptimizer();
        Places result = noOptimizer.construct(places, 10.0, "someFormula", 0.0);
        assertEquals(places, result);  
    }
    @DisplayName("chrisc23: test case for Three place")
    @Test
    public void testConstructReturnsOriginalPlacesForThreePlaces() {
        places.add(createPlace("100.0", "-100.0"));  
        places.add(createPlace("100.0", "100.0"));  
        places.add(createPlace("-100.0", "100.0"));
        NoOptimizer noOptimizer = new NoOptimizer();
        Places result = noOptimizer.construct(places, 10.0, "someFormula", 0.0);
        assertEquals(places, result);  
    }
    @DisplayName("kjell: test case for Four place (test pattern set by chris23)")
    @Test
    public void testConstructReturnsOriginalPlacesForFourPlaces() {
        places.add(createPlace("100.0", "-100.0"));  
        places.add(createPlace("100.0", "100.0"));  
        places.add(createPlace("-100.0", "100.0"));
        places.add(createPlace("-100.0", "-100.0"));
        NoOptimizer noOptimizer = new NoOptimizer();
        Places result = noOptimizer.construct(places, 10.0, "someFormula", 0.0);
        assertEquals(places, result);  
    }
    
}
