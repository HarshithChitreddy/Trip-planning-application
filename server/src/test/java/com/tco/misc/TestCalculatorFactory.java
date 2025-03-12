package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
