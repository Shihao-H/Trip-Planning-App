package com.tripco.t03.server;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class TestConfig {

    private Config config;
    private String json;

    @Before
    public void setUp(){
        this.config = new Config();
        json = "{\"type\":\"config\",\"version\":3,\"units\":[\"miles\",\"kilometers\",\"nautical miles\",\"user defined\"],\"optimization\":"
                + "[{\"label\":\"none\",\"description\":\"The trip is not optimized.\"},{\"label\":\"short\",\"description\":\"Nearest neighbor.\"}]," +
                "\"attributes\":[\"name\",\"id\",\"latitude\",\"longitude\"]}";
    }

    @Test
    public void testConfig(){
        assertEquals(Config.getConfig(), json);
    }

    @Test
    public void testFalseConfig(){
        assertFalse(config == null);
    }
}
