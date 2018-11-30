package com.tripco.t03.server;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestConfig {

    private Config config;
    private String json;

    @Before
    public void setUp(){
        this.config = new Config();
        json = "{\"type\":\"config\",\"version\":5," +
                "\"units\":[\"miles\",\"kilometers\",\"nautical miles\",\"user defined\"]," +
                "\"optimization\":[{\"label\":\"none\",\"description\":\"The trip is not optimized.\"}," +
                "{\"label\":\"short\",\"description\":\"Nearest neighbor.\"}," +
                "{\"label\":\"shorter\",\"description\":\"2-opt.\"}," +
                "{\"label\":\"shortest\",\"description\":\"3-opt.\"}]," +
                "\"attributes\":[\"name\",\"id\",\"latitude\",\"longitude\",\"type\",\"elevation\"," +
                "\"continent\",\"country\",\"region\",\"municipality\"]," +
                "\"filters\":[{\"name\":\"continents\",\"values\":[\"Africa\",\"Antarctica\",\"Asia\",\"Europe\",\"North America\"," +
                "\"Oceania\",\"South America\"]}," +
                "{\"name\":\"type\",\"values\":[\"heliport\",\"small_airport\",\"seaplane_base\",\"closed\",\"balloonport\"," +
                "\"medium_airport\",\"large_airport\"]}],\"maps\":[\"svg\",\"kml\"]}";

        // "{\"label\":\"shortest\",\"description\":\"3-opt.\"}]," +        Add this back in when 3-opt works
    }

    @Test
    public void testConfig(){
        assertEquals(Config.getConfig(), json);
    }

    @Test
    public void testFalseConfig(){
        assertNotNull(config);
    }
}
