package com.example.chessapp.ui.adaptes;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessapp.R;
import com.example.chessapp.helpers.AppHelpers;
import com.example.chessapp.storage.model.Person;
import com.example.chessapp.storage.model.Puzzle;

import java.util.List;


/**
 * Adapter for the persons cards
 */
public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Person> persons;


    public HistoryAdapter(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 0;
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        if (viewType == 0) // head
        {
            //todo
            View view = inflater.inflate(R.layout.person_head_card, parent, false);
            return new PersonCardHeadViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.person_card, parent, false);
            return new PersonCardViewHolder(view);
        }

    }


    private void onBindPersonViewHolder(PersonCardViewHolder holder, int position){

        TextView personNameTitleText = holder.personNameTitleText;
        TextView personInfoText  = holder.personInfoText;
        ImageView personImage  = holder.personImage;
        TextView personCitate = holder.personCitate;

        Person person = persons.get(position-1);

        personCitate.setText(person.citate);
        personNameTitleText.setText(person.name);

        String text = person.info;
        SpannableString ss = new SpannableString(text);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(boldSpan, 0, person.name.length() + 1    , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        personInfoText.setText(ss);


        AppHelpers.setImageOrDefault(personImage, person.image);

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder.getItemViewType() == 0) {
           // onBindPlaceInfoViewHolder((PlaceViewHolder) holder, position);
        } else {
            onBindPersonViewHolder((PersonCardViewHolder) holder, position);
        }

    }

    @Override
    public int getItemCount() {
        return persons.size() + 1; // puzzles + head
    }


    /**
     * Contains views of title (first) person`s card
     */
    public class PersonCardHeadViewHolder extends RecyclerView.ViewHolder {
        public TextView headTitle;
        public TextView headBody;

        public PersonCardHeadViewHolder(View itemView) {
            super(itemView);

            headTitle = (TextView) itemView.findViewById(R.id.historyHeadTitleText);
            headBody = (TextView) itemView.findViewById(R.id.historyHeadBodyText);

        }
    }


    /**
     * Contains views of person`s card
     */
    public class PersonCardViewHolder extends RecyclerView.ViewHolder {

        public TextView personNameTitleText;
        public TextView personInfoText;
        public ImageView personImage;
        public TextView personCitate;


        public PersonCardViewHolder(View itemView) {
            super(itemView);

            personNameTitleText = (TextView) itemView.findViewById(R.id.personNameTitleText);
            personInfoText = (TextView) itemView.findViewById(R.id.personInfoText);
            personCitate = (TextView) itemView.findViewById(R.id.personCitate);
            personImage = (ImageView) itemView.findViewById(R.id.personImage);

        }
    }

}
