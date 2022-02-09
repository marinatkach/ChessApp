package com.example.chessapp.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chessapp.storage.model.Place;

public class PlaceTable extends DBResourceManager<Place> {


    public PlaceTable(Context activity, String dbName) {
        super(activity, dbName, "places", "CREATE TABLE places (_id INTEGER PRIMARY KEY AUTOINCREMENT, clubName TEXT NOT NULL, address TEXT NOT NULL, link TEXT NOT NULL, longitude Double NOT NULL, latitude Double NOT NULL, category TEXT NOT NULL, logo TEXT, isVisited INTEGER NOT NULL)");
    }

    @Override
    protected int getKey(Place obj) {
        return obj.id;
    }

    @Override
    protected Place createDataFromCursor(Cursor cursor) {
        Place place = new Place();
        place.id = cursor.getInt(0);
        place.clubName = cursor.getString(1);
        place.address = cursor.getString(2);
        place.link = cursor.getString(3);
        place.longitude = cursor.getDouble(4);
        place.latitude = cursor.getDouble(5);
        place.category = cursor.getString(6);
        place.logo = cursor.getString(7);

        short isVisited = cursor.getShort(8);
        place.isVisited = isVisited != 0;

        return place;
    }

    @Override
    protected ContentValues createContextValuesFromObj(Place obj) {
        ContentValues daten = new ContentValues();
        daten.put("clubName", obj.clubName);
        daten.put("address", obj.address);
        daten.put("link", obj.link);
        daten.put("longitude", obj.longitude);
        daten.put("latitude", obj.latitude);
        daten.put("category", obj.category);
        daten.put("logo", obj.logo);

        short isVisited = 0;
        if(obj.isVisited) isVisited = 1;

        daten.put("isVisited", isVisited);
        return daten;
    }

}
