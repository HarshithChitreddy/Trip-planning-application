package com.tco.misc;

public class OptimizerFactory {

    public TourOptimizer get(int N, Double response){

        if(response == 0.0){
            return new NoOptimizer();
        }
        else if(N > 250 && N < 600){
            return new OneOptimizer();
        }
        else if(N <= 250){
            return new TwoOptimizer();
        }
        else{ return new NoOptimizer(); }
    }


}