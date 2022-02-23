package com.example.chessapp.ui.adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessapp.R;
import com.example.chessapp.storage.model.Puzzle;

import java.util.List;

public class BonusCardsAdapter extends PuzzlesCardsAdapter {

    public BonusCardsAdapter(List<Puzzle> puzzles, int infoTextId, boolean isAlLPlacesFound) {
        super(puzzles, infoTextId, isAlLPlacesFound);
    }

    @Override
    protected void onBindGameViewHolder(GameViewHolder holder, int position) {
        super.onBindGameViewHolder(holder, position);
        holder.gamePlayerTextView.setVisibility(View.GONE);
    }
}
