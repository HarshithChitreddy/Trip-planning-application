package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.misc.BadRequestException;
import com.tco.misc.CalculatorFactory;
import com.tco.misc.DistanceCalculator;
import com.tco.misc.GeographicCoordinate;

public class NearRequest extends Request{
    private Places places;
    private Place place;
    private Distances distances;
    protected int distance;
    protected double earthRadius;
    protected String formula;
    protected int limit;

    private static final transient Logger log = LoggerFactory.getLogger(NearRequest.class);

    public void buildResponse() throws BadRequestException{

    }

    public void setDistance(int distance){
        this.distance = distance;
    }
    public void setFormula(String formula){
        this.formula = formula;
    }
    public void setEarthRadius(double earthRadius){
        this.earthRadius = earthRadius;
    }
    public void setLimit(int limit){
        this.limit = limit;
    }

    

    
}
