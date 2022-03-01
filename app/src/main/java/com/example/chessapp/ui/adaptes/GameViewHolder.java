package com.example.chessapp.ui.adaptes;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chessapp.R;
import com.example.chessapp.helpers.AppHelpers;
import com.example.chessapp.storage.model.Puzzle;

public class GameViewHolder extends RecyclerView.ViewHolder {

    TextView gameNameTextView;
    ImageView gameImage;
    TextView  gameAnswerTextView;
    TextView gamePlayerTextView;
    TextView gameInfoTextView;
    Button gameBntShowAnswer;



    // We also create a constructor that accepts the entire item row
    // and does the view lookups to find each subview
    public GameViewHolder(View itemView) {
        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
        super(itemView);

        this.gameNameTextView = (TextView) itemView.findViewById(R.id.gameNameTextView);
        this.gameImage = (ImageView) itemView.findViewById(R.id.gameImage);
        this.gameAnswerTextView = (TextView) itemView.findViewById(R.id.gameAnswerTextView);
        this.gameBntShowAnswer = (Button) itemView.findViewById(R.id.gameBntShowAnswer);
        this.gamePlayerTextView = (TextView) itemView.findViewById(R.id.gamePlayerNameTextView);
        this.gameInfoTextView = (TextView) itemView.findViewById(R.id.gameInfoTextView);

    }

    public static void onBindGameViewHolder(GameViewHolder holder, Puzzle puzzle, int position){

        // Set item views based on your views and data model
        TextView gameNameTextView = holder.gameNameTextView;
        ImageView gameImage = holder.gameImage;
        TextView  gameAnswerTextView = holder.gameAnswerTextView;
        TextView  gamePlayerTextView = holder.gamePlayerTextView;
        TextView  gameInfoTextView = holder.gameInfoTextView;
        Button gameBntShowAnswer = holder.gameBntShowAnswer;

        gameNameTextView.setText("Game " + position);
        gamePlayerTextView.setText(puzzle.playerText);
        gameInfoTextView.setText(puzzle.gameInfo);
        AppHelpers.setImageOrDefault(gameImage, puzzle.image);

        gameAnswerTextView.setText(AppHelpers.hideString(puzzle.solution));

        gameBntShowAnswer.setOnClickListener((view) -> {
            if(gameAnswerTextView.getText().toString().startsWith("***")){
                gameAnswerTextView.setText(puzzle.solution);
                gameBntShowAnswer.setText(R.string.bnt_hide_solution);
            }else {
                gameAnswerTextView.setText(AppHelpers.hideString(puzzle.solution));
                gameBntShowAnswer.setText(R.string.bnt_open_solution);
            }
        });
    }
}