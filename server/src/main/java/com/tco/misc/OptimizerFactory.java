package com.tco.misc;

public class OptimizerFactory {

    public TourOptimizer get(int N, Double response){

        if(response < 0.2){
            return new NoOptimizer();
        }
        else if(response > 0.6){
            return new OneOptimizer();
        }
        else{ return new NoOptimizer(); }
    }


}