package com.tco.misc;

import com.tco.misc.GeographicCoordinate;

public class VincentyCalculator implements DistanceCalculator {
    @Override
    public long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
        double lat1 = from.latRadians();
        double lon1 = from.lonRadians();
        double lat2 = to.latRadians();
        double lon2 = to.lonRadians();
        

        double deltaLambda = lon2 - lon1;

        double cosLat1 = Math.cos(lat1);
        double cosLat2 = Math.cos(lat2);
        double sinLat1 = Math.sin(lat1);
        double sinLat2 = Math.sin(lat2);

        double numerator = Math.sqrt(
            Math.pow(cosLat2 * Math.sin(deltaLambda), 2) +
            Math.pow(cosLat1 * sinLat2 - sinLat1 * cosLat2 * Math.cos(deltaLambda), 2)
        );

        double denominator = sinLat1 * sinLat2 + cosLat1 * cosLat2 * Math.cos(deltaLambda);

        double centralAngle = Math.atan2(numerator, denominator);

        double distance = earthRadius * centralAngle;

        return Math.round(distance);
    }

}