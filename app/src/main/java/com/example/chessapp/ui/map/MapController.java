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
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;

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
    // placeName = marker
    private Map<Integer, Marker> markers = new HashMap<>();
    private boolean isLastPositionWasDefined = false;
    private Location lastCurrentLocation = null;


    @SuppressLint("MissingPermission")
    public MapController(MapFragment mapFragment, MapView mapView, Button button){
        this.mapView = mapView;
        this.context = mapFragment.getContext();
        this.mapFragment = mapFragment;
        this.button = button;


        this.permissionCheck();

        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(12.0);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        addPointsOnMap();
       // moveToCurrentLocation();
        //moveToMunchen();

        drawCurrentLocationIcon();

        LocationManager locationManager = (LocationManager) mapFragment.getActivity().getSystemService(Context.LOCATION_SERVICE);
        try {
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 10, this::updatePlacesStateByCurrentLocation);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 5, this::updatePlacesStateByCurrentLocation);
        }
        catch (Exception ex) {
            //do something useful here
        }
    }


    public void permissionCheck(){
        if (ContextCompat.checkSelfPermission(mapFragment.getActivity(),  Manifest.permission.ACCESS_FINE_LOCATION) == PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions( mapFragment.getActivity(),  new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1 );
        }

        Configuration.getInstance().load(mapFragment.getContext(), PreferenceManager.getDefaultSharedPreferences(mapFragment.getContext()));

    }

    private void moveToLocation(Location location){
        mapView.getController().animateTo(new GeoPoint(location.getLatitude(), location.getLongitude()));
    }

    public void moveToCurrentLocation(){
        Location location = new GpsUtils(context).getLastKnowLocation();
        if(location == null) return;
        isLastPositionWasDefined = true;
        moveToLocation(location);
        lastCurrentLocation = location;
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
        markers.clear();
    }


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


    public Drawable getIconFromResource(Place place, Location currentLocation) {

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

        public Drawable getIconFromResource(Place place){
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

    Drawable getIcon(Place place, Location location){
        final int size = 64;

        Drawable icon = getIconFromResource(place, location);
        if( icon == null ) return null;

        if(icon.getClass() == VectorDrawable.class) return icon;

        Bitmap b = ((BitmapDrawable)icon).getBitmap();

        int height = icon.getIntrinsicHeight();
        int width = icon.getIntrinsicWidth();

        double coef = (double)width / height;

        Bitmap newBitmap =  Bitmap.createScaledBitmap(b, (int) (coef * size), size, false);

        BitmapDrawable newBitmapD =  new BitmapDrawable(newBitmap);
        newBitmapD.setHotspotBounds(-500, -500, 500, 500 );
        newBitmapD.setHotspot(1000, 1000 );
        return newBitmapD;
    }



    public void addPointsOnMap() {
        addPointsOnMap(null);
    }

    public void addPointsOnMap(Location location){
        reloadPlaces();
        for (Place place : places) {
            GeoPoint point = new GeoPoint (place.latitude, place.longitude);
            Marker marker = new Marker(mapView);
            marker.setTitle(place.clubName);
            marker.setIcon(getIcon(place, location));
            marker.setPosition(point);


            marker.setInfoWindow(new MapInfoWindow(R.layout.info_window, mapView, place));

//            marker.showInfoWindow();
            marker.setOnMarkerClickListener((a,b) -> {
                if(marker.isInfoWindowOpen()){
                    marker.closeInfoWindow();
                    return true;
                }
                markers.values().forEach(OverlayWithIW::closeInfoWindow);
                marker.showInfoWindow();
                mapView.getController().animateTo(marker.getPosition());
                return true;
            });
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
            mapView.getOverlays().add(marker);
            markers.put(place.id, marker);
        }

    }

    public void updatePointsStatesOnMap(Location location){
        for (Place place : places) {
            Marker marker = markers.get(place.id);
            marker.setIcon(getIcon(place, location));
        }
    }


    void drawCurrentLocationIcon(){
        MyLocationNewOverlay myLocationNewOverlay
                = new MyLocationNewOverlay(new GpsMyLocationProvider(mapView.getContext()),mapView);
        myLocationNewOverlay.enableMyLocation();

        myLocationNewOverlay.enableMyLocation();
      //  myLocationNewOverlay.enableFollowLocation();
        myLocationNewOverlay.setDrawAccuracyEnabled(true);

        mapView.getOverlays().add(myLocationNewOverlay);
    }

    void onBntClicked(){
        Location currentLocation = new GpsUtils(context).getLastKnowLocation();
        if(currentLocation == null) return;
        for (Place place : places) {
            Location placeLocation = new Location("");
            placeLocation.setLatitude(place.latitude);
            placeLocation.setLongitude(place.longitude);

            if(placeLocation.distanceTo(currentLocation) <= DISTANCE_TO_ADD_TO_VISITED_METES){
                place.isVisited = true;
                Application.placeTable.update(place);
            }
            updatePointsStatesOnMap(currentLocation);
        }
    }

}