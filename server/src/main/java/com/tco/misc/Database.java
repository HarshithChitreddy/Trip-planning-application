package com.tco.misc;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

import com.tco.requests.Place;
import com.tco.requests.Places;

public class Database {
    private final static String COLUMNS = "city,city_ascii,city_alt,lat,lng,country,admin_name,admin_name_ascii,admin_type";

    static Places places(String sql, Integer limit) throws Exception {
        return new Places();
    }
}
