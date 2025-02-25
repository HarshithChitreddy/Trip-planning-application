package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPlaces {
    private Place testPlace1;
    private Place testPlace2;
    private Places testPlacesArray;


    @BeforeEach
    public void setUp() {
        testPlace1 = new Place();
        testPlace2 = new Place();
        testPlacesArray = new Places();
    }

    
    @DisplayName("dnweath: Test Places can add Place objects")
    @Test
    public void testPlacesArrayAddMethod() {
        testPlacesArray.add(testPlace1);
        testPlacesArray.add(testPlace2);

        assertEquals(2, testPlacesArray.size());
    }

    @DisplayName("dnweath: Test Places can remove Place objects")
    @Test
    public void testPlacesArrayRemoveMethod() {
        testPlacesArray.add(testPlace1);
        testPlacesArray.add(testPlace2);

        testPlacesArray.remove(testPlace1);

        assertEquals(1, testPlacesArray.size());
    }
}