package com.example.chessapp.ui.adaptes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessapp.R;
import com.example.chessapp.helpers.AppHelpers;
import com.example.chessapp.storage.model.Place;

public class PlaceInfoCardAdapter extends RecyclerView.Adapter<PlaceInfoCardAdapter.ViewHolder> {

    private Place place;

    public PlaceInfoCardAdapter(Place place) {
        this.place = place;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.place_info_card, parent, false);

        // Return a new holder instance
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        // Set item views based on your views and data model

        TextView gameNameTextView = holder.gameNameTextView;
        TextView cardDescriptionLink = holder.cardDescriptionLink;
        TextView placeInfoText = holder.placeInfoText;
        ImageView gameImage = holder.gameImage;


        gameNameTextView.setText(place.clubName);
        cardDescriptionLink.setText(place.link);
        placeInfoText.setText(place.info);

        AppHelpers.setImageOrDefault(gameImage, place.logo);

    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView gameNameTextView;
        public TextView cardDescriptionLink;
        public TextView placeInfoText;
        public ImageView gameImage;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

           gameNameTextView = (TextView) itemView.findViewById(R.id.gameNameTextView);
           cardDescriptionLink = (TextView) itemView.findViewById(R.id.cardDescrLinkTextView);
           placeInfoText = (TextView) itemView.findViewById(R.id.placeInfoText);
           gameImage = (ImageView) itemView.findViewById(R.id.gameImage);

        }
    }
}
