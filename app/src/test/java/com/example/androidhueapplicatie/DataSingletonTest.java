package com.example.androidhueapplicatie;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DataSingletonTest {

    @Test
    public void getInstance() {
        assertNotNull(DataSingleton.getInstance());
        assertEquals(DataSingleton.getInstance(), DataSingleton.getInstance());
    }

    @Test
    public void Context() {
        DataSingleton.getInstance().setContext(null);
        assertNull(DataSingleton.getInstance().getAppContext());
    }

    @Test
    public void onLightsAvailable() {
//        DataSingleton.getInstance().addAdapter(new HueAdapter(new ArrayList<>()));
//        HueLight light = new HueLight(new State());
//        DataSingleton.getInstance().onLightsAvailable(light);
//        assertEquals(new ArrayList<HueLight>().add(light), DataSingleton.getInstance().getLights());
    }

    @Test
    public void addAdapter() {
        HueAdapter adapter = new HueAdapter(new ArrayList<>());
        DataSingleton.getInstance().addAdapter(adapter);
        assertEquals(adapter, DataSingleton.getInstance().getAdapter());

    }

    @Test
    public void clearLights() {
        HueLight light1 = new HueLight(new State());
        for (int i = 0; i <5 ; i++) {
            DataSingleton.getInstance().addLight(light1);
        }
        DataSingleton.getInstance().clearLights();
        assertEquals(new ArrayList<HueLight>(), DataSingleton.getInstance().getLights());
    }

    @Test
    public void setPressedItem() {
        DataSingleton.getInstance().setPressedItem(1);
        assertEquals(1, DataSingleton.getInstance().getPressedItem());
        DataSingleton.getInstance().setPressedItem(2);
        assertEquals(2, DataSingleton.getInstance().getPressedItem());
    }

    @Test
    public void setFragmentManager() {
        DataSingleton.getInstance().setFragmentManager(null);
        assertNull(DataSingleton.getInstance().getFragmentManager());
    }
}