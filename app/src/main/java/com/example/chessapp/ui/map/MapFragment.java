package com.example.chessapp.ui.map;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

import android.Manifest;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.chessapp.databinding.FragmentMapBinding;

import org.osmdroid.config.Configuration;
import org.osmdroid.views.MapView;

public class MapFragment extends Fragment {

    private FragmentMapBinding binding;
    private MapController controller;
    private MapView map;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

         map = (MapView) binding.map;

        controller = new MapController(this, map, binding.mapBtnMaskAsVisited);
        controller.moveToMunchen();
        controller.addPointsOnMap();
        binding.mapBtnMaskAsVisited.setOnClickListener(l -> {controller.onBntClicked();});

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}