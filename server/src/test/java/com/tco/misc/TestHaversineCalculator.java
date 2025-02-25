package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHaversineCalculator {
    
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
    private double radiusTest = 6371.009;
    private HaversineCalculator haversineTest = new HaversineCalculator();

    @DisplayName("dnweath: Test Haversine calculator returns 0")
    @Test
    public void testBaseHaversineCalculatorReturnsZero() {
        long actualDistance = haversineTest.between(geoTest1, geoTest2, radiusTest);
        assertEquals(0L, actualDistance);
    }

    @DisplayName("chrisc23:Test Haversine calculator for 90 degrees")
    @Test
    public void testHaversine90degree() {
        // Test points are 90 degrees apart along the equator
        Geo geoTest3 = new Geo(0.0, 0.0); 
        Geo geoTest4 = new Geo(0.0, Math.PI / 2); 
        
        long actualDistance = haversineTest.between(geoTest3, geoTest4, radiusTest);
        
        // Expected distance is roughly 10007.543 km for Earth's radius of 6371 km
        assertEquals(10008L, actualDistance);
    }
    @DisplayName("chrisc23:Test Haversine calculator for 180 degrees")
    @Test
    public void testHaversine180degree() {
        
        Geo geoTest3 = new Geo(0.0, 0.0); 
        Geo geoTest4 = new Geo(Math.PI, 0.0); 
        
        long actualDistance = haversineTest.between(geoTest3, geoTest4, radiusTest);
        
        assertEquals(20015L, actualDistance);
    }

    @DisplayName("chrisc23:Test Haversine calculator for negative degrees")
    @Test
    public void testHaversinePartialNegativedegree() {
        
        Geo geoTest3 = new Geo(Math.toRadians(-30.0), Math.toRadians(-60.0)); 
        Geo geoTest4 = new Geo(Math.toRadians(30.0), Math.toRadians(60.0)); 
        
        long actualDistance = haversineTest.between(geoTest3, geoTest4, radiusTest);
        
        assertEquals(14309L, actualDistance);
    }
    @DisplayName("chrisc23:Test Haversine calculator for all negative degrees")
    @Test
    public void testHaversineAllNegativedegree() {
        
        Geo geoTest3 = new Geo(Math.toRadians(-30.0), Math.toRadians(-60.0)); 
        Geo geoTest4 = new Geo(Math.toRadians(-10.0), Math.toRadians(-90.0)); 
        
        long actualDistance = haversineTest.between(geoTest3, geoTest4, radiusTest);
        
        assertEquals(3822L, actualDistance);
    }

}
