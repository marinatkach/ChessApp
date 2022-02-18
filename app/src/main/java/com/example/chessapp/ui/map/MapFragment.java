package com.example.chessapp.ui.map;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

import android.Manifest;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.chessapp.databinding.FragmentHomeBinding;
import com.example.chessapp.databinding.FragmentMapBinding;
import com.example.chessapp.ui.home.HomeViewModel;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class MapFragment extends Fragment {

    private FragmentMapBinding binding;
    private MapController controller;
    private MapView map;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        if (ContextCompat.checkSelfPermission(this.getActivity(),  Manifest.permission.ACCESS_FINE_LOCATION) == PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions( this.getActivity(),  new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1 );
        }

        Configuration.getInstance().load(getContext(), PreferenceManager.getDefaultSharedPreferences(getContext()));
        binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

         map = (MapView) binding.map;

        controller = new MapController(getContext(), map);
        controller.moveToMunchen();
        controller.updatePointsOnMap();

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