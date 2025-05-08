package com.tco.requests;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class FindRequestTest {

    private FindRequest request;
    private Integer limit;
    private List<String> where;
    private Places places;

    @BeforeEach
    void setUp() {
        request = new FindRequest();
        where = new ArrayList<>();
        places = new Places();
    }

    @Test
    @DisplayName("lennoxxx: Test get/set limit")
    public void testGetSetLimit() {
        limit = 5;
        request.setLimit(limit);
        assertEquals(limit, request.getLimit());
    }

    @Test
    @DisplayName("lennoxxx: Test null limit")
    public void testNullLimit() {
        assertNull(request.getLimit());
    }

    @Test
    @DisplayName("lennoxxx: Test get/set where")
    public void testGetSetWhere(){
        where.add("Indianapolis");
        request.setWhere(where);
        assertEquals(where, request.getWhere());
    }

    @Test
    @DisplayName("lennoxxx: Test size of where")
    public void testSizeWhere(){
        where.add("Indianapolis");
        where.add("Denver");
        request.setWhere(where);
        assertEquals(2, request.getWhere().size());
    }

    @Test
    @DisplayName("lennoxxx: Test get/set places")
    public void testSetAndGetPlaces() {
        request.setPlaces(places);
        assertEquals(places, request.getPlaces());
    }
    @Test
    @DisplayName("lennoxxx: Test places null")
    public void testNullPlaces() {
        request.setPlaces(null);
        assertNull(request.getPlaces());
    }
}
