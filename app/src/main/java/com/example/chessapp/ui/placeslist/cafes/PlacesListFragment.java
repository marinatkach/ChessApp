package com.example.chessapp.ui.placeslist.cafes;

import com.example.chessapp.R;
import com.example.chessapp.storage.model.Place;
import com.example.chessapp.ui.placeslist.CommonPlacesListFragment;

public class PlacesListFragment extends CommonPlacesListFragment {

    public PlacesListFragment() {
        super(Place.CATEGORY_CAFE, R.id.action_nav_cafes_to_place_description);
    }

    public PlacesListFragment(int contentLayoutId) {
        super(contentLayoutId, Place.CATEGORY_CAFE, R.id.action_nav_cafes_to_place_description);
    }
}