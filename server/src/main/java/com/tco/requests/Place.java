package com.tco.requests;

import java.util.HashMap;
import java.lang.Math;
import com.tco.misc.GeographicCoordinate;

public class Place extends HashMap<String, String> implements GeographicCoordinate {

    public double latRadians() {
        return 0.0;
    }

    public double lonRadians() {
        return 0.0;
    }

}