package com.tco.requests;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tco.requests.Distances;
import com.tco.requests.Places;

public class DistancesRequest {
    protected String formula;
    private double earthRadius;
    private Distances distances;
    private Places places;
    
    private static final transient Logger log = LoggerFactory.getLogger(DistancesRequest.class);

    public void buildResponse(){

        places = new Places();
        distances = new Distances();

        log.trace("buildResponse -> {}", this);
    }
}
