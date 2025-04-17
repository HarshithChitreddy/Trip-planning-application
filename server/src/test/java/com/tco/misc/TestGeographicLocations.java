package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import com.tco.requests.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGeographicLocations {
    Place place;
    Places places;
    Place testPlace1;
    Place testPlace2;
    GeographicLocations geoLoc;
    Integer limit = 10;
    double earthRadius = 3000;
    long distance = 50;
    String formula;
    Distances distances;
    
    @BeforeEach
    public void setUp(){
        place = new Place();
        testPlace1 = new Place();
        testPlace2 = new Place();
        places = new Places();
        geoLoc = new GeographicLocations();
        distances = new Distances();

        testPlace1.put("latitude", "0.0");
        testPlace1.put("longitude", "45.0");

        testPlace2.put("latitude", "45.0");
        testPlace2.put("longitude", "45.0");

        place.put("latitude", "0.0");
        place.put("longitude", "0.0");

        places.add(testPlace1);
        places.add(testPlace2);
        places.add(place);

    }

    @DisplayName("dnweath: Test distances method functions with vincenty")
    @Test
    public void testDistancesVincenty() throws BadRequestException {
        formula = "vincenty";
        
        geoLoc.near(place, 50, earthRadius, formula, limit);
        distances = geoLoc.distances(place, places);

        System.out.println(distances.get(0));

        assertEquals(distances.get(0), 2356l);
        assertEquals(distances.get(1), 3142l);
    }

    @DisplayName("dnweath: Test distances method functions with cosines")
    @Test
    public void testDistancesCosines() throws BadRequestException {
        formula = "cosines";
        
        geoLoc.near(place, 50, earthRadius, formula, limit);
        distances = geoLoc.distances(place, places);

        System.out.println(distances.get(0));

        assertEquals(distances.get(0), 2356l);
        assertEquals(distances.get(1), 3142l);
    }

    @DisplayName("dnweath: Test distances method functions with haversine")
    @Test
    public void testDistancesHaversine() throws BadRequestException {
        formula = "haversine";
        
        geoLoc.near(place, 50, earthRadius, formula, limit);
        distances = geoLoc.distances(place, places);

        System.out.println(distances.get(0));

        assertEquals(distances.get(0), 2356l);
        assertEquals(distances.get(1), 3142l);
    }

    @DisplayName("dnweath: Wrong formula throws BadRequestException")
    @Test
    public void testWrongFormula() throws BadRequestException {
        formula = "sigmoid";
        
        assertThrows(BadRequestException.class, () -> {
            geoLoc.near(place, 50, earthRadius, formula, limit);
        });
    }


}
