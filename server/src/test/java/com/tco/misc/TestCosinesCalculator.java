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

    private Geo geoTest1 = new Geo(0.0, 0.0);
    private Geo geoTest2 = new Geo(0.0, 0.0);
    private double radiusTest = 6371.0; 
    private CosinesCalculator cosinesTest = new CosinesCalculator();
    private final double DELTA = 10.0; 

    @DisplayName("reddy17: Test CosinesCalculator returns 0 for same location")
    @Test
    public void testBaseCosinesCalculatorReturnsZero() {
        long actualDistance = cosinesTest.between(geoTest1, geoTest2, radiusTest);
        assertEquals(0L, actualDistance, "Distance should be 0 for the same location");
    }
    
    @DisplayName("reddy17: Test CosinesCalculator for partial negative degrees")
    @Test
    public void testCosinesPartialNegativeDegree() {
        Geo geoTest3 = new Geo(Math.toRadians(-30.0), Math.toRadians(-60.0)); 
        Geo geoTest4 = new Geo(Math.toRadians(30.0), Math.toRadians(60.0)); 
        long actualDistance = cosinesTest.between(geoTest3, geoTest4, radiusTest);

        assertEquals(14309L, actualDistance, DELTA, "Distance for partial negative degrees should be approximately 14309 km");
    }

    @DisplayName("kjell: Test cosines for one negative degree and one positive degree")
    @Test
    public void testCosinesSingleNegativeDegree() {
        Geo geoTest3 = new Geo(Math.toRadians(60.0), Math.toRadians(-60.0)); 
        Geo geoTest4 = new Geo(Math.toRadians(-60.0), Math.toRadians(60.0)); 
        long actualDistance = cosinesTest.between(geoTest3, geoTest4, radiusTest);

        assertEquals(16795L, actualDistance, DELTA, "Distance for 60/-60→-60/60 degrees should be approximately 16795 km = ((1 * radiusTest) * pi)");
    }
    
    @DisplayName("reddy17: Test CosinesCalculator for all negative degrees")
    @Test
    public void testCosinesAllNegativeDegree() {
        Geo geoTest3 = new Geo(Math.toRadians(-30.0), Math.toRadians(-60.0)); 
        Geo geoTest4 = new Geo(Math.toRadians(-10.0), Math.toRadians(-90.0)); 
        long actualDistance = cosinesTest.between(geoTest3, geoTest4, radiusTest);

        assertEquals(3822L, actualDistance, DELTA, "Distance for all negative degrees should be approximately 3822 km");
    }

    @DisplayName("reddy17: Test CosinesCalculator: Equatorial Distance (90 degrees)")
    @Test
    public void testEquatorial90Distance() {
        Geo point1 = new Geo(0.0, 0.0);
        Geo point2 = new Geo(0.0, Math.PI / 2); 
        long actualDistance = cosinesTest.between(point1, point2, radiusTest);

        assertEquals(10008L, actualDistance, DELTA, "Equatorial distance for 90 degrees should be approximately 10008 km");
    }
    
    @DisplayName("reddy17: Test CosinesCalculator: Maximum Distance (180 degrees)")
    @Test
    public void testMax180Distance() {
        Geo point1 = new Geo(0.0, 0.0);
        Geo point2 = new Geo(0.0, Math.PI);
        long actualDistance = cosinesTest.between(point1, point2, radiusTest);

        assertEquals(20015L, actualDistance, DELTA, "Maximum distance for 180 degrees should be approximately 20015 km");
    }
}
