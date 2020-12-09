package com.example.androidhueapplicatie;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HueApiManager {
    private static final String LOGTAG = HueApiManager.class.getName();

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private OkHttpClient client;
    private HueApiListener listener;
    private String getUrl = "http://10.0.2.2/api/newdeveloper";


    public  HueApiManager(HueApiListener listener) {
        this.listener = listener;
        this.client = new OkHttpClient();
    }

    public void setGetUrl(String getUrl) {
        this.getUrl = getUrl;
    }

    public void getHueLights() {
        //Make the uri immutable


        Request request = new Request.Builder()
                .url(getUrl)
                .build();

        Thread t = new Thread(()-> {
            try (Response response = client.newCall(request).execute();) {
                String responseString = response.body().string();
                JSONObject fullJson = new JSONObject(responseString);
                JSONObject json = fullJson.getJSONObject("lights");
                int i = 1;
                while (true) {
                    String lightJson = json.getJSONObject(i + "").toString();
                    HueLight light = new Gson().fromJson(lightJson, HueLight.class);
                    listener.onLightsAvailable(light);
                    Log.d(LOGTAG, "Light added: " + light);
                    i++;
                }
            } catch (Exception e) {
                Log.e(LOGTAG, "Error while parsing: " + e.getLocalizedMessage());
            }
        });
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setOnState(boolean state, String id) {
        final String url = "http://10.0.2.2/api/newdeveloper/lights/" + id + "/state";

        JSONObject json = new JSONObject();
        try {
            json.put("on", state);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .build();

        Thread t = new Thread(() -> {
            try (Response response = client.newCall(request).execute()) {

            } catch (IOException e) {
                Log.d(LOGTAG, "Error while changing light settings" + e.getLocalizedMessage());
            }
        });
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setColorState(int hue, int saturation, int brightness, String id){
        final String url = "http://10.0.2.2/api/newdeveloper/lights/" + id + "/state";

        JSONObject json = new JSONObject();
        try {
            json.put("bri", brightness);
            json.put("hue", hue);
            json.put("sat", saturation);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .build();

        Thread t = new Thread(() -> {
            try (Response response = client.newCall(request).execute()) {

            } catch (IOException e) {
                Log.d(LOGTAG, "Error while changing light settings" + e.getLocalizedMessage());
            }
        });
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
