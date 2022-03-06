package com.example.chessapp.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.chessapp.storage.model.DBEntry;

import java.util.ArrayList;
import java.util.List;

public abstract class DBResourceManager<T extends DBEntry> extends SQLiteOpenHelper implements ResourceManager<T> {
    protected SQLiteDatabase db; // DB object
    private String tableName; // table name in DB
    private String dbName; // data base name (like 'db123')
    private String sql; // sql query to create table

    protected int getKey(T obj){
        return obj.getId();
    };


    protected abstract T createDataFromCursor(Cursor cursor); // to get data from db
    protected abstract ContentValues createContextValuesFromObj(T obj); // to save data to db

    public String getTableName() {
        return tableName;
    }

    public DBResourceManager(Context activity, String dbName, String tableName, String SQL) {
        super(activity, dbName, null, 1);
        this.dbName = dbName;
        this.tableName = tableName;
        this.sql = SQL;
        db = this.getWritableDatabase();
        if(!isExists()) recreate();

    }

    @Override
    public boolean isExists() {
        Cursor cursor = null;
        try {
            cursor = db.query("sqlite_master", new String[]{"name"}, String.format("type='table' AND name='%s'", tableName), null, null, null, null);
            return cursor.getCount() > 0 ;
        } catch (Exception ex) {
           throw ex;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }

    @Override
    public synchronized void close() {
        if (db != null) {
            db.close();
            db = null;
        }
        super.close();
    }

    @Override
    public void push(T obj) {
        ContentValues daten = createContextValuesFromObj(obj);
        db.insert(tableName, null, daten);
    }



    public List<T> all() {
        List<T> ergebnis = new ArrayList<T>();
        Cursor cursor = null;
        try {
            cursor = db.query(tableName, null, null, null, null, null, null);
            int anzahl = cursor.getCount();
            cursor.moveToFirst();
            for (int i = 0; i < anzahl; i++) {
                T ds = createDataFromCursor(cursor);
                ergebnis.add(ds);
                cursor.moveToNext();
            }
        } catch (Exception ex) {
            Log.e("gehtfxs", ex.getMessage());
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return ergebnis;
    }

    @Override
    public T update(T obj) {
            ContentValues daten = createContextValuesFromObj(obj);
            db.update(tableName, daten, "_id = " + getKey(obj), null);
            return obj;
    }


    @Override
    public T removeByKey(int key) {
        T obj = get(key);
        if( obj == null) return null;
        db.delete(tableName, "_id = " + key, null);
        return obj;
    }

    @Override
    public T removeByObj(T obj) {
            return removeByKey(getKey(obj));
    }

    @Override
    public T get(int key) {
        for (T t : all()) {
            if(getKey(t) == key) {
                return t;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        for (T t : all()) {
            removeByObj(t);
        }
    }

    @Override
    public void recreate() {
        Log.w("DB","recreate table " + tableName);
        onUpgrade(db, 0, 1);
    }

}

