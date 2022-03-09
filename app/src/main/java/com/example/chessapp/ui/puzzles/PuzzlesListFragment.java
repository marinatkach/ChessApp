package com.example.chessapp.ui.puzzles;

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
import com.example.chessapp.ui.adaptes.PuzzlesCardsAdapter;

import java.util.List;
import java.util.stream.Collectors;

public class PuzzlesListFragment extends Fragment {

    private FragmentEmptyRecycleViewBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentEmptyRecycleViewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        List<Puzzle> puzzles = Application.puzzlesTable
                .all()
                .stream()
                .filter(it -> it.isPublic)
                .collect(Collectors.toList());

        RecyclerView puzzlesView = binding.recycleView;
        PuzzlesCardsAdapter adapter = new PuzzlesCardsAdapter(puzzles, R.string.puzzles_head_information, false);
        puzzlesView.setLayoutManager(new LinearLayoutManager(getActivity()));
        puzzlesView.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}