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

public class TestDistancesRequest {
    private Places testPlaces;
    private Place place1 = new Place();
    private Place place2 = new Place();
    private Place place3 = new Place();
    private DistancesRequest distReq;
    private double earthRadius;
    private String formula;
    
    @BeforeEach
    public void setUpDR(){
        this.distReq = new DistancesRequest();
        testPlaces = new Places();
        place1.put("latitude", "0.0");
        place1.put("longitude", "90.0");
        place2.put("latitude", "0.0");
        place2.put("longitude", "0.0");
        place3.put("latitude", "-45.0");
        place3.put("longitude", "0.0");
    } 
    
    @Test
    @DisplayName("lennoxxx: Test for empty list of distances.")
    public void testGetPlacesEmpty() throws BadRequestException{
        distReq.setPlaces(new Places());
        distReq.buildResponse();

        assertTrue(distReq.getDistances().isEmpty());
    } 

    @Test
    @DisplayName("lennoxxx: Test for one location")
    public void testOnePlace() throws BadRequestException {
        testPlaces.add(place1);
        distReq.setPlaces(testPlaces);
        distReq.buildResponse();

        assertTrue(distReq.getDistances().size() == 1);
    }

    @Test
    @DisplayName("lennoxxx: Test for two locations, vincenty")
    public void testTwoPlaces() throws BadRequestException {
        testPlaces.add(place1);
        testPlaces.add(place2);

        distReq.setPlaces(testPlaces);
        distReq.setRadius(300.0);
        distReq.buildResponse();

        assertEquals(471L, distReq.getDistances().get(0));
        assertTrue(distReq.getDistances().size() == 2);
    }

    @Test
    @DisplayName("lennoxxx: Test for three locations, vincenty")
    public void testThreePlaces() throws BadRequestException {
        testPlaces.add(place1);
        testPlaces.add(place2);
        testPlaces.add(place3);

        distReq.setPlaces(testPlaces);
        distReq.setRadius(300.0);
        distReq.buildResponse();

        assertTrue(distReq.getDistances().size() == 3);
        assertEquals(236L, distReq.getDistances().get(1));
    }
 }