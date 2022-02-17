package com.example.chessapp.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chessapp.storage.model.Puzzle;

import java.util.List;
import java.util.stream.Collectors;

public class PuzzlesTable extends DBResourceManager<Puzzle> {

    public PuzzlesTable(Context activity, String dbName) {
        super(activity, dbName, "puzzles", "CREATE TABLE puzzles ( _id INTEGER PRIMARY KEY AUTOINCREMENT,  name TEXT NOT NULL,  solution TEXT NOT NULL,  clubName TEXT, playerNames TEXT NOT NULL,  isPublic INTEGER NOT NULL,  image TEXT NOT NULL )\n");
    }

    @Override
    protected int getKey(Puzzle obj) {
        return obj.id;
    }

    public List<Puzzle> findPuzzlesByClubName(String clubName){
        return all().stream().filter(it -> it.clubName.equals(clubName)).collect(Collectors.toList());
    }


    @Override
    protected Puzzle createDataFromCursor(Cursor cursor) {
        Puzzle puzzles = new Puzzle();
        puzzles.id = cursor.getInt(0);
        puzzles.name = cursor.getString(1);
        puzzles.solution = cursor.getString(2);
        puzzles.clubName = cursor.getString(3);
        puzzles.playerText = cursor.getString(4);

        short isPublic = cursor.getShort(5);
        puzzles.isPublic = isPublic != 0;

        puzzles.image = cursor.getString(6);

        return puzzles;
    }

    @Override
    protected ContentValues createContextValuesFromObj(Puzzle obj) {
        ContentValues daten = new ContentValues();
        daten.put("name", obj.name);
        daten.put("solution", obj.solution);
        daten.put("clubName", obj.clubName);
        daten.put("image", obj.image);
        daten.put("playerNames", obj.playerText);

        short isPublic = 0;
        if(obj.isPublic) isPublic = 1;

        daten.put("isPublic", isPublic);

        return daten;
    }

}
