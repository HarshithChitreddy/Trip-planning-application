package com.tco.misc;

public class HaversineCalculator implements DistanceCalculator {
    
    @Override
    public long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
        double lat1 = from.latRadians();
        double lon1 = from.lonRadians();
        double lat2 = to.latRadians();
        double lon2 = to.lonRadians();
        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        double result = haversineFormula(lat1, lat2, deltaLat, deltaLon, earthRadius);
        return Math.round(result);
        
    }


    private double haversineFormula(double lat1, double lat2, double deltaLat, double deltaLon, double earthRadius){

         double sinLat = Math.pow(Math.sin(deltaLat/2),2);
         double cosLat = Math.pow(Math.cos((lat1 + lat2)/2), 2);
         double sinLon = Math.pow(Math.sin(deltaLon/2),2);
         double result = Math.asin(Math.sqrt(sinLat + (cosLat - sinLat) * sinLon))*2;

         return earthRadius * result;



    }

}
