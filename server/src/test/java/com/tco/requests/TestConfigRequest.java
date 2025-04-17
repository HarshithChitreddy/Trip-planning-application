package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestConfigRequest {

    private ConfigRequest conf;

    @BeforeEach
    public void createConfigurationForTestCases() {
        conf = new ConfigRequest();
        conf.buildResponse();
    }

    @Test
    @DisplayName("base: Request type is \"config\"")
    public void testType() {
        String type = conf.getRequestType();
        assertEquals("config", type);
    }

    @Test
    @DisplayName("base: Features includes \"config\"")
    public void testFeatures(){
        assertTrue(conf.validFeature("config"));
    }

    @Test
    @DisplayName("dnweath: Features includes \"distances\"")
    public void testDistancesFeature() {
        assertTrue(conf.validFeature("distances"));
    }

    @Test
    @DisplayName("dnweath: Features includes \"tour\"")
    public void testTourFeature() {
        assertTrue(conf.validFeature("tour"));
    }

    @Test
    @DisplayName("base: Features list is expected length")
    public void testFeaturesLength(){
        assertEquals(conf.getFeatures().size(), 4);
    }

    /*
    @Test
    @DisplayName("base: Team name is correct")
    public void testServerName() {
        String name = conf.getServerName();
        assertEquals("Team Name", name);
    }

    @Test
    @DisplayName("base: Team number is correct")
    public void testTeamNumber() {
        String teamNumber = conf.getTeamNumber();
        assertEquals("t41", teamNumber);
    }

    @Test
    @DisplayName("base: Mission statement is correct")
    public void testMissionStatement() {
        String missionStatement = conf.getMissionStatement();
        assertEquals("Insert your team's mission statement here! Lorem ipsum odor amet, consectetuer adipiscing elit. Sociosqu nisi ut luctus dapibus platea justo justo. Diam ridiculus sem nisi consequat senectus sagittis tempus neque. Sem faucibus netus velit odio ridiculus porta. Sit vulputate sollicitudin penatibus dolor, velit eu molestie. Semper quis velit ridiculus bibendum elit. Vel sollicitudin eu quisque ligula felis eleifend, quis in curae. Metus convallis dis pellentesque posuere et sit suspendisse potenti. Lacinia dignissim duis vel urna dignissim pellentesque litora tempor. Netus vulputate commodo dolor aptent efficitur.",
                     missionStatement);
    }

    @Test
    @DisplayName("base: People list is expected length")
    public void testPeopleLength(){
        assertEquals(conf.getPeople().size(), 5);
    }
    */

    // @Test
    // @DisplayName("reddy17: Verify getLocationTypes returns 'city' for geographic locations")
    // public void testGetLocationTypes() {
    //     ConfigRequest configRequest = new ConfigRequest();
    //     assertTrue(configRequest.getLocationTypes().contains("city"), "Location types should contain 'city'");
    // }
}