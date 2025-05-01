package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import com.tco.misc.BadRequestException;

import static org.junit.jupiter.api.Assertions.*;

public class TestNearRequest {

    private NearRequest request;

    @BeforeEach
    public void setup() {
        request = new NearRequest();
        request.setDistance(300);
        request.setEarthRadius(3959.0);
        request.setFormula("great-circle");
        request.setLimit(5);
    }

    @Test
    @DisplayName("reddy17: Longitude below -180 should throw BadRequestException")
    public void testLongitudeTooLow() {
        Place invalidPlace = new Place();
        invalidPlace.put("latitude", "40.0");
        invalidPlace.put("longitude", "-181.0");

        setPrivateField(request, "place", invalidPlace);

        assertThrows(BadRequestException.class, () -> {
            request.buildResponse();
        });
    }

    @Test
    @DisplayName("reddy17: Longitude above 180 should throw BadRequestException")
    public void testLongitudeTooHigh() {
        Place invalidPlace = new Place();
        invalidPlace.put("latitude", "40.0");
        invalidPlace.put("longitude", "181.0");

        setPrivateField(request, "place", invalidPlace);

        assertThrows(BadRequestException.class, () -> {
            request.buildResponse();
        });
    }

    @Test
    @DisplayName("reddy17: Valid longitude should not throw error")
    public void testValidLongitude() {
        Place validPlace = new Place();
        validPlace.put("latitude", "40.0");
        validPlace.put("longitude", "100.0");

        setPrivateField(request, "place", validPlace);

        assertDoesNotThrow(() -> {
            request.buildResponse();
        });
    }

    private void setPrivateField(NearRequest request, Place place) {
        try {
            java.lang.reflect.Field field = NearRequest.class.getDeclaredField("place");
            field.setAccessible(true);
            field.set(request, place);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
