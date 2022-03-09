package com.example.chessapp.ui.placeslist;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessapp.Application;
import com.example.chessapp.databinding.FragmentEmptyRecycleViewBinding;
import com.example.chessapp.helpers.GpsUtils;
import com.example.chessapp.storage.model.Place;
import com.example.chessapp.ui.adaptes.PlaceCardAdapter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Common fragment to draw a list of places (clubs, cafes, outdoors)
 */
public class CommonPlacesListFragment extends Fragment {

    private FragmentEmptyRecycleViewBinding binding;

    private final String category;
    private final int navigationAction;

    /**
     *
     * @param category type of place to draw
     * @param navigationAction nav action if place was clicked
     */
    public CommonPlacesListFragment(String category, int navigationAction) {
        this.category = category;
        this.navigationAction = navigationAction;
    }

    public CommonPlacesListFragment(int contentLayoutId, String category, int navigationAction) {
        super(contentLayoutId);
        this.category = category;
        this.navigationAction = navigationAction;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentEmptyRecycleViewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        GpsUtils utils = new GpsUtils(requireContext());

        List<Place> placeList = Application
                .placeTable.all()
                .stream()
                .filter(place -> place.category.equals(category))
                .collect(Collectors.toList());

        // get distances to all places from places List
        List<Pair<Place, Float>> placeDistances = utils.getDistances(placeList);

        // if gps enabled -> sort places by distance
        if(!placeDistances.isEmpty() && placeDistances.get(0).second != null){
            placeDistances = placeDistances.stream()
                    .sorted((a,b) -> Float.compare(a.second, b.second))
                    .collect(Collectors.toList());
        }

        RecyclerView placesList = binding.recycleView;
        PlaceCardAdapter adapter = new PlaceCardAdapter(placeDistances, navigationAction);
        placesList.setAdapter(adapter);
        placesList.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}