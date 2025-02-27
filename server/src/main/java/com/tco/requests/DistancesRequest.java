package com.tco.requests;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tco.requests.Distances;
import com.tco.requests.Places;
import com.tco.misc.BadRequestException;
import com.tco.misc.DistanceCalculator;

public class DistancesRequest extends Request{
    protected String formula;
    protected double earthRadius;
    private Distances distances = new Distances();
    private Places places = new Places();
    private DistanceCalculator calculator;
    
    private static final transient Logger log = LoggerFactory.getLogger(DistancesRequest.class);

    public void buildResponse() throws BadRequestException{

        // this.places = new Places();
        // this.distances = new Distances();

        log.trace("buildResponse -> {}", this);

        // if(places.isEmpty() || places.size() == 1){
        //     distances.add(0L);
        // }
        checkPlacesEmpty();

        if(formula.equals("vincenty")){
            calculator = new vincentyCalculator();
        }
        else if(formula.equals("haversine")){
            calculator = new haversineCalculator();
        }
        else if(formula.equals("cosine")){
            calculator = new cosineCalculator();
        }
        else{
            throw new BadRequestException();
        }
    }

    private Distances checkPlacesEmpty(){
        if(places.isEmpty()){
            return distances;
        }
        return distances;
    }

    public Places getPlaces(){ return places; }

    public Distances getDistances(){ return distances; }

    public String getFormula(){ return formula; }

    public double getEarthRadius(){ return earthRadius; }
}
