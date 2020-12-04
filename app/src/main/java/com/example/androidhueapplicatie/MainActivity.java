package com.example.androidhueapplicatie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.ListFragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements HueAdapter.onItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_holder);

        DataSingleton.getInstance().setContext(getApplicationContext());
        DataSingleton.getInstance().getManager().getHueLights();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListFragment()).commit();
    }


    @Override
    public void onItemClick(int position) {
        DataSingleton.getInstance().setPressedItem(position);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DetailLampActivity()).commit();
    }
}