package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.ArrayList;

public class TestDistancesRequest{
    private Distances distances;
    private Places places;
    private DistancesRequest distReq;
    private List<String> buildResponse;

    @BeforeEach
    public void setUpDR(){
        distances = new Distances();
        places = new Places();
        distReq = new DistancesRequest();
        buildResponse = distReq.buildResponse();

    }
    @Test
    @DisplayName("lennoxxx: Correct implementation of list size.")
    public void testBuildResponseSize(){
        buildResponse.add("random place");
        assertEquals(1, buildResponse.size(), "The size of the buildResponse list should be 1.");
    }

    @Test
    @DisplayName("lennoxxx: Detection of empty list.")
    public void testBuildResponseEmpty(){
        buildResponse.clear();
        assertEquals(0, buildResponse.size(), "Error: the distancesList is empty!");
    }

}