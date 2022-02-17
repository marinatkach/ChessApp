package com.example.chessapp.ui.admin;

import com.example.chessapp.Application;
import com.example.chessapp.storage.model.Place;
import com.example.chessapp.storage.model.UserState;

import java.util.List;
import java.util.stream.Collectors;

public class AdminViewController  {


    private UserState userState = Application.getCurrentUserState();

    private void setVisited(List<Place> places){
        for (Place place : places) {
            place.isVisited = true;
            Application.placeTable.update(place);
        }

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
    }

    public void setVisitedByRadius( int meters){
        // todo setVisitedByRadius
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