package com.example.chessapp.ui.placeslist.outdoors;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessapp.Application;
import com.example.chessapp.R;
import com.example.chessapp.databinding.FragmentEmptyRecycleViewBinding;
import com.example.chessapp.storage.model.Place;
import com.example.chessapp.ui.placeslist.PlaceCardAdapter;
import com.example.chessapp.ui.placeslist.PlacesListViewModel;

import java.util.List;
import java.util.stream.Collectors;

public class PlacesListFragment extends Fragment {

    private FragmentEmptyRecycleViewBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        PlacesListViewModel homeViewModel =  new ViewModelProvider(this).get(PlacesListViewModel.class);

        binding = FragmentEmptyRecycleViewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        List<Place> placeList = Application
                .placeTable.all()
                .stream()
                .filter(place -> place.category.equals(Place.CATEGORY_OUTDOOR))
                .collect(Collectors.toList());

        RecyclerView placesList = binding.recycleView;
        PlaceCardAdapter adapter = new PlaceCardAdapter(placeList,  R.id.action_nav_outdoors_to_place_description);
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