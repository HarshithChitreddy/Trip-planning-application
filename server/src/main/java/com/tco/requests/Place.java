package com.tco.requests;

import java.util.HashMap;
import java.lang.Math;
import com.tco.misc.GeographicCoordinate;

public class Place extends HashMap<String, String> implements GeographicCoordinate {
    @Override
    public double latRadians() {
        return Math.toRadians(Double.parseDouble(this.get("latitude")));
    }

    public double lonRadians() {
        return 0.0;
    }

}
