package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCosinesCalculator {

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

    private static final double EARTH_RADIUS_KM = 6371.0;
    private final CosinesCalculator cosinesTest = new CosinesCalculator();

    @DisplayName("reddy17: Test CosinesCalculator returns 0 for same location")
    @Test
    public void testBaseCosinesCalculatorReturnsZero() {
        Geo sameLocation = new Geo(0.0, 0.0);
        long actualDistance = cosinesTest.between(sameLocation, sameLocation, earthRadiusKm);
        assertEquals(0L, actualDistance, "Distance should be 0 for the same location");
    }

    @DisplayName("reddy17: Test CosinesCalculator for short distance in NYC")
    @Test
    public void testShortDistance() {
        Geo point1 = new Geo(Math.toRadians(40.748817), Math.toRadians(-73.985428)); 
        Geo point2 = new Geo(Math.toRadians(40.748541), Math.toRadians(-73.985758)); 
        long actualDistance = cosinesTest.between(point1, point2, earthRadiusKm);
        assertEquals(0L, actualDistance, "Distance should be less than 1 km for nearby locations");
    }

    @DisplayName("reddy17: Test CosinesCalculator for long distance (NYC to LA)")
    @Test
    public void testLongDistance() {
        Geo newYork = new Geo(Math.toRadians(40.712776), Math.toRadians(-74.005974)); 
        Geo losAngeles = new Geo(Math.toRadians(34.052235), Math.toRadians(-118.243683)); 
        long actualDistance = cosinesTest.between(newYork, losAngeles, earthRadiusKm);
        assertEquals(3940L, actualDistance, 50, "Distance between NYC and LA should be approximately 3940 km");
    }
}
