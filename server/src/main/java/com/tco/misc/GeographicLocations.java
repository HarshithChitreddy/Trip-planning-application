package com.tco.misc;

import com.tco.requests.Place;
import com.tco.requests.Places;
import com.tco.requests.Distances;

import java.util.ArrayList;
import java.util.List;

public class GeographicLocations {
    CalculatorFactory calculatorFactory;
    DistanceCalculator calculator;
    double earthRadius;
    
    public Places near(Place place, Integer distance, double earthRadius, String formula, int limit) throws BadRequestException {
        this.calculatorFactory = new CalculatorFactory();
        this.calculator = calculatorFactory.get(formula);
        this.earthRadius = earthRadius;

        try {
            return Database.places(Select.near(limit, place, distance), limit);
        } catch (Exception e) {
            throw new BadRequestException();
        }
        
    }

    public Distances distances(Place place, Places places) {
        Distances distances = new Distances();
        
        for (Place eachPlace : places) {
            distances.add(calculator.between(place, eachPlace, earthRadius));
        }

        return distances;
    }
    public Places find(String match, List<String> type, String[] where, Integer limit) throws Exception{


        return null;
    }
    public List<String> getTypes() {
        List<String> types = new ArrayList<>();
        types.add("city");
        return types;
    }
}
