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
}
