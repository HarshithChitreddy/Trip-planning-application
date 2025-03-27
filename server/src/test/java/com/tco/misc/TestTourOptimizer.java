package com.tco.misc;


import org.junit.jupiter.api.Test;

import com.tco.requests.Place;
import com.tco.requests.Places;

import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

public class TestTourOptimizer extends TourOptimizer{
    @Override
    public Places construct(Places places, Double radius, String formula, Double response){
        return null;
    }
    @Override
    public void improve() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'improve'");
    }

    private TourOptimizer tourOpt;
    private Places testPlaces;
    private Place place1 = new Place();
    private Place place2 = new Place();
    private Place place3 = new Place();
    


    @BeforeEach
    public void setUp(){
        tourOpt = new TestTourOptimizer();
        testPlaces = new Places();

        place1.put("latitude", "0.0");
        place1.put("longitude", "120.0");
        place2.put("latitude", "0.0");
        place2.put("longitude", "0.0");
        place3.put("latitude", "-90.0");
        place3.put("longitude", "0.0");
    }

    @Test
    @DisplayName("lennoxxx: Test when constructing null Places list.")
    public void testConstructNullPlaces(){
        Places result = tourOpt.construct(null, 4300.0, "haversine", 1.0);
        assertNull(result);
    }
    
}
