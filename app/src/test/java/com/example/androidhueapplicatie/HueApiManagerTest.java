package com.example.androidhueapplicatie;

import org.junit.Test;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockWebServer;

import static org.junit.Assert.*;

public class HueApiManagerTest {

    @Test
    void testIntegrationHueApiManager() {

        HttpUrl baseUrl = MockWebServer.url("/api/newdeveloper/lights");
        

    }

}