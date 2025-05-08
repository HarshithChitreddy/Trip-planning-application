package com.tco.misc;

import java.util.List;

import com.tco.requests.Place;

public class Select {
    private final static String TABLE = "cities";
    private final static String COLUMNS = "city,city_ascii,city_alt,lat,lng,country,admin_name,admin_name_ascii,admin_type";
    
    static String near(Integer limit, Place place, Integer distance) { 
        // No wrap around, hard cap at 90/-90
        Double latRange = (double) distance / 69;
        
        Double latLowValue = Double.parseDouble(place.get("latitude")) - latRange;
        latLowValue = Math.max(latLowValue, -90.0); // Can't be lower than -90.0
        String latLow = String.valueOf(latLowValue);

        Double latHighValue = Double.parseDouble(place.get("latitude")) + latRange;
        latHighValue = Math.min(latHighValue, 90.0);
        String latHigh = String.valueOf(latHighValue);

        // Longitude values that go above 180/below -180, convert to -/+
        Double lonRange = (double) distance / (69 * Math.cos(Double.parseDouble(place.get("latitude"))));

        Double lonLowValue = Double.parseDouble(place.get("longitude")) - lonRange;
        String lonLow = String.valueOf(lonLowValue);

        Double lonHighValue = Double.parseDouble(place.get("longitude")) + lonRange;
        String lonHigh = String.valueOf(lonHighValue);
        
        // international date line wrapping at 180,-180
        String where =  " WHERE lat BETWEEN " + latLow + " AND " + latHigh + " AND lng BETWEEN " + 
            Math.min(Double.parseDouble(lonHigh), Double.parseDouble(lonLow)) + " AND " + Math.max(Double.parseDouble(lonHigh), Double.parseDouble(lonLow)) + " ";

        // Order by the small difference between places lat/long value and the values in the database
        String orderBy = "ORDER BY ABS(lat - " + place.get("latitude") + ") + ABS(lng - " + place.get("longitude") + ") ";
        return statement(where, COLUMNS + " ", orderBy + "LIMIT " + limit);
    }
    static String found(String match, List<String> type, List<String> here) {
        String joined = String.join(",", type); // cities
        String joineds = String.join(",", here);
        String where = " WHERE " + joined + " LIKE \"%" + match + "%\" AND country IN (" + joineds + " )";
        return statement(where, "COUNT(*) AS count ", "");
    }
    static String find(String match,List<String> type, String[] here, int limit) {
        String joineds = String.join(",", here);
        String joined = String.join(",", type);
        String where = " WHERE " + joined + " LIKE \"%" + match + "%\" AND country IN (" + joineds + ")";
         return statement(where, COLUMNS + " ", "LIMIT " + limit);
        
    }
    
    private static String statement(String where, String data, String limit) {
        return "SELECT "
                  + data
                  + " FROM " + TABLE
                  + where
                  + limit
                  + " ;";
    }
    
    
}
