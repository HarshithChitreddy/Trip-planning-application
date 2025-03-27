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

    @Test
    @DisplayName("reddy17: Test buildResponse() with haversine formula")
    public void testBuildResponseHaversine() throws BadRequestException {
        testPlaces.add(place1);
        testPlaces.add(place2);
        testPlaces.add(place3);
        tourRequest.setPlaces(testPlaces);
        tourRequest.setFormula("haversine");
        tourRequest.setEarthRadius(earthRadius);
        tourRequest.setResponse(response);
    
        tourRequest.buildResponse();
        assertNotNull(tourRequest.getPlaces());
        assertEquals(3, tourRequest.getPlaces().size());
    }

    @Test
    @DisplayName("reddy17: Test buildResponse() with null formula defaults to vincenty")
    public void testBuildResponseWithNullFormula() throws BadRequestException {
        testPlaces.add(place1);
        testPlaces.add(place2);
        tourRequest.setPlaces(testPlaces);
        tourRequest.setFormula(null);
        tourRequest.setEarthRadius(earthRadius);
        tourRequest.setResponse(response);
    
        tourRequest.buildResponse();
        assertEquals("vincenty", tourRequest.getFormula());
    }
        
    @Test
    @DisplayName("reddy17: buildResponse with cosines formula works")
    public void testBuildResponseCosines() throws BadRequestException {
        testPlaces.add(place1);
        testPlaces.add(place2);
        testPlaces.add(place3);
        tourRequest.setPlaces(testPlaces);
        tourRequest.setFormula("cosines");
        tourRequest.setEarthRadius(earthRadius);
        tourRequest.setResponse(response);
    
        tourRequest.buildResponse();
        assertEquals(3, tourRequest.getPlaces().size());
    }

    @Test
    @DisplayName("reddy17: Invalid formula should throw BadRequestException")
    public void testInvalidFormulaThrows() {
        tourRequest.setPlaces(testPlaces);
        tourRequest.setFormula("euclidean");
        tourRequest.setEarthRadius(earthRadius);
        tourRequest.setResponse(response);
    
        assertThrows(BadRequestException.class, () -> tourRequest.buildResponse());
    }

    @Test
    @DisplayName("reddy17: Test buildResponse() with mixed-case formula")
    public void testFormulaCaseInsensitive() throws BadRequestException {
        testPlaces.add(place1);
        testPlaces.add(place2);
        tourRequest.setPlaces(testPlaces);
        tourRequest.setFormula("HaVerSiNe");
        tourRequest.setEarthRadius(earthRadius);
        tourRequest.setResponse(response);
    
        tourRequest.buildResponse();
        assertEquals("HaVerSiNe", tourRequest.getFormula()); 
    }

    @Test
    @DisplayName("reddy17: Empty places list should not throw error")
    public void testEmptyPlacesNoThrow() {
        tourRequest.setPlaces(new Places());
        tourRequest.setFormula("vincenty");
        tourRequest.setEarthRadius(earthRadius);
        tourRequest.setResponse(response);
    
        assertDoesNotThrow(() -> tourRequest.buildResponse());
        assertEquals(0, tourRequest.getPlaces().size());
    }
    @Test
    @DisplayName("reddy17: Null places should not throw in buildResponse")
    public void testNullPlacesNoThrow() {
        tourRequest.setPlaces(null);
        tourRequest.setFormula("vincenty");
        tourRequest.setEarthRadius(earthRadius);
        tourRequest.setResponse(response);
    
        assertDoesNotThrow(() -> tourRequest.buildResponse());
        assertNull(tourRequest.getPlaces());
    }
    
    @Test
    @DisplayName("reddy17: Response 0 should still allow buildResponse (NoOptimizer fallback)")
    public void testBuildResponseWithZeroResponse() throws BadRequestException {
        testPlaces.add(place1);
        testPlaces.add(place2);
        tourRequest.setPlaces(testPlaces);
        tourRequest.setFormula("vincenty");
        tourRequest.setEarthRadius(earthRadius);
        tourRequest.setResponse(0.0); 
    
        tourRequest.buildResponse();
        assertEquals(2, tourRequest.getPlaces().size());
    }
    
    @Test
    @DisplayName("reddy17: buildResponse should handle large number of places")
    public void testBuildResponseLargeInput() throws BadRequestException {
        for (int i = 0; i < 260; i++) {
            Place p = new Place();
            p.put("latitude", String.valueOf(i % 90));
            p.put("longitude", String.valueOf(i % 180));
            testPlaces.add(p);
        }
    
        tourRequest.setPlaces(testPlaces);
        tourRequest.setFormula("cosines");
        tourRequest.setEarthRadius(earthRadius);
        tourRequest.setResponse(response);
    
        tourRequest.buildResponse();
        assertEquals(260, tourRequest.getPlaces().size());
    }
    
    @Test
    @DisplayName("reddy17: buildResponse called twice should not throw")
    public void testBuildResponseCalledTwice() throws BadRequestException {
        testPlaces.add(place1);
        testPlaces.add(place2);
        tourRequest.setPlaces(testPlaces);
        tourRequest.setFormula("vincenty");
        tourRequest.setEarthRadius(earthRadius);
        tourRequest.setResponse(response);
    
        tourRequest.buildResponse();
        assertDoesNotThrow(() -> tourRequest.buildResponse());
        assertEquals(2, tourRequest.getPlaces().size());
    }
    
    @Test
    @DisplayName("reddy17: Formula set after setting other fields still applies correctly")
    public void testLateFormulaSetStillApplies() throws BadRequestException {
        testPlaces.add(place1);
        testPlaces.add(place2);
        tourRequest.setPlaces(testPlaces);
        tourRequest.setEarthRadius(earthRadius);
        tourRequest.setResponse(response);
        tourRequest.setFormula("haversine"); 
    
        tourRequest.buildResponse();
        assertEquals("haversine", tourRequest.getFormula());
    }
    
}
