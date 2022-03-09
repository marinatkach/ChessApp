package com.example.chessapp.ui.adaptes;

import android.view.Gravity;
import android.view.View;

import com.example.chessapp.Application;
import com.example.chessapp.R;
import com.example.chessapp.helpers.AppHelpers;
import com.example.chessapp.storage.model.Puzzle;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Adapter for bonus cards
 */
public class BonusCardsAdapter extends PuzzlesCardsAdapter {


    boolean isAllPlaceFound = false;

    /**
     * constructor
     * @param puzzles
     * @param infoTextId text of first card
     * @param isAlLPlacesFound if true -> show the last "bonus" card
     */
    public BonusCardsAdapter(List<Puzzle> puzzles, int infoTextId, boolean isAlLPlacesFound) {
        super(puzzles, infoTextId, isAlLPlacesFound);
        this.isAllPlaceFound = isAlLPlacesFound;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) return 0;
        if(isAllPlaceFound && position == puzzles.size() + 1) return 0;
        return 1;
    }

    @Override
    protected void onBindGameViewHolder(GameViewHolder holder, int position) {
        super.onBindGameViewHolder(holder, position);
        Puzzle puzzle = this.puzzles.get(position - 1);
        holder.gamePlayerTextView.setVisibility(View.VISIBLE);
        holder.gamePlayerTextView.setText(puzzle.name);

        // -------------------------------------------------------------
        // below logic to find and set solution image for bonus etude
        // -------------------------------------------------------------

        // all images has the follow format bonusend_<number_of_etude>.jpeg
        // to try to find number of etude

        // make a key from bonus puzzles only
        List <String> puzzlesName = Application.puzzlesTable.all()
                .stream()
                .filter(it -> !it.isPublic)
                .map(it -> it.solution  +"|"+ it.clubName) // key = solution + club name
                .collect(Collectors.toList());

        // index + 1 is number of bonus puzzle
        int index = puzzlesName.indexOf(puzzle.solution + "|" + puzzle.clubName) + 1;

        // create a name of image
        String imageName = "bonusend_";
        if(index < 10) {
            imageName += "0";
        }
        imageName += index + ".jpg";

        // set solution image
        AppHelpers.setImageOrDefault(holder.gameAnswerImage, imageName);

        // show bonus if it was already opened by user
        if(super.isOpen.get(position -1)){
            holder.gameAnswerImage.setVisibility(View.VISIBLE);
        }else {
            holder.gameAnswerImage.setVisibility(View.GONE);
        }


        // set open/close solution event!
        holder.gameBntShowAnswer.setOnClickListener( e -> {
            if(holder.gameAnswerTextView.getText().toString().startsWith("***")){
                holder.gameAnswerTextView.setText(puzzle.solution);
                holder.gameBntShowAnswer.setText(R.string.bnt_hide_solution);
                holder.gameAnswerImage.setVisibility(View.VISIBLE);
                super.isOpen.set(position -1, true);
            }else {
                holder.gameAnswerTextView.setText(AppHelpers.hideString("0000000000"));
                holder.gameBntShowAnswer.setText(R.string.bnt_open_solution);
                holder.gameAnswerImage.setVisibility(View.GONE);
                super.isOpen.set(position -1, true);
            }
        });

    }

    @Override
    protected void onBindPuzzlesInfoViewHolder(InfoViewHolder holder, int position) {
        super.onBindPuzzlesInfoViewHolder(holder, position);
        holder.infoNameTextView.setGravity(Gravity.CENTER);
    }
}
