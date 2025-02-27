package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestPlace {

    private Place placeUnderTest;
    private static final String LATITUDE = "latitude";

    @BeforeEach
    public void setUp() {
        placeUnderTest = new Place();
    }

    @Test
    @DisplayName("reddy17: latRadians() for positive latitude")
    public void testLatRadiansPositive() {
        placeUnderTest.put(LATITUDE, "45.0");
        double expected = Math.toRadians(45.0);
        assertEquals(expected, placeUnderTest.latRadians(), 0.0001);
    }

    @Test
    @DisplayName("reddy17: latRadians() for negative latitude")
    public void testLatRadiansNegative() {
        placeUnderTest.put(LATITUDE, "-45.0");
        double expected = Math.toRadians(-45.0);
        assertEquals(expected, placeUnderTest.latRadians(), 0.0001);
    }

    @Test
    @DisplayName("reddy17: latRadians() for zero latitude")
    public void testLatRadiansZero() {
        placeUnderTest.put(LATITUDE, "0.0");
        double expected = 0.0;
        assertEquals(expected, placeUnderTest.latRadians(), 0.0001);
    }

    @Test
    @DisplayName("reddy17: latRadians() for invalid latitude")
    public void testInvalidLatitude() {
        placeUnderTest.put(LATITUDE, "invalid");
        assertThrows(NumberFormatException.class, () -> placeUnderTest.latRadians());
    }

    @Test
    @DisplayName("reddy17: latRadians() for null latitude")
    public void testNullLatitude() {
        placeUnderTest.put(LATITUDE, null);
        assertThrows(NullPointerException.class, () -> placeUnderTest.latRadians());
    }

    @Test
    @DisplayName("reddy17: lonRadians() for positive longitude")
    public void testLonRadiansPositive() {
        placeUnderTest.put("longitude", "90.0");
        double expected = Math.toRadians(90.0);
        assertEquals(expected, placeUnderTest.lonRadians(), 0.0001);
    }

    @Test
    @DisplayName("reddy17: lonRadians() for negative longitude")
    public void testLonRadiansNegative() {
        placeUnderTest.put("longitude", "-90.0");
        double expected = Math.toRadians(-90.0);
        assertEquals(expected, placeUnderTest.lonRadians(), 0.0001);
    }

    @Test
    @DisplayName("reddy17: lonRadians() for zero longitude")
    public void testLonRadiansZero() {
        placeUnderTest.put("longitude", "0.0");
        double expected = 0.0;
        assertEquals(expected, placeUnderTest.lonRadians(), 0.0001);
    }

    @Test
    @DisplayName("reddy17: lonRadians() for invalid longitude")
    public void testInvalidLongitude() {
        placeUnderTest.put("longitude", "invalid");
        assertThrows(NumberFormatException.class, () -> placeUnderTest.lonRadians());
    }

    @Test
    @DisplayName("reddy17: lonRadians() for null longitude")
    public void testNullLongitude() {
        placeUnderTest.put("longitude", null);
        assertThrows(NullPointerException.class, () -> placeUnderTest.lonRadians());
    }

}
