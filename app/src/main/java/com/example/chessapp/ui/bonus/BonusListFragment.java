package com.example.chessapp.ui.bonus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessapp.Application;
import com.example.chessapp.R;
import com.example.chessapp.databinding.FragmentEmptyRecycleViewBinding;
import com.example.chessapp.storage.model.Puzzle;
import com.example.chessapp.ui.adaptes.BonusCardsAdapter;
import com.example.chessapp.ui.adaptes.PuzzlesCardsAdapter;

import java.util.List;
import java.util.stream.Collectors;

public class BonusListFragment extends Fragment {

    private FragmentEmptyRecycleViewBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        FragmentPuzzlesBinding homeViewModel =  new ViewModelProvider(this).get(PlacesListViewModel.class);

        binding = FragmentEmptyRecycleViewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        List<String> visitedPlaces = Application.placeTable.getPlacesByVisitedState(true)
                .stream().map(it-> it.clubName).collect(Collectors.toList());

        List<Puzzle> puzzles = Application.puzzlesTable
                .all()
                .stream()
                .filter(it -> !it.isPublic)
                .filter(it -> visitedPlaces.contains(it.clubName))
                .collect(Collectors.toList());

        boolean isAllPlacesFound = Application.placeTable.all().stream().allMatch(it -> it.isVisited);

        RecyclerView puzzlesView = binding.recycleView;
        BonusCardsAdapter adapter = new BonusCardsAdapter(puzzles, R.string.bonus_head_information, isAllPlacesFound);
        puzzlesView.setAdapter(adapter);
        puzzlesView.setLayoutManager(new LinearLayoutManager(getContext()));
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}