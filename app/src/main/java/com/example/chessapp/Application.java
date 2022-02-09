package com.example.chessapp;

import android.content.Context;
import android.util.Log;

import com.example.chessapp.storage.DBResourceManager;
import com.example.chessapp.storage.PlaceTable;
import com.example.chessapp.storage.PuzzlesTable;
import com.example.chessapp.storage.ResourceManager;
import com.example.chessapp.storage.UserStateTable;
import com.example.chessapp.storage.model.Place;
import com.example.chessapp.storage.model.Puzzles;
import com.example.chessapp.storage.model.UserState;

import java.util.Arrays;

public class Application {
    public static PlaceTable placeTable = null;
    public static PuzzlesTable puzzlesTable = null;
    public static UserStateTable stateTable = null;
    public static final String DB_NAME = "db";


    // todo: if db not exists -> recreate()
    public static void init(Context context){
        puzzlesTable = new PuzzlesTable(context, DB_NAME);
        placeTable = new PlaceTable(context, DB_NAME);
        stateTable = new UserStateTable(context, DB_NAME);
        fillDB();
    }


    private static void fillDB(){
        if(puzzlesTable.all().isEmpty()){
            Puzzles[] arr = new Puzzles[]
                    {
                            new Puzzles("puzzle1", "solution1", null, true, "im1.jpeg"),
                            new Puzzles("puzzle2", "solution2", null, true, "im2.jpeg"),
                            new Puzzles("puzzle3", "solution3", "Schachklub Gräfelfing", false, "im3.jpeg"),
                            new Puzzles("puzzle4", "solution4", "Schachklub Germering e.V.", false, "im4.jpeg"),
                    };

            Arrays.stream(arr).forEach(puzzlesTable::push);

        }
        if(placeTable.all().isEmpty()){

            Place[] arr = new Place[]
                    {
                            new Place("Schachklub Germering e.V.","Planegger Str. 9, 82110 Germering","https://www.schach-in-germering.de/",  48.12672216850067, 11.369169831264655, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Schachklub Gräfelfing",    "Lochhamer Str. 79, 82166 Gräfelfing","http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Heiterwanger Schachplatz", "Heiterwanger Str./Fernpaßstraße", "http://www.spielplaetze-in-muenchen.de/cgi-bin/details.pl?n=Heiterwanger%20Schachplatz",48.121625080099726,11.521993986945072, Place.CATEGORY_CAFE, "empty", false),
                            new Place("Mehlfeld's", "Guardinistr. 98a, 81375 München", "https://www.mehlfeld.com/", 48.11949476046334, 11.490581831820519, Place.CATEGORY_OUTDOOR, "empty", false)
                    };

            Arrays.stream(arr).forEach(placeTable::push);

        }

        if(stateTable.all().isEmpty()){
            UserState state = new UserState(false, "1234", "admin");
            stateTable.push(state);
        }
    }

    public static void printAllTables(){
        Log.i("DB", "-----------------------------------");
        printTable(placeTable);
        Log.i("DB", "-----------------------------------");
        printTable(puzzlesTable);
        Log.i("DB", "-----------------------------------");
        printTable(stateTable);
        Log.i("DB", "-----------------------------------");
    }

    public static void printTable(DBResourceManager<?> table){
        Log.i("DB", "Table Name :" +  table.getTableName());
        Log.i("DB", "Size:" +  table.all().size());

        table.all().forEach(it -> Log.i("DB", it.toString()));
    }

}
