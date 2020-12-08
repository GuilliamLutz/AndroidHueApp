package com.example.androidhueapplicatie;

import com.google.gson.annotations.SerializedName;

import java.security.InvalidParameterException;

public class HueLight {
    private State state;
    private String name;
    private String type;
    @SerializedName("modelid")
    private String modelId;

    public HueLight(State state) {
        this.state = state;
    }

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
    public void setBrightness(int brightness) {
        if (brightness < 254 || 0 < brightness) {
            this.state.brightness = brightness;
        }
        else {
            throw new InvalidParameterException("Value must be between 0 and 255");
        }
    }
    public int getBrightness() {
        return state.brightness;
    }
    public int getHue() {
        return state.hue;
    }
    public void setHue(int hue) {
        if (hue < 65535 || 0 < hue) {
            this.state.hue = hue;
        }
        else {
            throw new InvalidParameterException("Value must be between 0 and 65535");
        }
    }
    public int getSaturation() {
        return state.saturation;
    }
    public void setSaturation(int saturation) {
        if (saturation < 254 || 0 < saturation) {
            this.state.saturation = saturation;
        }
        else {
            throw new InvalidParameterException("Value must be between 0 and 255");
        }
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
}

class State{
    public State() {
    }

    public Boolean on;
    @SerializedName("bri")
    public int brightness;
    public int hue;
    @SerializedName("sat")
    public int saturation;
}
