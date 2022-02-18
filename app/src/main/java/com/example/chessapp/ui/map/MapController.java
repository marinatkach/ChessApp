package com.example.chessapp.ui.map;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;

import com.example.chessapp.Application;
import com.example.chessapp.R;
import com.example.chessapp.storage.model.Place;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.List;

public class MapController {

    private List<Place> places = Application.placeTable.all();

    private MapView mapView;
    private Context context;

    public MapController(Context context, MapView mapView){
        this.mapView = mapView;
        this.context = context;
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(12.0);
        mapView.setTileSource(TileSourceFactory.MAPNIK);

        MyLocationNewOverlay myLocationNewOverlay
                = new MyLocationNewOverlay(new GpsMyLocationProvider(mapView.getContext()),mapView);
        myLocationNewOverlay.enableMyLocation();

        myLocationNewOverlay.enableMyLocation();
        myLocationNewOverlay.enableFollowLocation();
        myLocationNewOverlay.setDrawAccuracyEnabled(true);

        mapView.getOverlays().add(myLocationNewOverlay);
    }


    public void moveToMunchen(){
        GeoPoint m = new GeoPoint(48.12014245471684, 11.57697293298894);
        mapView.getController().setCenter(m);
    }

    public void reloadPlaces(){
        places = Application.placeTable.all();
    }

    public void clearAllMarkers(){
        mapView.getOverlays().clear();
    }

//    public Drawable getIconByCategory(String category){
//        if(category.equals(Place.CATEGORY_CLUB)){
//            return
//        }
//    }



    public Drawable getIcon(Place place){
        Drawable cafeBlack = AppCompatResources.getDrawable(context, R.drawable.ic_cafe_black);
        Drawable cafeGold = AppCompatResources.getDrawable(context, R.drawable.ic_cafe_golden);
        Drawable clubBlack = AppCompatResources.getDrawable(context, R.drawable.ic_club_black);
        Drawable clubGold = AppCompatResources.getDrawable(context, R.drawable.ic_club_gold);
        Drawable outdoorBlack = AppCompatResources.getDrawable(context, R.drawable.ic_outdoor_black);
        Drawable outdoorGold = AppCompatResources.getDrawable(context, R.drawable.ic_outdoor_gold);

        String category = place.category;
        if(place.isVisited){
            switch (category){
                case Place.CATEGORY_CAFE:
                    return cafeGold;
                case Place.CATEGORY_CLUB:
                    return clubGold;
                case Place.CATEGORY_OUTDOOR:
                    return outdoorGold;
            }
        }else{
            switch (category){
                case Place.CATEGORY_CAFE:
                    return cafeBlack;
                case Place.CATEGORY_CLUB:
                    return clubBlack;
                case Place.CATEGORY_OUTDOOR:
                    return outdoorBlack;
            }
        }
        return null;
    }



    public void updatePointsOnMap(){



        reloadPlaces();
       // clearAllMarkers();
        for (Place place : places) {
            GeoPoint point = new GeoPoint (place.latitude, place.longitude);
            Marker marker = new Marker(mapView);
            marker.setTitle(place.clubName);
            marker.setIcon(getIcon(place));
            marker.setPosition(point);
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
            mapView.getOverlays().add(marker);
            mapView.getController().setCenter(point); // todo remove from here
        }
    }

}