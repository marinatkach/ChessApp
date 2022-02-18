package com.example.chessapp.ui.gps;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.chessapp.databinding.FragmentGpsBinding;
import com.example.chessapp.helpers.GpsUtils;

public class GpsController {


    private Context context;
    private GpsUtils utils;


    public GpsController(Context context) {
        this.context = context;
        utils =new GpsUtils(context);
    }

    public void enableGps(){
        utils.turnGPSOn(e -> {});
    }

    public void disableGps(GpsFragment fragment){
        fragment.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }

    public boolean isGpsEnabled(){
        LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        return  locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


}