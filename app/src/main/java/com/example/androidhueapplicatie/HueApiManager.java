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

import okhttp3.Request;
import okhttp3.Response;

public class HueApiManager {
    private static final String LOGTAG = HueApiManager.class.getName();
//    private static final String IP = "192.168.178.27";
//    private static final String IP = "localhost";
//    private static final String IP = "192.168.178.12";
    private static final String IP = "192.168.99.144";

    @Override
    public void getLights() {
        //Make the uri immutable
        final String uri = bridgeUri+username+category;

        //keep mutable as it overwrites the result
        Lamp lamp;
        //Create request
        final okhttp3.Request allLightsRequest = new Request.Builder().url(uri).build();

        try (Response response = client.newCall(allLightsRequest).execute()){
            // Im using FasterXML Jackson to map JSON
            lamp = new ObjectMapper().readValue(response.body().string(), Lamp.class);
        } catch (IOException e) {
            Log.d(TAG, e.getLocalizedMessage());
            lamp = new Lamp();
        }

        return lamp;
    }


//    private RequestQueue queue;
//    private HueApiListener listener;
//    private Context appContext;

//    public  HueApiManager(Context context, HueApiListener listener) {
//        this.appContext = context;
//        this.listener = listener;
//        this.queue = Volley.newRequestQueue(this.appContext);
//    }

//    public void getHueLights() {
//        final String url = "http://" + IP + "/api/newdeveloper";
//        final JsonObjectRequest request = new JsonObjectRequest(
//                Request.Method.GET,
//                url,
//                null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d(LOGTAG, "Volley response: " + response.toString());
//                        try {
//                            JSONObject json = response.getJSONObject("lights");
//                            int i = 1;
//                            while (true) {
//                                String lightJson = json.getJSONObject(i + "").toString();
//                                HueLight light = new Gson().fromJson(lightJson, HueLight.class);
//                                listener.onLightsAvailable(light);
//                                Log.d(LOGTAG, "Light added: " + light);
//                                i++;
//                            }
//                        } catch (JSONException e) {
//                            Log.e(LOGTAG, "Error while parsing: " + e.getLocalizedMessage());
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(LOGTAG, error.getLocalizedMessage());
//                    }
//                }
//        );
//        this.queue.add(request);
//    }
//
//    public void setOnState(boolean state, String id) {
//        JSONObject json = new JSONObject();
//        try {
//            json.put("on", state);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        final String url = "http://" + IP + "/api/newdeveloper/lights/" + id + "/state";
//        Log.d(LOGTAG, "send onState: " + json);
//        final JsonObjectRequest request = new JsonObjectRequest(
//                Request.Method.PUT,
//                url,
//                json,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // response
//                        Log.d(LOGTAG, "Response: " + response.toString());
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Log.d(LOGTAG, "Error.Response: " + error.toString());
//                    }
//                }
//        );
//        this.queue.add(request);
//    }
//
//    public void setColorState(int hue, int saturation, int brightness, String id){
//        JSONObject json = new JSONObject();
//        try {
//            json.put("bri", brightness);
//            json.put("hue", hue);
//            json.put("sat", saturation);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        final String url = "http://" + IP + "/api/newdeveloper/lights/" + id + "/state";
//        Log.d(LOGTAG, "send colorState: " + json);
//        final JsonObjectRequest request = new JsonObjectRequest(
//                Request.Method.PUT,
//                url,
//                json,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // response
//                        Log.d(LOGTAG, "Response: " + response.toString());
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Log.d(LOGTAG, "Error.Response: " + error.toString());
//                    }
//                }
//        );
//        this.queue.add(request);
//    }
}
