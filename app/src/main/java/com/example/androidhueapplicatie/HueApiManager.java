package com.example.androidhueapplicatie;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HueApiManager {
    private static final String LOGTAG = HueApiManager.class.getName();
    private static final String IP = "10.0.2.2";

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private OkHttpClient client;
    private HueApiListener listener;
    private Context appContext;

    public  HueApiManager(Context context, HueApiListener listener) {
        this.appContext = context;
        this.listener = listener;
        this.client = new OkHttpClient();
    }

    public void getHueLights() {
        //Make the uri immutable
        final String uri = "http://" + IP + "/api/newdeveloper";

        Request request = new Request.Builder()
                .url(uri)
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
        final String url = "http://" + IP + "/api/newdeveloper/lights/" + id + "/state";

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
        final String url = "http://" + IP + "/api/newdeveloper/lights/" + id + "/state";

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
