package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;

public class TestOptimizerFactory {
    private OptimizerFactory optFact;
    private Double response;
    private int N;

    @BeforeEach
    public void setUp(){
        optFact = new OptimizerFactory();
    }

    @Test
    @DisplayName("lennoxxx: Test TourOptimizer get() NoOpt.")
    public void testGetNoOpt() throws BadRequestException{
        assertTrue(optFact.get(617, 0.0) instanceof NoOptimizer);
    }
    @Test
    @DisplayName("lennoxxx: Test TourOptimizer get() OneOpt")
    public void testGetOneOpt() throws BadRequestException{
        assertTrue(optFact.get(300, 0.8) instanceof OneOptimizer);
    }
    // @Test
    // @DisplayName("lennoxxx: Test TourOptimizer get() TwoOpt.")
    // public void testGetTwoOpt(){
    //     assertTrue(optFact.get(100, 0.9) instanceof TwoOptimizer);
    // }
    @Test
    @DisplayName("lennoxxx: Test TourOptimizer zero response time.")
    public void testGetWithZeroReponseTime() throws BadRequestException{
        assertTrue(optFact.get(260, 0.0) instanceof NoOptimizer);
    }

    @Test
    @DisplayName("lennoxxx: Test TourOptimizer for invalid response time.")
    public void testInvalidResponseTime() throws BadRequestException{
        assertThrows(BadRequestException.class, () -> {
            optFact.get(265, 1.1);
        });
    }

}
