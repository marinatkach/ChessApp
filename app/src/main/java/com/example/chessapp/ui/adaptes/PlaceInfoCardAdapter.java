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

public class PlaceInfoCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Place place;
    private List<Puzzles> puzzles;

    public PlaceInfoCardAdapter(Place place,  List<Puzzles> puzzles) {
        this.place = place;
        this.puzzles = puzzles;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) return 0;
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        if(viewType == 0) // place info
        {
            View view = inflater.inflate(R.layout.place_info_card, parent, false);
            return new PlaceViewHolder(view);
        }
        else {
            View view = inflater.inflate(R.layout.game_card, parent, false);
            return new GameViewHolder(view);
        }

    }


    private void onBindGameViewHolder(GameViewHolder holder, int position){
        int fixPosition = position - 1 ;
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

    private void onBindPlaceInfoViewHolder(PlaceViewHolder holder, int position){
        // Get the data model based on position
        // Set item views based on your views and data model

        TextView clubNameDescriptionTextView = holder.clubNameDescriptionTextView;
        TextView cardDescriptionLink = holder.cardDescriptionLink;
        TextView placeInfoText = holder.placeInfoText;
        ImageView clubImage = holder.clubImage;


        clubNameDescriptionTextView.setText(place.clubName);
        cardDescriptionLink.setText(place.link);
        placeInfoText.setText(place.info);

        // todo : if puzzles count == 0 -> remove (hide) puzzles textview

        AppHelpers.setImageOrDefault(clubImage, place.logo);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder.getItemViewType() == 0 )
        {
            onBindPlaceInfoViewHolder((PlaceViewHolder) holder, position);
        }
        else{
            onBindGameViewHolder((GameViewHolder) holder, position);
        }

    }

    @Override
    public int getItemCount() {
        return puzzles.size() + 1; // puzzles + info
    }


    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView clubNameDescriptionTextView;
        public TextView cardDescriptionLink;
        public TextView placeInfoText;
        public ImageView clubImage;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public PlaceViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

           clubNameDescriptionTextView = (TextView) itemView.findViewById(R.id.clubNameDescriptionTextView);
           cardDescriptionLink = (TextView) itemView.findViewById(R.id.cardDescrLinkTextView);
           placeInfoText = (TextView) itemView.findViewById(R.id.placeInfoText);
           clubImage = (ImageView) itemView.findViewById(R.id.clubImage);

        }
    }

    public class GameViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView gameNameTextView;
        ImageView gameImage;
        TextView  gameAnswerTextView;
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

        }
    }
}
