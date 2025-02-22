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

    @DisplayName("dnweath: Initial test - placeholder")
    @Test
    public void testBaseVincenty() {
        assertEquals(vincentyTest.between(geoTest1, geoTest2, radiusTest), 0L);
    }

}
