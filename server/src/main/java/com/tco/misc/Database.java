package com.tco.misc;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import com.tco.requests.Place;
import com.tco.requests.Places;

public class Database {
    private final static String COLUMNS = "city,city_ascii,city_alt,lat,lng,country,admin_name,admin_name_ascii,admin_type";

    static Places places(String sql, Integer limit) throws Exception {
        String url = Credential.URL;
        String user = Credential.USER;
        String password = Credential.PASSWORD;
        try (
              // connect to the database and query
              Connection conn = DriverManager.getConnection(url, user, password);
              Statement query = conn.createStatement();
              ResultSet results = query.executeQuery(sql);
        ) {
            return convertQueryResultsToPlaces(results, COLUMNS);
        } catch (Exception e) {
            throw e;
        }
    }

    private static Places convertQueryResultsToPlaces(ResultSet results, String columns) throws Exception {
        String[] cols = columns.split(",");
        Places places = new Places();
        while (results.next()) {
            Place place = new Place();
            for (String col : cols) {
                if (col.equals("city")) {
                    place.put("name", results.getString(col));
                    place.put("municipality", results.getString(col));
                } else if (col.equals("lat")) {
                    place.put("latitude", results.getString(col));
                } else if (col.equals("lng")) {
                    place.put("longitude", results.getString(col));
                } else if (col.equals("country")) {
                    place.put("country", results.getString(col));
                } else if (col.equals("admin_name")) {
                    place.put("region", results.getString(col));
                }
            }
            places.add(place);
        }
        return places;
    }
    private static Integer count(ResultSet results) throws Exception {
        if (results.next()) {
            return results.getInt("count");
        }
        throw new Exception("No count results in found query.");
    }
    public Places find(String match, List<String> type, List<String> where, Integer limit) throws Exception{


        return null;
    }
    static Integer found(String sql) throws Exception {

        try (
              // connect to the database and query
              Connection conn = DriverManager.getConnection(Credential.URL, Credential.USER,
                    Credential.PASSWORD);
              Statement query = conn.createStatement();
              ResultSet results = query.executeQuery(sql)
        ) {
            return count(results);
        } catch (Exception e) {
            throw e;
        }
    }
}
