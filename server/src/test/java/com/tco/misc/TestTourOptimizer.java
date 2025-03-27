package com.tco.misc;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import com.tco.requests.*;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
 
public class TestTourOptimizer {
    private Places places;
    private Double radius;
    private String formula;
    private Double response;
    private TourOptimizer testTour;
 
    @BeforeEach
    public void setUp() {
        this.places = new Places();
        this.radius = 300.0;
        this.formula = "vincenty";
        this.response = 1.0;
        
    }

    
    @Test
    @DisplayName("chrisc23: tested Nearest Neighbor with 3 places")
    public void testNearestNeighborWithThreePlaces() throws BadRequestException{
        Place place1 = new Place();
        Place place2 = new Place();
        Place place3 = new Place();
 
        DistanceCalculator calculator = new VincentyCalculator();
 
        place1.put("latitude", "0.0");
        place1.put("longitude", "90.0");
        place2.put("latitude", "0.0");
        place2.put("longitude", "0.0");
        place3.put("latitude", "45.0");
        place3.put("longitude", "0.0");
        
 
        places.add(place1);
        places.add(place2);
        places.add(place3);
        
 
        testTour = new OneOptimizer();
 
        testTour.construct(places, radius, formula, response);
 
        Places optPlaces = testTour.getPlaces();
 
        assertTrue(optPlaces.size() == 3);
        
        assertTrue(calculator.between(optPlaces.get(0), optPlaces.get(1), radius) == calculator.between(place2, place3, radius));
        assertTrue(calculator.between(optPlaces.get(1), optPlaces.get(2), radius) == calculator.between(place3, place1, radius));
        assertTrue(calculator.between(optPlaces.get(2), optPlaces.get(0), radius) == calculator.between(place1, place2, radius));
 
    }
    
}

