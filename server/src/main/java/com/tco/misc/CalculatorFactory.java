package com.tco.misc;

public class CalculatorFactory {

    public DistanceCalculator get(String formula) throws BadRequestException {
        // switch case with string can't check against null, throws compilation error
        if (formula == null) {
            return new VincentyCalculator();
        }
        
        switch(formula) {
            case "vincenty":
                return new VincentyCalculator();
            case "cosines":
                return new CosinesCalculator();
            case "haversine":
                return new HaversineCalculator();
            default: // invalid formula input
                throw new BadRequestException();
        }
    }
}
