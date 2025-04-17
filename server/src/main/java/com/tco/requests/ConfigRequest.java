package com.tco.requests;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tco.misc.Team;
import com.tco.misc.People;
import com.tco.misc.GeographicLocations;

public class ConfigRequest extends Request {

    private static final transient Logger log = LoggerFactory.getLogger(ConfigRequest.class);
    private List<String> formulae;
    private List<String> features;
    private Team team;
    private People people;
    // private GeographicLocations geographicLocations;

    @Override
    public void buildResponse() {

        team = new Team();
        people = new People();
        formulae = new ArrayList<>();
        features = new ArrayList<>();
        features.add("config");
        features.add("distances");
        features.add("tour");
        features.add("near");

        formulae.add("vincenty");
        formulae.add("haversine");
        formulae.add("cosines");

        // geographicLocations = new GeographicLocations();
        
        log.trace("buildResponse -> {}", this);
    }


  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

    public ConfigRequest() {
        this.requestType = "config";
        // this.geographicLocations = new GeographicLocations();
    }

    public List<String> getFeatures() {
        return features;
    }

    public Team getTeam() { return team; }

    public People getPeople() {
        return people;
    }

    public boolean validFeature(String feature){
        return features.contains(feature);
    }

//     public List<String> getLocationTypes() {
//         return geographicLocations.getTypes(); 
//     }
}
