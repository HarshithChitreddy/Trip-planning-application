package com.tco.misc;
import com.tco.requests.Places;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TestDatabase {
    private String sql;
    private Places places;
    
    @BeforeEach
    public void setUp() {
        this.sql = "";
        this.places = new Places();
    }

    @Test
    @DisplayName("dnweath: Test places array is populated correctly with 1 place")
    public void testOnePlace() throws Exception {
        sql = "SELECT * FROM cities LIMIT 1;";
        places = Database.places(sql, 1);

        assertTrue(places.size() == 1);
    }

    @Test
    @DisplayName("dnweath: Test places array is populated correctly with 10 place")
    public void testTenPlaces() throws Exception {
        sql = "SELECT * FROM cities LIMIT 10;";
        places = Database.places(sql, 10);

        assertTrue(places.size() == 10);
    }

    @Test
    @DisplayName("dnweath: Test that exception is thrown with invalid query")
    public void testInvalidQuery() throws Exception {
        sql = "SELECT * FOM cities LIMIT 10;";
        
        assertThrows(Exception.class, () -> {
            Database.places(sql, 10);
        });
    }

    @Test
    @DisplayName("dnweath: Test that exception is thrown when accessing invalid table")
    public void testInvalidTableAccess() throws Exception {
        sql = "SELECT * FROM city LIMIT 10;";
        
        assertThrows(Exception.class, () -> {
            Database.places(sql, 10);
        });
    }
}
