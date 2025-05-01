package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.misc.BadRequestException;
import com.tco.misc.GeographicLocations;

public class NearRequest extends Request {
    private Places places;
    private Place place;
    private Distances distances;
    protected int distance;
    protected double earthRadius;
    protected String formula;
    protected int limit;

    private static final transient Logger log = LoggerFactory.getLogger(NearRequest.class);

    @Override
    public void buildResponse() throws BadRequestException {
        GeographicLocations geoLoc = new GeographicLocations();

        try {
            if (place == null) {
                log.error("Place object is null.");
                throw new BadRequestException();
            }

            log.info("Place values received: {}", place);

            String lonStr = place.get("longitude");
            if (lonStr == null) {
                log.error("Longitude is missing in the place.");
                throw new BadRequestException();
            }

            double lon = Double.parseDouble(lonStr);
            if (lon < -180.0 || lon > 180.0) {
                throw new IllegalArgumentException("Longitude out of bounds: " + lon);
            }

            places = geoLoc.near(place, distance, earthRadius, formula, limit);
            this.distances = geoLoc.distances(place, places);

        } catch (IllegalArgumentException e) {
            log.error("Validation error: {}", e.getMessage());
            throw new BadRequestException();
        } catch (Exception e) {
            log.error("Unexpected error in near request: {}", e.getMessage());
            throw new BadRequestException();
        }

        log.trace("buildResponse -> {}", this);
    }

    public void setDistance(int distance) { this.distance = distance; }
    public void setFormula(String formula) { this.formula = formula; }
    public void setEarthRadius(double earthRadius) { this.earthRadius = earthRadius; }
    public void setLimit(int limit) { this.limit = limit; }

    public int getDistance() { return distance; }
    public String getFormula() { return formula; }
    public double getEarthRadius() { return earthRadius; }
    public int getLimit() { return limit; }
}