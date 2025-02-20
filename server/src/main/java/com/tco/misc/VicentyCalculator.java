package com.tco.misc;

public class VicentyCalculator extends DistanceCalculator{
    @Override
    public long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
        double lat1 = from.getLatitude();
        double lon1 = from.getLongitude();
        double lat2 = to.getLatitude();
        double lon2 = to.getLongitude();

        return Math.round(computeVicentyDistance(lat1,lon1,lat2,lon2,earthRadius));

    }
}