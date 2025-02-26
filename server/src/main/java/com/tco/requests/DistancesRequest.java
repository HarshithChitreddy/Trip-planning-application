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
    private List<String> distancesList;

    private static final transient Logger log = LoggerFactory.getLogger(DistancesRequest.class);


    public List<String> buildResponse(){
        places = new Places();
        distances = new Distances();
        distancesList = new ArrayList<>();
        log.trace("buildResponse -> {}", this);

        if(distancesList.size() == 0){
            System.err.println("Error: the distancesList is empty!");
        }
        return distancesList;
    }
}
