package com.example.androidhueapplicatie;

import android.content.Context;
import android.widget.Adapter;

import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

public class DataSingleton implements HueApiListener{
    private static DataSingleton instance = null;

    private Context appContext;
    private HueApiManager manager;
    private List<HueLight> lights;
    private int pressedItem;
    private FragmentManager fragmentManager;


    public int getPressedItem() {
        return pressedItem;
    }

    private HueAdapter adapter;

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
        adapter.updateData(this.lights);
        adapter.notifyDataSetChanged();
    }

    public void addAdapter(HueAdapter adapter){
        this.adapter = adapter;
    }

    public HueAdapter getAdapter() {
        return adapter;
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

    public void setPressedItem(int position) {
        this.pressedItem = position;
    }

    public void setFragmentManager(FragmentManager supportFragmentManager) {
        this.fragmentManager = supportFragmentManager;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }
}
