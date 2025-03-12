package com.tco.misc;

import com.tco.requests.Places;

public class NoOptimizer extends TourOptimizer {

    @Override
    public Places construct(Places places, Double radius, String formula, Double response) {
        return null;
    }

    @Override
    public void improve() {

    }
    
}