package com.tco.misc;
import com.tco.requests.Places;
public abstract class TourOptimizer {

    public abstract Places construct(Places places, Double radius, String formula, Double response);

    public abstract void improve();
    
}