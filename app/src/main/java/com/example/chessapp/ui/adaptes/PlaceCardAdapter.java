package com.example.chessapp.ui.adaptes;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessapp.Application;
import com.example.chessapp.R;
import com.example.chessapp.storage.model.Place;

import java.util.List;

// print card with place name and distance from current location
public class PlaceCardAdapter extends RecyclerView.Adapter<PlaceCardAdapter.PlaceCardViewHolder> {

    private List<Pair<Place, Float>>  places;
    private final int onClickNavigationAction;

    public PlaceCardAdapter(List<Pair<Place, Float>> place, int onClickNavigationAction) {
        this.places = place;
        this.onClickNavigationAction = onClickNavigationAction;
    }

    @NonNull
    @Override
    public PlaceCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.place_card, parent, false);

        // Return a new holder instance
        PlaceCardViewHolder viewHolder = new PlaceCardViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceCardViewHolder holder, int position) {
        // Get the data model based on position
        Place contact = places.get(position).first;
        holder.addOnClickEvent(contact.id);

        // Set item views based on your views and data model
        TextView placeName = holder.nameTextView;
        TextView placeDistance = holder.distanceTextView;
        placeName.setText(contact.clubName);

        Float distance = places.get(position).second;

        // if distance is null (gps is disabled) -> hide distance textview
        if(distance == null){
            placeDistance.setText("");
            placeDistance.setVisibility(View.GONE);
            placeDistance.setBackgroundColor(holder.itemView.getResources().getColor(R.color.distance_unknown));
            return;
        }


        String distanceStr = String.valueOf(distance/1000).substring(0, 4);
        String units = "km";
        if(distance < 1000){
            distanceStr = String.valueOf(distance).substring(0, 4);
            units = "m";
        }

        if(distanceStr.endsWith(".")) distanceStr = distanceStr.replace(".", "");
        distanceStr += " " + units;
        placeDistance.setText(distanceStr);

        int color = R.color.distance_far;
        if(distance <= Application.DISTANCE_TO_PLACE_MIDDLE_METERS){
            color = R.color.distance_near;
        }
        placeDistance.setBackgroundColor(holder.itemView.getResources().getColor(color));

    }

    @Override
    public int getItemCount() {
        return places.size();
    }


    /**
     * contains info of place card
     */
    public class PlaceCardViewHolder extends RecyclerView.ViewHolder  {
        public TextView nameTextView;
        public TextView distanceTextView;

        public PlaceCardViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.clubNameTextView);
            distanceTextView = (TextView) itemView.findViewById(R.id.placeDiistanceTextView);
        }


        public void addOnClickEvent(int placeId) {
            itemView.setOnClickListener( (v) -> {
                Bundle bundle = new Bundle();
                bundle.putInt("placeId", placeId);
                Navigation.findNavController(v).navigate(onClickNavigationAction, bundle);
            });
        }

    }
}
