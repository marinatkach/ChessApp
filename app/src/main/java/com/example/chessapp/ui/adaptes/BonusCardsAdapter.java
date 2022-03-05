package com.example.chessapp.ui.adaptes;

import android.view.Gravity;
import android.view.View;

import com.example.chessapp.Application;
import com.example.chessapp.R;
import com.example.chessapp.helpers.AppHelpers;
import com.example.chessapp.storage.model.Puzzle;

import java.util.List;
import java.util.stream.Collectors;

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
//        holder.gameAnswerImage.setVisibility(View.VISIBLE);

        List <String> puzzlesName = Application.puzzlesTable.all().stream().filter(it -> !it.isPublic).map(it -> it.solution  +"|"+ it.clubName).collect(Collectors.toList());

        int index = puzzlesName.indexOf(puzzle.solution + "|" + puzzle.clubName) + 1;

        String imageName = "bonusend_";
        if(index < 10) {
            imageName += "0";
        }
        imageName += index + ".jpg";

        AppHelpers.setImageOrDefault(holder.gameAnswerImage, imageName);

        holder.gameBntShowAnswer.setOnClickListener( e -> {
            if(holder.gameAnswerTextView.getText().toString().startsWith("***")){
                holder.gameAnswerTextView.setText(puzzle.solution);
                holder.gameBntShowAnswer.setText(R.string.bnt_hide_solution);
                holder.gameAnswerImage.setVisibility(View.VISIBLE);
            }else {
                holder.gameAnswerTextView.setText(AppHelpers.hideString("0000000000"));
                holder.gameBntShowAnswer.setText(R.string.bnt_open_solution);
                holder.gameAnswerImage.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected void onBindPuzzlesInfoViewHolder(InfoViewHolder holder, int position) {
        super.onBindPuzzlesInfoViewHolder(holder, position);
        holder.infoNameTextView.setGravity(Gravity.CENTER);
    }
}
