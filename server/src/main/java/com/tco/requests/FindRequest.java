package com.tco.requests;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.misc.BadRequestException;
import com.tco.misc.GeographicCoordinate;
import com.tco.misc.GeographicLocations;
public class FindRequest extends Request{
    private Places places;
    protected Integer limit;
    protected String match;
    protected List<String> type;
    protected List<String> where;
    protected Integer found;
    private static final transient Logger log = LoggerFactory.getLogger(NearRequest.class);

    @Override
    public void buildResponse() throws BadRequestException{
        GeographicLocations geoLoc = new GeographicLocations();
        this.type = geoLoc.getTypes();
        
       
    try {
        this.found = geoLoc.found(match, type, where);
        places = geoLoc.find(match, type, where, limit);
    } catch (Exception e) {
        throw new BadRequestException();
    }


    log.trace("buildResponse -> {}", this);
}
    public void setMatch(String match){
        this.match = match;
    }
    public String getMatch() { return match; }
    
    public void setLimit(Integer limit){
        this.limit = limit;
    }
    public void setWhere(List<String> where){
        this.where = where;
    }

    public Integer getLimit(){return limit;}
    public List<String> getWhere(){return where;}

    public Places getPlaces(){return places;}
    public void setPlaces(Places places){
        this.places = places;
    }
}