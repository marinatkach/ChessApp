package com.example.chessapp.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chessapp.storage.model.Place;

public class PlaceTable extends DBResourceManager<Place> {


    public PlaceTable(Context activity, String dbName) {
        super(activity, dbName, "places", "CREATE TABLE places (_id INTEGER PRIMARY KEY AUTOINCREMENT, clubName TEXT NOT NULL, address TEXT NOT NULL, info TEXT NOT NULL, link TEXT NOT NULL, longitude Double NOT NULL, latitude Double NOT NULL, category TEXT NOT NULL, logo TEXT, isVisited INTEGER NOT NULL)");
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
        place.info = cursor.getString(3);
        place.link = cursor.getString(4);
        place.longitude = cursor.getDouble(5);
        place.latitude = cursor.getDouble(6);
        place.category = cursor.getString(7);
        place.logo = cursor.getString(8);

        short isVisited = cursor.getShort(9);
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
        daten.put("info", obj.info);

        short isVisited = 0;
        if(obj.isVisited) isVisited = 1;

        daten.put("isVisited", isVisited);
        return daten;
    }

}
