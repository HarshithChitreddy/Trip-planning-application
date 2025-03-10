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
import com.tco.misc.HaversineCalculator;
import com.tco.misc.CosinesCalculator;

public class DistancesRequest extends Request {
    protected String formula;
    protected double earthRadius;
    private Distances distances;
    private Places places;
    private static final transient Logger log = LoggerFactory.getLogger(DistancesRequest.class);
    
    public void buildResponse() throws BadRequestException{
        this.distances = new Distances();

        if(!places.isEmpty()){
            DistanceCalculator calculator = setCalculator();

            if (places.size() > 2){
                distanceCalc(calculator);
                distanceCalcFirstLast(calculator);
            } else if (places.size() == 2) {
                distanceCalcFirstLast(calculator);
                distanceCalcLastFirst(calculator);
            } else {
                distances.add(0L);
            }    
        }

        log.trace("buildResponse -> {}", this);
    }

    private DistanceCalculator setCalculator() throws BadRequestException {
        if (formula == null || formula.equals("vincenty")) {
            return new VincentyCalculator();
        } else if (formula.equals("haversine")) {
            return new HaversineCalculator();
        } else if (formula.equals("cosines")) {
            return new CosinesCalculator();
        } else {
            throw new BadRequestException();
        }
    }
    
    private void distanceCalcFirstLast(DistanceCalculator calculator) {
        Place first = places.get(0);
        Place last = places.get(places.size()-1);
        distances.add(calculator.between(first, last, earthRadius));
    }

    private void distanceCalcLastFirst(DistanceCalculator calculator){
        Place first = places.get(0);
        Place last = places.get(places.size()-1);
        distances.add(calculator.between(last, first, earthRadius));
    }

    private void distanceCalc(DistanceCalculator calculator){
        for(int i=0;i < places.size()-1; i++){
            Place from = places.get(i);
            Place to = places.get(i+1);
            distances.add(calculator.between(from, to, earthRadius));
        }
    }

    public Places getPlaces(){ return places; }

    public void setPlaces(Places places){
        this.places = places;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public void setRadius(Double earthRadius) {
        this.earthRadius = earthRadius;
    }
    
    public Distances getDistances(){ return distances; }

    public String getFormula(){ return formula; }

    public double getEarthRadius(){ return earthRadius; }
}