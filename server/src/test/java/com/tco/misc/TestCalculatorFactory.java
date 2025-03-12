package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;

public class TestCalculatorFactory {
    private CalculatorFactory testFactory;
    
    @BeforeEach
    public void setUp() {
        testFactory = new CalculatorFactory();
    }

    @DisplayName("dnweath: test null input returns VincentyCalculator")
    @Test
    public void testNullString() throws BadRequestException {
        DistanceCalculator testCalculator = testFactory.get(null);
        assertTrue(testCalculator instanceof VincentyCalculator);
    }

    @DisplayName("dnweath: test vincenty input returns VincentyCalculator")
    @Test
    public void testVincentyString() throws BadRequestException {
        DistanceCalculator testCalculator = testFactory.get("vincenty");
        assertTrue(testCalculator instanceof VincentyCalculator);
    }

    @DisplayName("dnweath: test cosines input returns CosinesCalculator")
    @Test
    public void testCosinesString() throws BadRequestException {
        DistanceCalculator testCalculator = testFactory.get("cosines");
        assertTrue(testCalculator instanceof CosinesCalculator);
    }

    @DisplayName("dnweath: test haversine returns HaversineCalculator")
    @Test
    public void testHaversineString() throws BadRequestException {
        DistanceCalculator testCalculator = testFactory.get("haversine");
        assertTrue(testCalculator instanceof HaversineCalculator);
    }

    @DisplayName("dnweath: confirm invalid string throws exception")
    @Test
    public void testInvalidString() throws BadRequestException {
        assertThrows(BadRequestException.class, () -> testFactory.get("sigmoid"));
    }
}