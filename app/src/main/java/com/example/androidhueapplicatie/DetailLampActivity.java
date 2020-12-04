package com.example.androidhueapplicatie;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorListener;

public class DetailLampActivity extends Fragment {

    private TextView nameLamp;
    private Switch onOffSwitch;
    private ColorPickerView colorPicker;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detaillamp_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.nameLamp = view.findViewById(R.id.detail_nameLamp);
        this.onOffSwitch = view.findViewById(R.id.detail_switch);
        this.colorPicker = view.findViewById(R.id.detail_colorPickerView);
        this.button = view.findViewById(R.id.detail_button);

        //set the lamp title/name
        nameLamp.setText("TEST");

        //On/Off switch listener
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DataSingleton.getInstance().getManager().setOnState(isChecked, "1");
            }
        });

        colorPicker.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {
                button.setBackgroundColor(color);
                button.setHighlightColor(color);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOffSwitch.isChecked()) {

                    float[] hsv = new float[3];
                    Color.colorToHSV(button.getHighlightColor(), hsv);
                    System.out.println(hsv[0] + " - " + hsv[1] + " - " + hsv[2]);
                    DataSingleton.getInstance().getManager().setColorState((int) (hsv[0] / 360 * 65535), (int) (hsv[1] * 255 - 1), (int) (hsv[2] * 255 - 1), "1");
                }
                else {
                    Toast.makeText(DataSingleton.getInstance().getAppContext(), "Lamp is off", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
