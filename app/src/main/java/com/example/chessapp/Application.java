package com.example.chessapp;

import android.content.Context;
import android.util.Log;

import com.example.chessapp.helpers.AppHelpers;
import com.example.chessapp.storage.DBResourceManager;
import com.example.chessapp.storage.PlaceTable;
import com.example.chessapp.storage.PuzzlesTable;
import com.example.chessapp.storage.UserStateTable;
import com.example.chessapp.storage.model.Place;
import com.example.chessapp.storage.model.Puzzle;
import com.example.chessapp.storage.model.UserState;

import java.util.Arrays;

public class Application {
    public static PlaceTable placeTable = null;
    public static PuzzlesTable puzzlesTable = null;
    public static UserStateTable stateTable = null;
    public static final String DB_NAME = "db";


    // todo: if db not exists -> recreate()
    public static void init(Context context, boolean clearAndFill, boolean recreate){
        puzzlesTable = new PuzzlesTable(context, DB_NAME);
        placeTable = new PlaceTable(context, DB_NAME);
        stateTable = new UserStateTable(context, DB_NAME);

        if(recreate){
            puzzlesTable.recreate();
            placeTable.recreate();
            stateTable.recreate();
        }
        if(clearAndFill){
            puzzlesTable.clear();
            placeTable.clear();
            stateTable.clear();
        }
        fillDB();
    }


    private static void fillDB(){
        if(puzzlesTable.all().isEmpty()){
            Puzzle[] arr = new Puzzle[]
                    {
                            new Puzzle("puzzle6", "solution1", null,  "N - N", true, "im1.jpeg"),
                            new Puzzle("puzzle1", "solution1", null,  AppHelpers.createPlayerText("Ivanov", "Petrov", 2010), true, "im1.jpeg"),
                            new Puzzle("puzzle2", "solution2", null, AppHelpers.createPlayerText("Vasilenko", "Petrov", 2012) , true, "im2.jpeg"),
                            new Puzzle("puzzle3", "solution3", "Schachklub Gräfelfing", AppHelpers.createPlayerText("Kosparov", "Petrov", 3010) ,  false, "im3.jpeg"),
                            new Puzzle("Etude 1", "solution4", "Schachklub Germering e.V.", AppHelpers.createPlayerText("Ivanov", "Aleksandrovskiy", 2012) ,  false, "im4.jpeg"),
                            new Puzzle("Etude 10", "solution4", "Germering e.V.", AppHelpers.createPlayerText("Ivanov", "Robin", 2011) ,  false, "im4.jpeg"),
                            new Puzzle("Etude 12", "solution4", "Germering e.V.", AppHelpers.createPlayerText("Batman", "Petrov", 2000) ,  false, "im4.jpeg"),
                    };

            Arrays.stream(arr).forEach(puzzlesTable::push);

        }
        if(placeTable.all().isEmpty()){

            String dummyInfoText = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec";

            Place[] arr = new Place[]
                    {
                            new Place("Germering e.V.","Planegger Str. 9, 82110 Germering",dummyInfoText,"https://www.schach-in-germering.de/",  48.12672216850067, 11.369169831264655, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Gräfelfing",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Germering e.V.",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Gräfelfing",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Nymphenburg Schach e.V.",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Schach-Union München e.V.",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("SC Sendling e.V.",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Garching 1980 e.V.",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("MSA Zugzwang 82 e.V.",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Roter Turm Altstadt München",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Münchener 1836 e. V.",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Tarrasch München",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Unterhaching",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Trudering",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Gräfelfing Gräfelfing 1",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Gräfelfing Gräfelfing 1",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Gräfelfing Gräfelfing 1",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Gräfelfing Gräfelfing 1",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Gräfelfing Gräfelfing 1",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Gräfelfing Gräfelfing 1",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Gräfelfing Gräfelfing 1",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Gräfelfing Gräfelfing 1",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Gräfelfing Gräfelfing 1",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Gräfelfing Gräfelfing 1",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Gräfelfing Gräfelfing 1",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Gräfelfing Gräfelfing 1",    "Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",   48.135776711629575, 11.429469356249529, Place.CATEGORY_CLUB, "empty", false),
                            new Place("Heiterwanger Schachplatz", "Heiterwanger Str./Fernpaßstraße", dummyInfoText,"http://www.spielplaetze-in-muenchen.de/cgi-bin/details.pl?n=Heiterwanger%20Schachplatz",48.121625080099726,11.521993986945072, Place.CATEGORY_CAFE, "empty", false),
                            new Place("Mehlfeld's", "Guardinistr. 98a, 81375 München", dummyInfoText,"https://www.mehlfeld.com/", 48.11949476046334, 11.490581831820519, Place.CATEGORY_OUTDOOR, "empty", false)
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
