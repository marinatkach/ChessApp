package com.example.chessapp.ui.adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessapp.R;
import com.example.chessapp.helpers.AppHelpers;
import com.example.chessapp.storage.model.Place;
import com.example.chessapp.storage.model.Puzzle;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

public class PlaceInfoCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Place place;
    private List<Puzzle> puzzles;

    public PlaceInfoCardAdapter(Place place,  List<Puzzle> puzzles) {
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
        Puzzle puzzle = this.puzzles.get(fixPosition);
        GameViewHolder.onBindGameViewHolder(holder, puzzle, position);
    }

    private void onBindPlaceInfoViewHolder(PlaceViewHolder holder, int position){
        // Get the data model based on position
        // Set item views based on your views and data model

        TextView clubNameDescriptionTextView = holder.clubNameDescriptionTextView;
        TextView cardDescriptionLink = holder.cardDescriptionLink;
        TextView placeInfoText = holder.placeInfoText;
        TextView placeAdressText = holder.placeAdressText;
        TextView placeMembersText = holder.placeMembersText;
        TextView membersDescriptionInfo = holder.membersDescriptionInfo;

        ImageView clubImage = holder.clubImage;


        clubNameDescriptionTextView.setText(place.clubName);
        cardDescriptionLink.setText(place.link);

        if(place.category.equals(Place.CATEGORY_CLUB) && !place.labels.equals("")){
            membersDescriptionInfo.setText(place.labels.replace("\n", ", "));
        }else{
            membersDescriptionInfo.setVisibility(View.GONE);
        }

        if(place.link == null || place.link.equals("")){
            cardDescriptionLink.setVisibility(View.GONE);
        }

        if(place.info == null || place.info.equals("")){
            holder.table.setVisibility(View.GONE);
            holder.place5StrText.setVisibility(View.GONE);
        }
        else {
            holder.table.setVisibility(View.VISIBLE);
            holder.place5StrText.setVisibility(View.VISIBLE);
            fillTable(holder, place.info);
        }
        placeAdressText.setText(place.address);


        if(place.membersCnt == null || place.membersCnt <= 0){
            placeMembersText.setVisibility(View.GONE);
        }else {
            placeMembersText.setVisibility(View.VISIBLE); // to be sure =)
            placeMembersText.setText(String.format("%s members", place.membersCnt));
        }



        AppHelpers.setImageOrDefault(clubImage, place.logo);
    }


    /*
        1. Firouzja, Alireza 	  	2804 	GM
        2. Eljanov, Pavel 	  	2683 	GM
        3. Saric, Ivan 	  		2678 	GM
        4. Jones, Gawain 	  	2671 	GM
        5. Indjic, Aleksandar 	  	2604 	GM
     */
    private String fillTable(PlaceViewHolder holder, String sourceInfo){

        Integer minSpaceCnt = 5;
        Integer maxLen = 22;

        String[] lines = Arrays.stream(sourceInfo.split("\n"))
                .map(String::trim)
                .toArray(String[]::new);

        StringBuilder sb = new StringBuilder();

        int i = 1;
        for (String line : lines) {

            String[] words = Arrays.stream(line.split(";"))
                    .map(String::trim)
                    .toArray(String[]::new);

            String names = i + ". " + words[0].replace(",", ", "); // Firouzja, Alireza
            String points = words[1]; // 2804

            String grad = "";
            if(words.length == 3 ) {
               grad = words[2]; // GM
            }

            holder.getTextViewByWord(1)[i-1].setText(names);
            holder.getTextViewByWord(2)[i-1].setText(points);
            holder.getTextViewByWord(3)[i-1].setText(grad);

            i++;
        }


        return sb.toString();
    }

    private String spaces(int cnt){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            s.append(" ");
        }
        return s.toString();
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
        public TextView placeAdressText;
        public TextView placeMembersText;
        public TextView place5StrText;
        public TextView membersDescriptionInfo;

        public ImageView clubImage;
        public TableLayout table;

        public TextView membersName1;
        public TextView membersPoints1;
        public TextView membersGrad1;

        public TextView membersName2;
        public TextView membersPoints2;
        public TextView membersGrad2;

        public TextView membersName3;
        public TextView membersPoints3;
        public TextView membersGrad3;

        public TextView membersName4;
        public TextView membersPoints4;
        public TextView membersGrad4;

        public TextView membersName5;
        public TextView membersPoints5;
        public TextView membersGrad5;


        // 1 - name, 2 - members, 3 - grad
        public TextView[] getTextViewByWord(int type){
            if(type == 1){
                return new TextView[]{membersName1 ,membersName2, membersName3, membersName4, membersName5};
            }
            else if(type == 2){
                return new TextView[]{membersPoints1 ,membersPoints2, membersPoints3, membersPoints4, membersPoints5};
            }
            else if(type == 3){
                return new TextView[]{membersGrad1 ,membersGrad2, membersGrad3, membersGrad4, membersGrad5};
            }
            throw new IllegalArgumentException("unknonm input type " + type);

        }


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public PlaceViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

           clubNameDescriptionTextView = (TextView) itemView.findViewById(R.id.clubNameDescriptionTextView);
           cardDescriptionLink = (TextView) itemView.findViewById(R.id.cardDescrLinkTextView);
           clubImage = (ImageView) itemView.findViewById(R.id.clubImage);
           placeAdressText = (TextView) itemView.findViewById(R.id.placeAddres);
           placeMembersText = (TextView) itemView.findViewById(R.id.clubMembers);
           place5StrText = (TextView) itemView.findViewById(R.id.placeDescr5StrText);
            membersDescriptionInfo = (TextView) itemView.findViewById(R.id.titlesCntTextView);
            table = itemView.findViewById(R.id.placeTable);

            membersName1 = (TextView) itemView.findViewById(R.id.placeTableName1) ;
            membersPoints1 = (TextView) itemView.findViewById(R.id.placeTableMembers1);
            membersGrad1 = (TextView) itemView.findViewById(R.id.placeTableGrad1) ;

            membersName2 = (TextView) itemView.findViewById(R.id.placeTableName2) ;
            membersPoints2 = (TextView) itemView.findViewById(R.id.placeTableMembers2);
            membersGrad2 = (TextView) itemView.findViewById(R.id.placeTableGrad2) ;

            membersName3 = (TextView) itemView.findViewById(R.id.placeTableName3) ;
            membersPoints3 = (TextView) itemView.findViewById(R.id.placeTableMembers3);
            membersGrad3 = (TextView) itemView.findViewById(R.id.placeTableGrad3) ;

            membersName4 = (TextView) itemView.findViewById(R.id.placeTableName4) ;
            membersPoints4 = (TextView) itemView.findViewById(R.id.placeTableMembers4);
            membersGrad4 = (TextView) itemView.findViewById(R.id.placeTableGrad4) ;

            membersName5 = (TextView) itemView.findViewById(R.id.placeTableName5) ;
            membersPoints5 = (TextView) itemView.findViewById(R.id.placeTableMembers5);
            membersGrad5 = (TextView) itemView.findViewById(R.id.placeTableGrad5) ;




        }
    }

}
