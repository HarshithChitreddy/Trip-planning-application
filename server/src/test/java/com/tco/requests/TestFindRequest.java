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

    @BeforeEach
    void setUp() {
        request = new FindRequest();
        where = new ArrayList<>();
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
    @DisplayName("lennoxxx: Test if where is set to null")
    public void testNullWhere(){
        request.setWhere(null);
        assertNull(request.getWhere());
    }

}
