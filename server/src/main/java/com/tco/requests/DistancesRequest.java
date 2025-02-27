package com.tco.requests;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tco.requests.Distances;
import com.tco.requests.Places;
import com.tco.misc.BadRequestException;
import com.tco.misc.DistanceCalculator;
import com.tco.misc.VincentyCalculator;

public class DistancesRequest extends Request{
    protected String formula;
    protected double earthRadius;
    private Distances distances;
    private Places places;
    private VincentyCalculator vincentyCalculator = new VincentyCalculator();
    
    private static final transient Logger log = LoggerFactory.getLogger(DistancesRequest.class);

    public void buildResponse() throws BadRequestException{

        //this.places = new Places();
        this.distances = new Distances();

        if(formula != null && !formula.equals("vincenty")){
            throw new BadRequestException();
        }

        if(!places.isEmpty()){
            vinCal();
        }

        log.trace("buildResponse -> {}", this);

    }

    private void vinCal(){
        Place from = places.get(0);
        Place to = places.get(1);
        distances.add(vincentyCalculator.between(from, to, earthRadius));
    }

    public Places getPlaces(){ return places; }

    public void setPlaces(Places places){
        this.places = places; 
    }

    public Distances getDistances(){ return distances; }

    public String getFormula(){ return formula; }

    public double getEarthRadius(){ return earthRadius; }
}
