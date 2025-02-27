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
    private DistanceCalculator calculator;
    private static final transient Logger log = LoggerFactory.getLogger(DistancesRequest.class);
    
    public void buildResponse() throws BadRequestException{
        this.distances = new Distances();

        if(!places.isEmpty()){
            setCalculator();

            if (places.size() > 2){
                distanceCalc();
                distanceCalcFirstLast();
            } else if (places.size() == 2) {
                distanceCalcFirstLast();
                distanceCalcLastFirst();
            } else {
                distances.add(0L);
            }    
        }

        log.trace("buildResponse -> {}", this);
    }

    private void setCalculator() throws BadRequestException {
        if (formula == null || formula.equals("vincenty")) {
            calculator = new VincentyCalculator();
        } else if (formula.equals("haversine")) {
            calculator = new HaversineCalculator();
        } else if (formula.equals("cosine")) {
            calculator = new CosinesCalculator();
        } else {
            throw new BadRequestException();
        }
    }
    
    private void distanceCalcFirstLast() {
        Place first = places.get(0);
        Place last = places.get(places.size()-1);
        distances.add(calculator.between(first, last, earthRadius));
    }

    private void distanceCalcLastFirst(){
        Place first = places.get(0);
        Place last = places.get(places.size()-1);
        distances.add(calculator.between(last, first, earthRadius));
    }

    private void distanceCalc(){
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