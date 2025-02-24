package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDistances {

    private Distances distances;

    @BeforeEach
    public void setUp() {
        distances = new Distances();
    }

    @Test
    @DisplayName("Total is 0 for empty list")
    public void testTotalEmptyList() {
        assertEquals(0, distances.total());
    }

    @Test
    @DisplayName("Total is correct for a single element")
    public void testTotalSingleElement() {
        distances.add(100L);
        assertEquals(100, distances.total());
    }

    @Test
    @DisplayName("Total is correct for multiple elements")
    public void testTotalMultipleElements() {
        distances.add(100L);
        distances.add(200L);
        distances.add(300L);
        assertEquals(600, distances.total());
    }

    @Test
    @DisplayName("Total is correct for large numbers")
    public void testTotalLargeNumbers() {
        distances.add(1000000L);
        distances.add(2000000L);
        distances.add(3000000L);
        assertEquals(6000000, distances.total());
    }

    @Test
    @DisplayName("Total is correct for negative numbers")
    public void testTotalNegativeNumbers() {
        distances.add(-100L);
        distances.add(200L);
        distances.add(-50L);
        assertEquals(50, distances.total());
    }
}
