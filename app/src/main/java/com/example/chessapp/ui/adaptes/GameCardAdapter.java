package com.example.chessapp.ui.adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessapp.R;
import com.example.chessapp.helpers.AppHelpers;
import com.example.chessapp.storage.model.Place;
import com.example.chessapp.storage.model.Puzzles;

import java.util.List;

public class GameCardAdapter extends RecyclerView.Adapter<GameCardAdapter.ViewHolder> {

    private List<Puzzles> puzzles;

    public GameCardAdapter(List<Puzzles> puzzles) {
        this.puzzles = puzzles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.game_card, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position

        int fixPosition = position-1;
        Puzzles puzzle = this.puzzles.get(fixPosition);

        // Set item views based on your views and data model
        TextView gameNameTextView = holder.gameNameTextView;
        ImageView gameImage = holder.gameImage;
        TextView  gameAnswerTextView = holder.gameAnswerTextView;
        Button gameBntShowAnswer = holder.gameBntShowAnswer;

        gameNameTextView.setText(puzzle.name);

        AppHelpers.setImageOrDefault(gameImage, puzzle.image);

        gameAnswerTextView.setText(AppHelpers.hideString(puzzle.solution));

        gameBntShowAnswer.setOnClickListener((view) -> gameAnswerTextView.setText(puzzle.solution));
    }


    @Override
    public int getItemCount() {
        return puzzles.size() + 1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView gameNameTextView;
        ImageView gameImage;
        TextView  gameAnswerTextView;
        Button gameBntShowAnswer;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            this.gameNameTextView = (TextView) itemView.findViewById(R.id.gameNameTextView);
            this.gameImage = (ImageView) itemView.findViewById(R.id.gameImage);
            this.gameAnswerTextView = (TextView) itemView.findViewById(R.id.gameAnswerTextView);
            this.gameBntShowAnswer = (Button) itemView.findViewById(R.id.gameBntShowAnswer);

        }
    }
}
