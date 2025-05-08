package com.tco.misc;

import com.tco.requests.Place;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TestSelect {
    Place place;
    Integer distance = 50;
    Integer limit = 10;
    
    @BeforeEach
    public void setUp(){
        this.place = new Place();
    }
    
    @Test
    @DisplayName("dnweath: Value close to 90.0 returns range no greater than 90.0")
    public void testLargeLatRange(){
        place.put("latitude", "89.5");
        place.put("longitude", "100");

        String query = Select.near(limit, place, distance);
        
        String[] queryElements = query.split(" ");
        
        assertTrue(queryElements[10].equals("90.0"));
    }

    @Test
    @DisplayName("dnweath: Value close to -90.0 returns range no smaller than -90.0")
    public void testSmallLatRange(){
        place.put("latitude", "-89.5");
        place.put("longitude", "100");

        String query = Select.near(limit, place, distance);

        String[] queryElements = query.split(" ");
        
        assertTrue(queryElements[8].equals("-90.0"));
    }
    
    @DisplayName("chrisc23: basic SQL Structure")
    @Test
    public void testNearBasicSQLStructure() {
        Place place = new Place();
        place.put("latitude", "40.0");
        place.put("longitude", "-105.0");

        String sql = Select.near(5, place, 100);

        assertNotNull(sql);
        assertTrue(sql.contains("SELECT"));
        assertTrue(sql.contains("FROM cities"));
        assertTrue(sql.contains("lat BETWEEN"));
        assertTrue(sql.contains("lng BETWEEN"));
        assertTrue(sql.contains("LIMIT 5"));
    }

    @Test
    @DisplayName("dnweath: Values close to 180 wrap properly")
    public void testLargeLonRange(){
        place.put("latitude", "50");
        place.put("longitude", "179.9");

        String query = Select.near(limit, place, distance);
        
        String[] queryElements = query.split(" ");
        
        assertTrue(queryElements[16].equals("180.65094631288915"));
    }

    @Test
    @DisplayName("dnweath: Values close to -180 wrap properly")
    public void testSmallLonRange(){
        place.put("latitude", "50");
        place.put("longitude", "-179.9");

        String query = Select.near(limit, place, distance);
        
        String[] queryElements = query.split(" ");
        
        assertTrue(queryElements[14].equals("-180.65094631288915"));
    }
    
}
