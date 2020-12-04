package com.example.androidhueapplicatie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment);

        DataSingleton.getInstance().setContext(getApplicationContext());
        DataSingleton.getInstance().getManager().getHueLights();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DetailLampActivity()).commit();
    }


}