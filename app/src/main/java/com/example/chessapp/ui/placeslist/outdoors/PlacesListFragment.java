package com.example.chessapp.ui.placeslist.outdoors;

import com.example.chessapp.R;
import com.example.chessapp.databinding.FragmentEmptyRecycleViewBinding;
import com.example.chessapp.storage.model.Place;
import com.example.chessapp.ui.placeslist.CommonPlacesListFragment;

public class PlacesListFragment extends CommonPlacesListFragment {

    public PlacesListFragment() {
        super(Place.CATEGORY_OUTDOOR, R.id.action_nav_outdoors_to_place_description);
    }

    public PlacesListFragment(int contentLayoutId, FragmentEmptyRecycleViewBinding binding) {
        super(contentLayoutId, Place.CATEGORY_OUTDOOR, R.id.action_nav_outdoors_to_place_description);
    }
}