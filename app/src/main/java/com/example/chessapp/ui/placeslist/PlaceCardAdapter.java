package com.example.chessapp.ui.placeslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessapp.R;
import com.example.chessapp.storage.model.Place;

import java.util.List;

public class PlaceCardAdapter extends RecyclerView.Adapter<PlaceCardAdapter.ViewHolder> {

    private List<Place> places;

    public PlaceCardAdapter(List<Place> place) {
        this.places = place;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.place_card, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        Place contact = places.get(position);

        // Set item views based on your views and data model
        TextView placeName = holder.nameTextView;
        TextView placeDistance = holder.distanceTextView;
        placeName.setText(contact.clubName);
        // todo: add distance in card
        placeDistance.setText("12.3 KM");
    }

    @Override
    public int getItemCount() {
        return places.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView distanceTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.gameNameTextView);
            distanceTextView = (TextView) itemView.findViewById(R.id.placeDiistanceTextView);
        }
    }
}
