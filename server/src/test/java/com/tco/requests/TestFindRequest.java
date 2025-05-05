package com.tco.requests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class FindRequestTest {

    private FindRequest request;
    private Integer limit;
    private String[] where;

    @BeforeEach
    void setUp() {
        request = new FindRequest();
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
        where = new String[] {"Indianapolis"};
        request.setWhere(where);
        assertEquals(where, request.getWhere());
    }

    @Test
    @DisplayName("lennoxxx: Test size of where")
    public void testSizeWhere(){
        where = new String[] {"Indianapolis", "Denver"};
        request.setWhere(where);
        assertEquals(2, request.getWhere().length);
    }

    @Test
    @DisplayName("lennoxxx: Test if where is set to null")
    public void testNullWhere(){
        request.setWhere(null);
        assertNull(request.getWhere());
    }

}
