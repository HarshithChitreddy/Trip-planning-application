package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestPlace {

    private Place testPlace;

    @BeforeEach
    public void setUp() {
        testPlace = new Place();
    }

    // latRadians() Tests
    @Test
    @DisplayName("reddy17: latRadians() for positive latitude")
    public void testLatRadiansPositive() {
        testPlace.put("latitude", "45.0");
        double expected = Math.toRadians(45.0);
        assertEquals(expected, testPlace.latRadians(), 0.0001);
    }

    @Test
    @DisplayName("reddy17: latRadians() for negative latitude")
    public void testLatRadiansNegative() {
        testPlace.put("latitude", "-45.0");
        double expected = Math.toRadians(-45.0);
        assertEquals(expected, testPlace.latRadians(), 0.0001);
    }

    @Test
    @DisplayName("reddy17: latRadians() for zero latitude")
    public void testLatRadiansZero() {
        testPlace.put("latitude", "0.0");
        double expected = 0.0;
        assertEquals(expected, testPlace.latRadians(), 0.0001);
    }

    @Test
    @DisplayName("reddy17: latRadians() for invalid latitude")
    public void testInvalidLatitude() {
        testPlace.put("latitude", "invalid");
        assertThrows(NumberFormatException.class, () -> {
            testPlace.latRadians();
        });
    }

    @Test
    @DisplayName("reddy17: latRadians() for null latitude")
    public void testNullLatitude() {
        testPlace.put("latitude", null);
        assertThrows(NullPointerException.class, () -> {
            testPlace.latRadians();
        });
    }
}
