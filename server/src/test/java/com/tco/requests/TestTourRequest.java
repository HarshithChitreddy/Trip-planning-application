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

public class TestTourRequest {
    private double response;
    private double earthRadius;
    private Places testPlaces;
    private TourRequest tourRequest;
    private String formula;
    private Place place1 = new Place();
    private Place place2 = new Place();
    private Place place3 = new Place();


    @BeforeEach
    public void setUpTR(){
        tourRequest = new TourRequest();
        testPlaces = new Places();
        response = 0.8;
        earthRadius = 3514;

        place1.put("latitude", "90.0");
        place1.put("longitude", "180.0");
        place2.put("latitude", "0.0");
        place2.put("longitude", "0.0");
        place3.put("latitude", "-80.0");
        place3.put("longitude", "60.0");
    }

    @Test
    @DisplayName("lennoxxx: Test for response getters and setters.")
    public void testResponseGetSet(){
        tourRequest.setResponse(response);
        assertEquals(response, tourRequest.getResponse());
    }

    @Test
    @DisplayName("lennoxxx: Test for formula getters and setters.")
    public void testFormulaGetSet(){
        formula = "vincenty";
        tourRequest.setFormula(formula);
        assertEquals(formula, tourRequest.getFormula());
    }

    @Test
    @DisplayName("lennoxxx: Test for earthRadius getters and setters.")
    public void testEarthRadiusGetSet(){
        tourRequest.setEarthRadius(earthRadius);
        assertEquals(earthRadius, tourRequest.getEarthRadius());
    }

    @Test
    @DisplayName("lennoxxx: Test for places getters and setters.")
    public void testPlacesGetSet(){
        testPlaces.add(place1);
        testPlaces.add(place2);
        testPlaces.add(place3);
        tourRequest.setPlaces(testPlaces);
        assertEquals(testPlaces, tourRequest.getPlaces());
    }
}
