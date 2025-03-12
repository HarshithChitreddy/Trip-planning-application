package com.tco.misc;

public class CalculatorFactory {

    public DistanceCalculator get(String formula) throws BadRequestException {
        return new VincentyCalculator();
    }

}
