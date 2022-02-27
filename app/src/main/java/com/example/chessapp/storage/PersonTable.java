package com.example.chessapp.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chessapp.storage.model.Person;
import com.example.chessapp.storage.model.Place;
import com.example.chessapp.storage.model.Puzzle;



public class PersonTable extends DBResourceManager<Person> {


    public PersonTable(Context activity, String dbName) {
        super(activity, dbName, "persons", "CREATE TABLE persons (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, info TEXT NOT NULL, сitation TEXT NOT NULL, image TEXT NOT NULL );");
    }

    @Override
    protected int getKey(Person obj) {
        return obj.id;
    }

    @Override
    protected Person createDataFromCursor(Cursor cursor) {
        Person person = new Person();
        person.id = cursor.getInt(0);
        person.name = cursor.getString(1);
        person.info = cursor.getString(2);
        person.citate = cursor.getString(3);
        person.image = cursor.getString(4);

        return person;
    }

    @Override
    protected ContentValues createContextValuesFromObj(Person obj) {
        ContentValues daten = new ContentValues();
        daten.put("name", obj.name);
        daten.put("info", obj.info);
        daten.put("сitation", obj.citate);
        daten.put("image", obj.info);
        return daten;
    }

}
