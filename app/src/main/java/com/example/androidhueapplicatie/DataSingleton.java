package com.example.androidhueapplicatie;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class DataSingleton implements HueApiListener{
    private static DataSingleton instance = null;

    private Context appContext;
    private HueApiManager manager;
    private List<HueLight> lights;
    private DataSingleton() {
        lights = new ArrayList<>();
    }

    public static DataSingleton getInstance() {
        if (instance == null) {
            instance = new DataSingleton();
        }
        return instance;
    }

    public void setContext(Context context){
        this.appContext = context;
        this.manager = new HueApiManager(context, this);
    }

    public void addLight(HueLight light) {
        lights.add(light);
    }

    public List<HueLight> getLights() {
        return lights;
    }

    @Override
    public void onLightsAvailable(HueLight light) {
        addLight(light);
    }

    public void clearLights() {
        lights = new ArrayList<>();
    }

    public Context getAppContext() {
        return appContext;
    }

    public HueApiManager getManager() {
        return manager;
    }
}
