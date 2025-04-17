package com.tco.misc;

public class Select {
    private final static String TABLE = "cities";
    
    static String statement(String where, String data, String limit) {
        return "SELECT "
                  + data
                  + " FROM " + TABLE
                  + where
                  + limit
                  + " ;";
    }
}
