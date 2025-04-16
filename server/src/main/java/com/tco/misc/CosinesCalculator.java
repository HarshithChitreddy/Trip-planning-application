package com.tco.misc;

public class CosinesCalculator implements DistanceCalculator {

    @Override
    public long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
        double latFrom = from.latRadians();
        double latTo = to.latRadians();
        double lonFrom = from.lonRadians();
        double lonTo = to.lonRadians();

        if (latFrom < -Math.PI/2 || latFrom > Math.PI/2 ||
            latTo   < -Math.PI/2 || latTo   > Math.PI/2 ||
            lonFrom < -Math.PI   || lonFrom > Math.PI   ||
            lonTo   < -Math.PI   || lonTo   > Math.PI) {
            throw new IllegalArgumentException("Latitude must be in [-π/2, π/2] and longitude in [-π, π].");
        }

        double distance = earthRadius * Math.acos(
            Math.sin(latFrom) * Math.sin(latTo) 
            + Math.cos(latFrom) * Math.cos(latTo) * Math.cos(Math.abs(lonTo - lonFrom))
        );

        return Math.round(distance);
    }
}
