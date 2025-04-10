package com.tco.misc;

public class OptimizerFactory {

    public TourOptimizer get(int N, Double response) throws BadRequestException{ 

        if(response < 0.0 || response > 1.0){
            throw new BadRequestException();
        }

        if (response == 0.0){
            return new NoOptimizer();
        } else { 
            return new OneOptimizer(); 
        }
    }


}