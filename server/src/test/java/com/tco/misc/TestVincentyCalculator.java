package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

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

    private Geo geoTest1;
    private Geo geoTest2;
    private double radiusTest;
    private VincentyCalculator vincentyTest = new VincentyCalculator();

    @BeforeEach
    public void setUp() {
        radiusTest = 3678.0;
    }


    @DisplayName("dnweath: Test Vincenty calculator returns 0")
    @Test
    public void testBaseVincentyCalculatorReturnsZero() {
        
        geoTest1 = new Geo(0.0, 0.0);
        geoTest2 = new Geo(0.0, 0.0);

        long actualDistance = vincentyTest.between(geoTest1, geoTest2, radiusTest);
        assertEquals(0L, actualDistance);
    }
    
    @DisplayName("chrisc23:Test Vincenty calculator for negative degrees")
    @Test
    public void testVincentyPartialNegativedegree() {

        Geo geoTest3 = new Geo(Math.toRadians(-30.0), Math.toRadians(-60.0)); 
        Geo geoTest4 = new Geo(Math.toRadians(30.0), Math.toRadians(60.0)); 

        long actualDistance = vincentyTest.between(geoTest3, geoTest4, radiusTest);

        assertEquals(8261L, actualDistance);
    }
    @DisplayName("chrisc23:Test Haversine calculator for all negative degrees")
    @Test
    public void testVincentyAllNegativedegree() {
        Geo geoTest3 = new Geo(Math.toRadians(-30.0), Math.toRadians(-60.0)); 
        Geo geoTest4 = new Geo(Math.toRadians(-10.0), Math.toRadians(-90.0)); 

        long actualDistance = vincentyTest.between(geoTest3, geoTest4, radiusTest);

        assertEquals(2206L, actualDistance);
    }
    @DisplayName("chrisc23: Test Vincenty Calculator: Equatorial Distance")
    @Test
    public void testLarge90Distance() {
        Geo point1 = new Geo(0.0, 0.0);
        Geo point2 = new Geo(0.0, Math.PI / 2); // 90 degrees in radians
        long actualDistance = vincentyTest.between(point1, point2, radiusTest);

        assertEquals(5777L, actualDistance);
    }
    @DisplayName("chrisc23: Test Vincenty Calculator: 180 degrees")
    @Test
    public void testLarge180Distance() {
        Geo point1 = new Geo(0.0, 0.0);
        Geo point2 = new Geo(0.0, Math.PI); //180 degrees in radians
        long actualDistance = vincentyTest.between(point1, point2, radiusTest);

        assertEquals(11555L, actualDistance);
    }

    /*
     * Testing with radius value
     */

    @DisplayName("dnweath: Test Vincenty Calculator: small radius")
    @Test
    public void testSmallRadius() {
        radiusTest = 300.0;

        geoTest1 = new Geo(0.0, 0.0);
        geoTest2 = new Geo(0.0, Math.PI / 2); // 90 degrees

        long actualDistance = vincentyTest.between(geoTest1, geoTest2, radiusTest);
        assertEquals(471L, actualDistance);
    }

    @DisplayName("dnweath: Test Vincenty Calculator: large radius")
    @Test
    public void testLargeRadius() {
        radiusTest = 780523.0;

        geoTest1 = new Geo(0.0, 0.0);
        geoTest2 = new Geo(0.0, Math.PI / 2); // 90 degrees

        long actualDistance = vincentyTest.between(geoTest1, geoTest2, radiusTest);
        assertEquals(1226043L, actualDistance);
    }

    @DisplayName("dnweath: Test Vincenty Calculator: multiple decimal points")
    @Test
    public void testRadiusManyDecimals() {
        radiusTest = 6082.385027367614;

        geoTest1 = new Geo(0.0, 0.0);
        geoTest2 = new Geo(0.0, Math.PI / 2); // 90 degrees

        long actualDistance = vincentyTest.between(geoTest1, geoTest2, radiusTest);
        assertEquals(9554L, actualDistance);
    }

}
