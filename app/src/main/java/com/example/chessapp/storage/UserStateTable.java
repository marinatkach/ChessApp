package com.example.chessapp.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chessapp.storage.model.UserState;

public class UserStateTable extends DBResourceManager<UserState> {


    public UserStateTable(Context activity, String dbName) {
        super(activity, dbName, "user",  "CREATE TABLE user (_id INTEGER PRIMARY KEY AUTOINCREMENT, accessPassword TEXT NOT NULL, accessType TEXT NOT NULL, hasAccess INTEGER NOT NULL)\n");
    }

    @Override
    protected int getKey(UserState obj) {
        return obj.id;
    }


    @Override
    protected UserState createDataFromCursor(Cursor cursor) {
        UserState place = new UserState();
        place.id = cursor.getInt(0);
        place.accessPassword = cursor.getString(1);
        place.accessType = cursor.getString(2);
        place.hasAccess = cursor.getShort(3) != 0;
        return place;
    }

    @Override
    protected ContentValues createContextValuesFromObj(UserState obj) {
        ContentValues daten = new ContentValues();
        daten.put("accessPassword", obj.accessPassword);
        daten.put("accessType", obj.accessType);

        short hasAccess = 0;
        if(obj.hasAccess) hasAccess = 1;

        daten.put("hasAccess", hasAccess);

        return daten;
    }

}
