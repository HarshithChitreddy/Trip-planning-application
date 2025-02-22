package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestVincentyCalculator {
    
    public class Geo implements GeographicCoordinate {
        private double lat;
        private double lon;
        
        public Geo(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        @Override
        public double latRadians() {
            return lat;
        }

        @Override
        public double lonRadians() {
            return lon;
        }
    }

    private Geo geoTest1 = new Geo(0.0, 0.0);
    private Geo geoTest2 = new Geo(0.0, 0.0);
    private double radiusTest = 3678.0;
    private VincentyCalculator vincentyTest = new VincentyCalculator();

    @DisplayName("dnweath: Test Vincenty calculator returns 0")
    @Test
    public void testBaseVincentyCalculatorReturnsZero() {
        long actualDistance = vincentyTest.between(geoTest1, geoTest2, radiusTest);
        assertEquals(0L, actualDistance);
    }
}
