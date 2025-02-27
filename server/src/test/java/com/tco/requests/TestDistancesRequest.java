package com.tco.requests;

import com.tco.misc.BadRequestException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.ArrayList;

public class TestDistancesRequest{
    private Distances distances;
    private Places places;
    private Place place1 = new Place();
    private Place place2 = new Place();
    private Place place3 = new Place();
    private DistancesRequest distReq;
    private double earthRadius;
    private String formula;
    //private List<Places> testPlaces;

    @BeforeEach
    public void setUpDR(){
        this.distances = new Distances();
        this.places = new Places();
        place1.put("latitude", "0.0");
        place1.put("longitude", "0.0");

        place2.put("latitude", "0.0");
        place2.put("longitude", "0.0");

        place3.put("latitude", "0.0");
        place3.put("longitude", "0.0");
        this.distReq = new DistancesRequest();
        //this.testPlaces = distReq.getPlaces();
    }

    @Test
    @DisplayName("lennoxxx: Test for getting the formula.")
    public void testGetFormula(){
        distReq.formula = "vincenty";
        assertEquals("vincenty", distReq.getFormula(), "getFormula should return the assigned formula.");
    }
    @Test
    @DisplayName("lennoxxx: Test for getting the earthRadius.")
    public void testGetEarthRadius(){
        distReq.buildResponse();
        distReq.earthRadius = 3963.19;
        assertEquals("3963.19", distReq.getEarthRadius(), "getEarthRadius should return the assigned earthRadius.");
    }

    // @Test
    // @DisplayName("lennoxxx: Test for getting the list of distances.")
    // public void testGetPlacesNotEmpty(){
    //     testPlaces.add("Test Place");
    //     assertFalse(testPlaces.isEmpty(), "The testPlaces list should have a population of at least one.");
    // }

    @Test
    @DisplayName("lennoxxx: Test for gauging whether or not getPlaces() returns Null.")
    public void testGetPlacesNotNull(){
        distReq.buildResponse();
        assertNotNull(distReq.getPlaces(), "Calling getPlaces() should not return Null.");
    }

    @Test
    @DisplayName("lennoxxx: Test for gauging whether or not getDistances() returns Null.")
    public void testGetDistancesNotNull(){
        distReq.buildResponse();
        assertNotNull(distReq.getDistances(), "Calling getDistances() should not return Null.");
    }

    // @Test
    // @DisplayName("lennoxxx: Test for one place (vincenty).")
    // public void testOnePlaceVin(){
    //     distReq.formula = "vincenty";
    //     distReq.buildResponse();

    //     assertEquals(1, distReq.getDistances().size(), "There should be one item in the distanceList");
    //     assertEquals(0L, distReq.getDistances().get(0), "Since there is only one distance in the list, it should output zero.");
    // }

    // @Test
    // @DisplayName("lennoxxx: Test for invalid argument (formula).")
    // public void testInvalidFormula(){
    //     distReq.formula = "NoCalculator";
    //     distReq.buildResponse();

    //     assertThrows(BadRequestException.class, distReq::buildResponse, "This error case for an Invalid Argument should be handled by the BadRequestException class.");
    // }

    // @Test
    // @DisplayName("lennoxxx: Test for null argument (formula).")
    // public void testNullFormula(){
    //     distReq.formula = null;
    //     distReq.buildResponse();

    //     assertThrows(BadRequestException.class, distReq::buildResponse, "This error case for a Null Argument should be handled by the BadRequestException class.");
    // }

}