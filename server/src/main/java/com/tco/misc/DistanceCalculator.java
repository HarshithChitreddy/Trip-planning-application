package com.tco.misc;
import com.tco.misc.GeographicCoordinate;

public interface DistanceCalculator {

public long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius);

}