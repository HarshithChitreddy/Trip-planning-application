package com.tco.misc;

import com.tco.requests.Place;
import com.tco.requests.Places;

import java.util.ArrayList;
import java.util.List;

public class GeographicLocations {
    public Places near(Place place, long distance, double earthRadius, String formula, int limit) {
        Places nearPlaces = new Places();

        return nearPlaces;
    }

    public List<String> getTypes() {
        List<String> types = new ArrayList<>();
        types.add("city");
        return types;
    }
}
