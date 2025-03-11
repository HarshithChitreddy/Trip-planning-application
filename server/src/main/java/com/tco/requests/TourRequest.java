package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tco.misc.BadRequestException;

public class TourRequest extends Request{
    private Places places;
    private double earthRadius;
    private double response;
    private String formula;
    private static final transient Logger log = LoggerFactory.getLogger(TourRequest.class);

    public void buildResponse() throws BadRequestException{
        //TBD
    }

    public void setPlaces(Places places){
        this.places = places;
    }
    public void setResponse(double response){
        this.response = response;
    }
    public void setFormula(String formula){
        this.formula = formula;
    }
    public void setEarthRadius(double earthRadius){
        this.earthRadius = earthRadius;
    }

    
    public Places getPlaces(){ return places; }

    public double getResponse(){ return response; }

    public double getEarthRadius(){ return earthRadius; }

    public String getFormula(){ return formula; }
}
