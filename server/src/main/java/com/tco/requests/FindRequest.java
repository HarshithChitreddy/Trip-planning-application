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
    protected String[] where;
    protected Integer found;
    private static final transient Logger log = LoggerFactory.getLogger(NearRequest.class);

@Override
public void buildResponse() throws BadRequestException{
    
}
public void setLimit(Integer limit){
    this.limit = limit;
}
public Integer getLimit(){
    return limit;
}
}