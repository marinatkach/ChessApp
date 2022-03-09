package com.example.chessapp.ui.map;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.chessapp.Application;
import com.example.chessapp.R;
import com.example.chessapp.helpers.GpsUtils;
import com.example.chessapp.storage.model.Place;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayWithIW;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapController {

    private List<Place> places = Application.placeTable.all();

    private final MapFragment mapFragment;
    private final MapView mapView;
    private final Context context;
    private final int DISTANCE_METERS = Application.DISTANCE_METERS;
    private final int DISTANCE_TO_ADD_TO_VISITED_METES = Application.DISTANCE_TO_ADD_TO_VISITED_METES;
    private final Button button;
    private Map<Integer, Marker> markers = new HashMap<>();
    private boolean isLastPositionWasDefined = false;
    private Location lastCurrentLocation = null;


    /**
     * init map
     * @param mapFragment
     * @param mapView
     * @param button
     */
    @SuppressLint("MissingPermission")
    public MapController(MapFragment mapFragment, MapView mapView, Button button){
        this.mapView = mapView;
        this.context = mapFragment.getContext();
        this.mapFragment = mapFragment;
        this.button = button;


        this.permissionCheck(); // check permission

        mapView.setBuiltInZoomControls(true); // set zoom in buttons
        mapView.setMultiTouchControls(true); // set multitouchControls for zooming
        mapView.getController().setZoom(12.0); // set init zoom
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        addPointsOnMap(); // add points on map
        drawCurrentLocationIcon(); // draw icons to map

        LocationManager locationManager = (LocationManager) mapFragment.getActivity().getSystemService(Context.LOCATION_SERVICE);
        try {

            // set listener for updating current location location
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 50, new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                   updatePlacesStateByCurrentLocation(location); // update icons and markers state by location
                }

                @Override
                public void onProviderEnabled(@NonNull String provider) {

                }

                @Override
                public void onProviderDisabled(@NonNull String provider) {

                }

                @Override
                public void onLocationChanged(@NonNull List<Location> locations) {
                }

                @Override
                public void onFlushComplete(int requestCode) {
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }
            });
        }
        catch (Exception ex) {
            //do something useful here
        }
    }


    public void permissionCheck(){
        if (ContextCompat.checkSelfPermission(mapFragment.getActivity(),  Manifest.permission.ACCESS_FINE_LOCATION) == PERMISSION_GRANTED) {

        } else {
        }

        Configuration.getInstance().load(mapFragment.getContext(), PreferenceManager.getDefaultSharedPreferences(mapFragment.getContext()));

    }

    /**
     * move camera to location
     * @param location
     */
    private void moveToLocation(Location location){
        mapView.getController().animateTo(new GeoPoint(location.getLatitude(), location.getLongitude()));
    }

    /**
     * move camera to current location
     */
    public void moveToCurrentLocation(){
        Location location = new GpsUtils(context).getLastKnowLocation();
        if(location == null) return;
        isLastPositionWasDefined = true;
        moveToLocation(location);
        lastCurrentLocation = location;
    }


    // move canera to munchen
    public void moveToMunchen(){
        GeoPoint m = new GeoPoint(48.12014245471684, 11.57697293298894);
        mapView.getController().setCenter(m);
    }

    public void reloadPlaces(){
        places = Application.placeTable.all();
    }

    public void clearAllMarkers(){
        mapView.getOverlays().clear();
        markers.clear();
    }


    /**
     *  enable btn if near is a place
     * @param currentLocation
     */
    public void updateBtnState(Location currentLocation){
        if(currentLocation == null) {
            button.setEnabled(false);
            return;
        }

        for (Place place : places) {
            Location placeLocation = new Location("");
            placeLocation.setLatitude(place.latitude);
            placeLocation.setLongitude(place.longitude);

            if(placeLocation.distanceTo(currentLocation) <= DISTANCE_TO_ADD_TO_VISITED_METES){
               button.setEnabled(true);
               return;
            }
        }
        button.setEnabled(false);
    }

    /**
     * updated states by location changing
     * @param currentLocation
     */
    public void updatePlacesStateByCurrentLocation(Location currentLocation){
        Log.i("Map", "updatePlacesStateByCurrentLocation(" + currentLocation.toString() + ")");
        lastCurrentLocation = currentLocation;

        if(!isLastPositionWasDefined){
            isLastPositionWasDefined = true;
            moveToLocation(currentLocation);
        }

        updatePointsStatesOnMap(currentLocation);
        updateBtnState(currentLocation);
    }


    /**
     * return Drawable for place by current location
     * @param place
     * @param currentLocation
     * @return
     */
    private Drawable getIconFromResource(Place place, Location currentLocation) {

        if(currentLocation == null || place.isVisited) return getIconFromResource(place);


        Drawable cafe = AppCompatResources.getDrawable(context, R.drawable.ic_cafe_near);
        Drawable club = AppCompatResources.getDrawable(context, R.drawable.ic_club_near);
        Drawable outdoor = AppCompatResources.getDrawable(context, R.drawable.ic_outdoor_near);

        Location placeLocation = new Location("");
        placeLocation.setLatitude(place.latitude);
        placeLocation.setLongitude(place.longitude);

        if(placeLocation.distanceTo(currentLocation) <= DISTANCE_METERS)
        {
            String category = place.category;
                switch (category) {
                    case Place.CATEGORY_CAFE:
                        return cafe;
                    case Place.CATEGORY_CLUB:
                        return club;
                    case Place.CATEGORY_OUTDOOR:
                        return outdoor;
                }
        }

        return getIconFromResource(place);
    }

    /**
     * return Drawable for place
     * @param place
     * @return
     */
    private Drawable getIconFromResource(Place place){
        Drawable cafeBlack = AppCompatResources.getDrawable(context, R.drawable.ic_cafe_black);
        Drawable cafeGold = AppCompatResources.getDrawable(context, R.drawable.ic_cafe_selected);
        Drawable clubBlack = AppCompatResources.getDrawable(context, R.drawable.ic_club_black);
        Drawable clubGold = AppCompatResources.getDrawable(context, R.drawable.ic_club_selected);
        Drawable outdoorBlack = AppCompatResources.getDrawable(context, R.drawable.ic_outdoor_black);
        Drawable outdoorGold = AppCompatResources.getDrawable(context, R.drawable.ic_outdoor_selected);

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

   private Drawable getIcon(Place place, Location location){
       return getIconFromResource(place, location);
    }



    public void addPointsOnMap() {
        addPointsOnMap(null);
    }


    /**
     * Add points markers on map =)
     * @param location
     */
    public void addPointsOnMap(Location location){
        reloadPlaces();
        for (Place place : places) {
            GeoPoint point = new GeoPoint (place.latitude, place.longitude);
            Marker marker = new Marker(mapView);
            marker.setTitle(place.clubName);
            marker.setIcon(getIcon(place, location));
            marker.setPosition(point);


            marker.setInfoWindow(new MapInfoWindow(R.layout.info_window, mapView, place));


            // set event if marker was clicked
            marker.setOnMarkerClickListener((a,b) -> {
                if(marker.isInfoWindowOpen()){ // if has been opened -> close
                    marker.closeInfoWindow();
                    return true;
                }
                markers.values().forEach(OverlayWithIW::closeInfoWindow);
                marker.showInfoWindow();
                mapView.getController().animateTo(marker.getPosition()); // move to clicked marker
                return true;
            });
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
            mapView.getOverlays().add(marker); // add marker to map
            markers.put(place.id, marker);  // add created marker to a list
        }

    }

    /**
     * update markes by current location
     * @param location
     */
    public void updatePointsStatesOnMap(Location location){
        for (Place place : places) {
            Marker marker = markers.get(place.id);
            marker.setIcon(getIcon(place, location));
        }
    }


    /**
     * Draw Arrow on map
     */
    void drawCurrentLocationIcon(){
        MyLocationNewOverlay myLocationNewOverlay
                = new MyLocationNewOverlay(new GpsMyLocationProvider(mapView.getContext()),mapView);
        myLocationNewOverlay.enableMyLocation();
        myLocationNewOverlay.enableMyLocation();
        myLocationNewOverlay.setDrawAccuracyEnabled(true);
        mapView.getOverlays().add(myLocationNewOverlay);
    }

    /**
     * Add to visited button event
     */
    void onBntClicked(){
        Location currentLocation = new GpsUtils(context).getLastKnowLocation();
        if(currentLocation == null) return;
        for (Place place : places) {
            Location placeLocation = new Location("");
            placeLocation.setLatitude(place.latitude);
            placeLocation.setLongitude(place.longitude);

            if(placeLocation.distanceTo(currentLocation) <= DISTANCE_TO_ADD_TO_VISITED_METES){
                place.isVisited = true;
                Application.placeTable.update(place); // change state and save(update) in db
            }
            updatePointsStatesOnMap(currentLocation);
        }
    }

}