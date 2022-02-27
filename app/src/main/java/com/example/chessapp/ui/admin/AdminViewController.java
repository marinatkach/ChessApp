package com.example.chessapp.ui.admin;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

import android.Manifest;
import android.location.Location;
import android.preference.PreferenceManager;
import android.util.Pair;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.chessapp.Application;
import com.example.chessapp.R;
import com.example.chessapp.helpers.GpsUtils;
import com.example.chessapp.storage.model.Place;
import com.example.chessapp.storage.model.UserState;

import org.osmdroid.config.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class AdminViewController  {


    private UserState userState = Application.getCurrentUserState();
    private AdminFragment fragment;
    private GpsUtils gpsUtils;


    public AdminViewController(AdminFragment fragment) {
        this.fragment = fragment;
        gpsUtils = new GpsUtils(fragment.getContext());

        if (ContextCompat.checkSelfPermission(fragment.getActivity(),  Manifest.permission.ACCESS_FINE_LOCATION) == PERMISSION_GRANTED) {

        } else {
          //  ActivityCompat.requestPermissions( fragment.getActivity(),  new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1 );
        }

        Configuration.getInstance().load(fragment.getContext(), PreferenceManager.getDefaultSharedPreferences(fragment.getContext()));


    }

    private void setVisited(List<Place> places){
        for (Place place : places) {
            place.isVisited = true;
            Application.placeTable.update(place);
        }
        printSuccess();
    }

    public void setVisitedClubs(){
        setVisited(Application.placeTable.getPlacesByCategory(Place.CATEGORY_CLUB));
    }
    public void setVisitedOutdoors(){
        setVisited(Application.placeTable.getPlacesByCategory(Place.CATEGORY_OUTDOOR));
    }
    public void setVisitedCafes(){
        setVisited(Application.placeTable.getPlacesByCategory(Place.CATEGORY_CAFE));
    }

    public void setAllPlacesAsUnvisited(){
        for (Place place : Application.placeTable.all()) {
            place.isVisited = false;
            Application.placeTable.update(place);
        }
        printSuccess();
    }


    public void setVisitedByRadius(String inputText){

        String text = inputText.trim();
        boolean isKm = true;

        if(text.equals("")){
            printMessageToast("Error: Empty input!");
            return;
        }


        if(text.contains("km")){
            text = text.replace("km", "").trim();
        }
        else if(text.contains("m")){
            text = text.replace("m", "").trim();
            isKm = false;
        }

        text = text.replace(",", ".");

        Double radius = null;
        try {
            radius = Double.parseDouble(text);
        }catch (NumberFormatException e){
            printMessageToast("Error: Incorrect input!");
            return;
        }

        int meters = -1;

        if(isKm){
            meters = (int) (radius*1000);
        }else {
            meters = (int) (double) radius;
        }


        Location location = gpsUtils.getLastKnowLocation();
        if(location == null) {
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(fragment.getContext(), R.string.admin_cannot_get_location, duration);
            toast.show();
            return;
        }
        for (Pair<Place, Float> placeFloatPair : gpsUtils.getDistances(Application.placeTable.all())) {
            Place place = placeFloatPair.first;
            Float distance = placeFloatPair.second;
            if(distance < meters){
                place.isVisited = true;
                Application.placeTable.update(place);
            }
        }
        printSuccess();
    }

    private void printMessageToast(String text){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(fragment.getContext(), text, duration);
        toast.show();
    }

    private void printSuccess(){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(fragment.getContext(), R.string.admin_success, duration);
        toast.show();
    }

    public boolean hasAdminAccess() {
        return Application.getCurrentUserState().hasAccess;
    }



    public void logout(){
        userState.hasAccess = false;
        Application.stateTable.update(userState);
    }

    public boolean getAdminAccess(String password){
        if(Application.getCurrentUserState().accessPassword.equals(password)){
            userState.hasAccess = true;
            Application.stateTable.update(userState);
            return true;
        }
        return false;
    }

}