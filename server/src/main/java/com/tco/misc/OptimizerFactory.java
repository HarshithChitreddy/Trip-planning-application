package com.tco.misc;

public class OptimizerFactory {

    public TourOptimizer get(int N, Double response){

        if (response == 0.0){
            return new NoOptimizer();
        } else { 
            return new OneOptimizer(); 
        }
    }


}