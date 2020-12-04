package com.example.androidhueapplicatie;

import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class HueLightTest {
    private HueLight hueLight = new HueLight(new State());

    @Test
    public void Brightness()throws InvalidParameterException {
        hueLight.setBrightness(255);
        hueLight.setBrightness(69);
        assertEquals(69, hueLight.getBrightness());
    }

    @Test
    public void Hue()throws InvalidParameterException {
        hueLight.setHue(65536);
        hueLight.setHue(420);
        assertEquals(420, hueLight.getHue());
    }

    @Test
    public void Saturation()throws InvalidParameterException {
        hueLight.setSaturation(255);
        hueLight.setSaturation(20);
        assertEquals(20, hueLight.getSaturation());
    }

    @Test
    public void Name() {
        hueLight.setName("Ricky");
        assertEquals("Ricky", hueLight.getName());
    }

    @Test
    public void Type() {
        hueLight.setType("Jorn007");
        assertEquals("Jorn007", hueLight.getType());
    }

    @Test
    public void Model() {
        hueLight.setModelId("Guilliam");
        assertEquals("Guilliam", hueLight.getModelId());
    }

    @Test
    public void On_Off() {
        hueLight.setOn(false);
        assertFalse(hueLight.getOn());
    }

    @Test
    public void ToString() {
        hueLight.setOn(true);
        hueLight.setBrightness(10);
        hueLight.setHue(10);
        hueLight.setSaturation(10);
        hueLight.setName("Test");
        hueLight.setType("Test");
        hueLight.setModelId("Test");
        assertEquals("HueLight{on=true, brightness=10, hue=10, saturation=10, name='Test', type='Test', modelId='Test'}", hueLight.toString());
    }
}