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

    @BeforeEach
    public void setUpDR(){
        distances = new Distances();
        places = new Places();
        distReq = new DistancesRequest();
    }

}