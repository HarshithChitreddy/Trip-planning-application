package com.tco.misc;

public class VicentyCalculator extends DistanceCalculator{
    @Override
    public long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
        double lat1 = from.latitudeRadians();
        double lon1 = from.longitudeRadians();
        double lat2 = to.latitudeRadians();
        double lon2 = to.longitudeRadians();

        return Math.round(computeVicentyDistance(lat1,lon1,lat2,lon2,earthRadius));

    }
}