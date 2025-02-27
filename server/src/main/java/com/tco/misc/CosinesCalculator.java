package com.tco.misc;

public class CosinesCalculator implements DistanceCalculator {

    @Override
    public long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
        double latFrom = from.latRadians();
        double latTo = to.latRadians();
        double lonFrom = from.lonRadians();
        double lonTo = to.lonRadians();

        double distance = earthRadius * Math.acos(
            Math.sin(latFrom) * Math.sin(latTo) 
            + Math.cos(latFrom) * Math.cos(latTo) * Math.cos(Math.abs(lonTo - lonFrom))
        );

        return Math.round(distance);
    }
}
