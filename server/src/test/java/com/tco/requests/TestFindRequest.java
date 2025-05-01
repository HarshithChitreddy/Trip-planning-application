package com.tco.requests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class FindRequestTest {

    private FindRequest request;
    private Integer limit;

    @BeforeEach
    void setUp() {
        request = new FindRequest();
    }

    @Test
    @DisplayName("lennoxxx: Test get/set limit")
    void testGetSetLimit() {
        limit = 5;
        request.setLimit(limit);
        assertEquals(limit, request.getLimit());
    }

    @Test
    @DisplayName("lennoxxx: Test null limit")
    void testNullLimit() {
        assertNull(request.getLimit());
    }
}
