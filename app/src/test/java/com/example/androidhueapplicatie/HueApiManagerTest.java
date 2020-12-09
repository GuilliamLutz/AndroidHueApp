package com.example.androidhueapplicatie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.junit.Assert.*;

public class HueApiManagerTest implements HueApiListener {
    final MockWebServer mockWebServer = new MockWebServer();


    @Before
    public void setUp() throws IOException {
        mockWebServer.start();
    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void testIntegrationHueApiManager() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(
                        "{\"lights\":{\"1\":{\"state\":{\"on\":true,\"bri\":254,\"hue\":4444,\"sat\":254,\"xy\":[0.0,0.0],\"ct\":0,\"alert\":\"none\",\"effect\":\"none\",\"colormode\":\"hs\",\"reachable\":true},\"type\":\"Extended color light\",\"name\":\"Hue Lamp 1\",\"modelid\":\"LCT001\",\"swversion\":\"65003148\",\"uniqueid\":\"00:17:88:01:00:d4:12:08-0a\",\"pointsymbol\":{\"1\":\"none\",\"2\":\"none\",\"3\":\"none\",\"4\":\"none\",\"5\":\"none\",\"6\":\"none\",\"7\":\"none\",\"8\":\"none\"}}}}"
                ));
        HttpUrl baseUrl = mockWebServer.url("/api/newdeveloper/lights");
        HueApiManager manager = new HueApiManager(this);
        manager.setGetUrl(baseUrl.toString());
        manager.getHueLights();
    }

    @Override
    public void onLightsAvailable(HueLight light) {
        assertEquals("LCT001", light.getModelId());
        assertEquals(4444, light.getHue());
    }
}