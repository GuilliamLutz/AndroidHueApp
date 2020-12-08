package com.example.androidhueapplicatie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_holder);

        DataSingleton.getInstance().setContext(getApplicationContext());
        DataSingleton.getInstance().setFragmentManager(getSupportFragmentManager());

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListFragment()).addToBackStack("Home").commit();

    }
}