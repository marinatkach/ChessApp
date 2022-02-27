package com.example.chessapp.ui.gps;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.chessapp.databinding.FragmentGpsBinding;
import com.example.chessapp.helpers.GpsUtils;

import org.osmdroid.config.Configuration;

public class GpsController {


    private Context context;
    private GpsUtils utils;
    private GpsFragment fragment;


    public GpsController(GpsFragment fragment) {
        this.context = fragment.getContext();
        this.fragment = fragment;
        assert context != null;
        utils =new GpsUtils(fragment.getContext());
    }

    public void makeLocationRequest(){
        utils.getLastKnowLocation();
    }

    public void enableGps(){
        utils.turnGPSOn(e -> {
            utils.getLastKnowLocation();
        });
    }

    public void disableGps(GpsFragment fragment){
        fragment.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }

    public boolean isGpsEnabled(){
        LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public void checkAndRequestPermission(){
        if (ContextCompat.checkSelfPermission( fragment.getActivity(),  Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( fragment.getActivity(),  new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1 );
        }
        if (ContextCompat.checkSelfPermission( fragment.getActivity(),  Manifest.permission.ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( fragment.getActivity(),  new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1 );
        }
        Configuration.getInstance().load(fragment.getContext(), PreferenceManager.getDefaultSharedPreferences(fragment.getContext()));
    }


}