//package com.example.chessapp.storage;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//
//import com.example.chessapp.storage.model.Place;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class PersonTable extends DBResourceManager<Place> {
//
//
//    public PersonTable(Context activity, String dbName) {
//        super(activity, dbName, "persons", "CREATE TABLE places (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, info TEXT NOT NULL, сitation TEXT NOT NULL,image TEXT NOT NULL    );");
//    }
//
//    @Override
//    protected int getKey(Place obj) {
//        return obj.id;
//    }
//
//    public List<Place> getPlacesByVisitedState(boolean isVisited){
//        return all().stream().filter(it -> it.isVisited == isVisited).collect(Collectors.toList());
//    }
//
//    public List<Place> getPlacesByCategory(String category){
//        return all().stream().filter(it -> it.category.equals(category)).collect(Collectors.toList());
//    }
//    @Override
//    protected Place createDataFromCursor(Cursor cursor) {
//
//
//
//        return place;
//    }
//
//    @Override
//    protected ContentValues createContextValuesFromObj(Place obj) {
//        ContentValues daten = new ContentValues();
//        daten.put("clubName", obj.clubName);
//        daten.put("address", obj.address);
//        daten.put("link", obj.link);
//        daten.put("longitude", obj.longitude);
//        daten.put("latitude", obj.latitude);
//        daten.put("category", obj.category);
//        daten.put("logo", obj.logo);
//        daten.put("info", obj.info);
//
//        short isVisited = 0;
//        if(obj.isVisited) isVisited = 1;
//
//        daten.put("isVisited", isVisited);
//        return daten;
//    }
//
//}
