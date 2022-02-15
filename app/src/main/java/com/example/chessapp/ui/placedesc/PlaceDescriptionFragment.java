package com.example.chessapp.ui.placedesc;

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
import com.example.chessapp.databinding.FragmentPlaceDescBinding;
import com.example.chessapp.storage.model.Place;
import com.example.chessapp.storage.model.Puzzles;
import com.example.chessapp.ui.adaptes.PlaceInfoCardAdapter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class PlaceDescriptionFragment extends Fragment {

    private FragmentPlaceDescBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        PlaceDescriptionModel historyViewModel =  new ViewModelProvider(this).get(PlaceDescriptionModel.class);

        Integer placeId = requireArguments().getInt("placeId");

        binding = FragmentPlaceDescBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        Place place = Objects.requireNonNull(Application.placeTable.get(placeId));

        List<Puzzles> puzzles = Application.puzzlesTable.all().stream()
                .filter(it -> it.clubName != null)
                .filter(it -> it.clubName.equals(place.clubName))
                .collect(Collectors.toList());;


        PlaceInfoCardAdapter placeInfoCardAdapter = new PlaceInfoCardAdapter(place, puzzles);

        RecyclerView recyclerView = binding.placeDescrRecyclesView;
        recyclerView.setAdapter(placeInfoCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}