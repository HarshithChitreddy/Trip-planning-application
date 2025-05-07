package com.tco.requests;

import com.tco.misc.BadRequestException;
import com.tco.misc.OptimizerFactory;
import com.tco.misc.TourOptimizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TourRequest extends Request {
    private static final transient Logger log = LoggerFactory.getLogger(TourRequest.class);

    private Places places;
    private double earthRadius;
    private double response;
    private String formula;

    public TourRequest() {
    }

    @Override
    public void buildResponse() throws BadRequestException {
        validateFormula();

        if (places == null || places.isEmpty()) {
            return;
        }

        int N = places.size();
        OptimizerFactory factory = new OptimizerFactory();
        TourOptimizer optimizer = factory.get(N, response);

        this.places = optimizer.construct(places, earthRadius, formula, response);
        optimizer.improve();
        log.trace("buildResponse -> {}", this);
    }

    private void validateFormula() throws BadRequestException {
        if (formula == null) {
            return;
        }

        if (!(formula.equalsIgnoreCase("haversine") ||
              formula.equalsIgnoreCase("cosines") ||
              formula.equalsIgnoreCase("vincenty"))) {
            throw new BadRequestException(); 
        }
    }

    public void setPlaces(Places places) {
        this.places = places;
    }

    public void setEarthRadius(double earthRadius) {
        this.earthRadius = earthRadius;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public void setResponse(double response) {
        this.response = response;
    }

    public Places getPlaces() {
        return places;
    }

    public double getEarthRadius() {
        return earthRadius;
    }

    public String getFormula() {
        return formula;
    }

    public double getResponse() {
        return response;
    }
}
