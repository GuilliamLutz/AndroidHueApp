package com.example.androidhueapplicatie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListFragment extends Fragment {

    private RecyclerView lampView;
    private HueAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.lampView = view.findViewById(R.id.lampList);
        this.listAdapter = new HueAdapter(DataSingleton.getInstance().getLights());
        this.lampView.setAdapter(this.listAdapter);
        this.lampView.setLayoutManager(new LinearLayoutManager(DataSingleton.getInstance().getAppContext()));
        DataSingleton.getInstance().addAdapter(this.listAdapter);
    }
}

