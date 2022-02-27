package com.example.chessapp.ui.placeslist;

import android.os.Bundle;
import android.util.Pair;
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
import com.example.chessapp.helpers.GpsUtils;
import com.example.chessapp.storage.model.Place;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CommonPlacesListFragment extends Fragment {

    private FragmentEmptyRecycleViewBinding binding;

    private String category;
    private int navigationAction;

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
        PlacesListViewModel homeViewModel =  new ViewModelProvider(this).get(PlacesListViewModel.class);

        binding = FragmentEmptyRecycleViewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        GpsUtils utils = new GpsUtils(requireContext());

        List<Place> placeList = Application
                .placeTable.all()
                .stream()
                .filter(place -> place.category.equals(category))
                .collect(Collectors.toList());

        List<Pair<Place, Float>> placeDistances = utils.getDistances(placeList);

        System.out.println(Arrays.toString(placeDistances.toArray()));

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
        category = null;
    }
}