package com.tco.misc;

import com.tco.requests.Place;

public class Select {
    private final static String TABLE = "cities";
    
    static String near(int limit, Place place, Integer distance) { 
        return "";
    }

    static String statement(String where, String data, String limit) {
        return "SELECT "
                  + data
                  + " FROM " + TABLE
                  + where
                  + limit
                  + " ;";
    }
}
