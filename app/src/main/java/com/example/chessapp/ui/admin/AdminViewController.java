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

        // checking permission
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

    /**
     * set all clubs as visited
     */
    public void setVisitedClubs(){
        setVisited(Application.placeTable.getPlacesByCategory(Place.CATEGORY_CLUB));
    }

    /**
     * set all outdoors as visited
     */
    public void setVisitedOutdoors(){
        setVisited(Application.placeTable.getPlacesByCategory(Place.CATEGORY_OUTDOOR));
    }

    /**
     * set all cafes as visited
     */
    public void setVisitedCafes(){
        setVisited(Application.placeTable.getPlacesByCategory(Place.CATEGORY_CAFE));
    }

    /**
     * set all places as unvisited
     */
    public void setAllPlacesAsUnvisited(){
        for (Place place : Application.placeTable.all()) {
            place.isVisited = false;
            Application.placeTable.update(place);
        }
        printSuccess();
    }


    /**
     * set all place as visited by radius
     */
    public void setVisitedByRadius(String inputText){

        String text = inputText.trim();
        boolean isKm = true; // if false -> input in meters else km

        // if empty input -> error
        if(text.equals("")){
            printMessageToast("Please Enter Radius!");
            return;
        }


        // if input contains km -> replace km
        if(text.contains("km")){
            text = text.replace("km", "").trim();
        }
        else if(text.contains("m")){
            text = text.replace("m", "").trim();
            isKm = false;
        }

        // 1,235 cannot be casted to number, only 1.234
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


        // get location and set distance
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
                Application.placeTable.update(place); // update place state in DB
            }
        }
        printSuccess(); // print success message
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


    /**
     * logout
     */
    public void logout(){
        userState.hasAccess = false;
        Application.stateTable.update(userState);
    }

    /**
     * get admin access
     * @param password
     * @return
     */
    public boolean getAdminAccess(String password){
        if(Application.getCurrentUserState().accessPassword.equals(password)){
            userState.hasAccess = true;
            Application.stateTable.update(userState);
            return true;
        }
        return false;
    }

}