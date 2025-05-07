package com.tco.misc;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import com.tco.requests.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
 
public class TestTourOptimizer {
    private Places places;
    private Double radius;
    private String formula;
    private Double response;
    private TourOptimizer testTour;
    private DistanceCalculator calculator;
 
    @BeforeEach
    public void setUp() {
        this.places = new Places();
        this.radius = 300.0;
        this.formula = "vincenty";
        this.response = 1.0;
        testTour = new OneOptimizer();
        calculator = new VincentyCalculator();
        
    }

    @Test
    @DisplayName("dnweath: Test Nearest Neighbor with empty list")
        public void testNearestNeighborWithNoPlaces() throws BadRequestException {
            testTour.construct(places, radius, formula, response);
            Places optPlaces = testTour.getPlaces();

            assertTrue(optPlaces.size() == 0);
        }
         @Test
    @DisplayName("chrisc23:test index of the best tour with four places") 
    public void testIndexWithFourPlaces() throws BadRequestException{
        Place place1 = new Place();
        Place place2 = new Place();
        Place place3 = new Place();
        Place place4 = new Place();

        place1.put("latitude", "0.0"); 
        place1.put("longitude", "90.0");
        place2.put("latitude", "0.0"); 
        place2.put("longitude", "0.0");
        place3.put("latitude", "0.0"); 
        place3.put("longitude", "90.0");  
        place4.put("latitude", "0.0");
        place4.put("longitude", "0.0"); 

        places.add(place1);
        places.add(place2);
        places.add(place3);
        places.add(place4);

        testTour.construct(places, radius, formula, response);

        int[] bestIndexTour = testTour.getIndexBestTour();
        //int[] result = {1,3,0,2};
        int[] result = {0,2,1,3};

        assertArrayEquals(bestIndexTour,result);

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
        
        // assertTrue(calculator.between(optPlaces.get(0), optPlaces.get(1), radius) == calculator.between(place2, place3, radius));
        // assertTrue(calculator.between(optPlaces.get(1), optPlaces.get(2), radius) == calculator.between(place3, place1, radius));
        // assertTrue(calculator.between(optPlaces.get(2), optPlaces.get(0), radius) == calculator.between(place1, place2, radius));
 
    }

    /*
     * Should reorder the places so that you only travel from 0.0, 90.0 -> 0.0, 0.0 twice
     */
    @Test
    @DisplayName("dnweath:test Nearest Neighbor with 4 places") 
    public void testNearestNeighborWithFourPlaces() throws BadRequestException{
        Place place1 = new Place();
        Place place2 = new Place();
        Place place3 = new Place();
        Place place4 = new Place();

        place1.put("latitude", "0.0");
        place1.put("longitude", "90.0");
        place2.put("latitude", "0.0");
        place2.put("longitude", "0.0");
        place3.put("latitude", "0.0");
        place3.put("longitude", "90.0");
        place4.put("latitude", "0.0");
        place4.put("longitude", "0.0");

        places.add(place1);
        places.add(place2);
        places.add(place3);
        places.add(place4);

        testTour.construct(places, radius, formula, response);
        Places optPlaces = testTour.getPlaces();


        long calculatedDistances = calculator.between(optPlaces.get(0), optPlaces.get(1), radius)
                                   + calculator.between(optPlaces.get(1), optPlaces.get(2), radius)
                                   + calculator.between(optPlaces.get(2), optPlaces.get(3), radius)
                                   + calculator.between(optPlaces.get(3), optPlaces.get(0), radius);



        assertTrue(calculatedDistances == 942l);

    }
    @Test
    @DisplayName("chrisc23:test Nearest Neighbor with 5 places") 
    public void testNearestNeighborWithFivePlaces() throws BadRequestException{
        Place place1 = new Place();
        Place place2 = new Place();
        Place place3 = new Place();
        Place place4 = new Place();
        Place place5 = new Place();

        place1.put("latitude", "0.0");
        place1.put("longitude", "90.0");
        place2.put("latitude", "0.0");
        place2.put("longitude", "0.0");
        place3.put("latitude", "0.0");
        place3.put("longitude", "90.0");
        place4.put("latitude", "0.0");
        place4.put("longitude", "0.0");
        place5.put("latitude", "0.0");
        place5.put("longitude", "45.0");

        places.add(place1);
        places.add(place2);
        places.add(place3);
        places.add(place4);
        places.add(place5);

        testTour.construct(places, radius, formula, response);
        Places optPlaces = testTour.getPlaces();


        long calculatedDistances = calculator.between(optPlaces.get(0), optPlaces.get(1), radius)
                                   + calculator.between(optPlaces.get(1), optPlaces.get(2), radius)
                                   + calculator.between(optPlaces.get(2), optPlaces.get(3), radius)
                                   + calculator.between(optPlaces.get(3), optPlaces.get(4), radius)
                                   + calculator.between(optPlaces.get(4), optPlaces.get(0), radius);

        assertTrue(calculatedDistances == 943l);

    }
}
    


