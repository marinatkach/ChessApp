package com.example.chessapp.ui.adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessapp.R;
import com.example.chessapp.storage.model.Puzzle;

import java.util.List;

public class PuzzlesCardsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Puzzle> puzzles;
    private int infoTextId;
    private boolean printSuccessMessage = false;

    public PuzzlesCardsAdapter( List<Puzzle> puzzles, int infoTextId, boolean printSuccessMessage) {
        this.puzzles = puzzles;
        this.infoTextId = infoTextId;
        this.printSuccessMessage = printSuccessMessage;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) return 0;
        if(printSuccessMessage && position == puzzles.size() + 1) return 0;
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        if(viewType == 0) // puzzles info
        {
            View view = inflater.inflate(R.layout.puzzles_head_card, parent, false);
            return new InfoViewHolder(view);
        }
        else {
            View view = inflater.inflate(R.layout.game_card, parent, false);
            return new GameViewHolder(view);
        }

    }


    protected void onBindGameViewHolder(GameViewHolder holder, int position){
        int fixPosition = position - 1 ;
        Puzzle puzzle = this.puzzles.get(fixPosition);
        GameViewHolder.onBindGameViewHolder(holder, puzzle, position);
        holder.gameNameTextView.setText(puzzle.clubName);
    }

    protected void onBindPuzzlesInfoViewHolder(InfoViewHolder holder, int position){
        if(position == 0){
            holder.infoNameTextView.setText(infoTextId);
        }
        else {
            holder.infoNameTextView.setText(R.string.bonus_extra_text);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder.getItemViewType() == 0)
        {
            onBindPuzzlesInfoViewHolder((InfoViewHolder) holder, position);
        }
        else{
            onBindGameViewHolder((GameViewHolder) holder, position);
        }

    }

    @Override
    public int getItemCount() {
        int cnt = puzzles.size() + 1; // puzzles + info
        if(printSuccessMessage) cnt++;
        return cnt;
    }


    public class InfoViewHolder extends RecyclerView.ViewHolder {

        public TextView infoNameTextView;

        public InfoViewHolder(View itemView) {
            super(itemView);
            infoNameTextView = (TextView) itemView.findViewById(R.id.puzzlesHeadInfoTextView);
        }
    }


}
