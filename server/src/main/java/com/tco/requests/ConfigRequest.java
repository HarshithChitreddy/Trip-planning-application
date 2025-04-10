package com.tco.requests;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tco.misc.Team;
import com.tco.misc.People;

public class ConfigRequest extends Request {

    private static final transient Logger log = LoggerFactory.getLogger(ConfigRequest.class);
    private List<String> formulae;
    private List<String> features;
    private Team team;
    private People people;

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
        
        
        log.trace("buildResponse -> {}", this);
    }


  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

    public ConfigRequest() {
        this.requestType = "config";
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
}
