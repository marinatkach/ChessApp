package com.example.chessapp.ui.map;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.example.chessapp.R;
import com.example.chessapp.storage.model.Place;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow;

public class MapInfoWindow extends MarkerInfoWindow {


    private Place place;

    public MapInfoWindow(int layoutResId, MapView mapView, Place place) {
        super(layoutResId, mapView);
        this.place = place;
    }


    @Override
    public void onOpen(Object arg0) {

        super.onOpen(arg0);

        Marker marker = (Marker)arg0;


//        TextView text  = (TextView) mView.findViewById(R.id.infoWindowTextView);
        mView.findViewById(R.id.bubble_image).setVisibility(View.VISIBLE);

        ImageView btn = (ImageView) mView.findViewById(R.id.infoWindowImageBnt);
//        text.setText(marker.getTitle());
//
        btn.setOnClickListener((view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("placeId", place.id);
            Navigation.findNavController(mMapView).navigate(R.id.action_nav_map_to_place_description, bundle);
        }));
    }






}