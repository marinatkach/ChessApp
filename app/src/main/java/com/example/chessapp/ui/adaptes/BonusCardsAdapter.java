package com.example.chessapp.ui.adaptes;

import android.view.View;

import com.example.chessapp.storage.model.Puzzle;

import java.util.List;

public class BonusCardsAdapter extends PuzzlesCardsAdapter {

    public BonusCardsAdapter(List<Puzzle> puzzles, int infoTextId, boolean isAlLPlacesFound) {
        super(puzzles, infoTextId, isAlLPlacesFound);
    }

    @Override
    protected void onBindGameViewHolder(GameViewHolder holder, int position) {
        super.onBindGameViewHolder(holder, position);
        Puzzle puzzle = this.puzzles.get(position - 1);
        holder.gamePlayerTextView.setVisibility(View.VISIBLE);
        holder.gamePlayerTextView.setText(puzzle.name);
    }
}
