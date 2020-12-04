package com.example.androidhueapplicatie;

import com.google.gson.annotations.SerializedName;

public class HueLight {
    private State state;
    private String name;
    private String type;
    @SerializedName("modelid")
    private String modelId;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getModelId() {
        return modelId;
    }
    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
    public Boolean getOn() {
        return state.on;
    }
    public void setOn(Boolean on) {
        state.on = on;
    }
    public int getBrightness() {
        return state.brightness;
    }
    public void setBrightness(int brightness) {
        state.brightness = brightness;
    }
    public int getHue() {
        return state.hue;
    }
    public void setHue(int hue) {
        state.hue = hue;
    }
    public int getSaturation() {
        return state.saturation;
    }
    public void setSaturation(int saturation) {
        state.saturation = saturation;
    }

    @Override
    public String toString() {
        return "HueLight{" +
                "on=" + state.on +
                ", brightness=" + state.brightness +
                ", hue=" + state.hue +
                ", saturation=" + state.saturation +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", modelId='" + modelId + '\'' +
                '}';
    }

    public class  State{
        private Boolean on;
        @SerializedName("bri")
        private int brightness;
        private int hue;
        @SerializedName("sat")
        private int saturation;
    }
}
