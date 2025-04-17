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
    
    public Places near(Place place, long distance, double earthRadius, String formula, int limit) throws BadRequestException {
        Places nearPlaces = new Places();

        this.calculatorFactory = new CalculatorFactory();
        this.calculator = calculatorFactory.get(formula);
        this.earthRadius = earthRadius;

        return nearPlaces;
    }

    public Distances distances(Place place, Places places) {
        Distances distances = new Distances();
        
        for (Place eachPlace : places) {
            distances.add(calculator.between(place, eachPlace, earthRadius));
        }

        return distances;
    }

    public List<String> getTypes() {
        List<String> types = new ArrayList<>();
        types.add("city");
        return types;
    }
}
