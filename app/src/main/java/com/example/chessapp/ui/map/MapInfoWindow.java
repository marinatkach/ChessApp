package com.example.chessapp.ui.map;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.example.chessapp.R;
import com.example.chessapp.storage.model.Place;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow;

/**
 * describes popup window in a map
 */
public class MapInfoWindow extends MarkerInfoWindow {


    private Place place; // place information

    public MapInfoWindow(int layoutResId, MapView mapView, Place place) {
        super(layoutResId, mapView);
        this.place = place;
    }


    /**
     * set context when windows are shown
     * @param arg0
     */
    @Override
    public void onOpen(Object arg0) {

        super.onOpen(arg0);

        Marker marker = (Marker)arg0;

        mView.findViewById(R.id.bubble_image).setVisibility(View.VISIBLE);

        TextView descr = mView.findViewById(R.id.bubble_subdescription);

        if(place.labels == null || place.labels.equals("")){
            descr.setVisibility(View.GONE);
        }
        else{
            descr.setVisibility(View.VISIBLE);
            descr.setText(place.labels);
            if(!place.labels.contains("\n")){
                descr.setGravity(Gravity.CENTER);
            }
        }



        ImageView btn = (ImageView) mView.findViewById(R.id.infoWindowImageBnt);
        btn.setOnClickListener((view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("placeId", place.id);
            bundle.putBoolean("movedFromMap", true);
            Navigation.findNavController(mMapView).navigate(R.id.action_nav_map_to_place_description, bundle);
        }));
    }
}