package com.example.chessapp.storage;

import com.example.chessapp.storage.model.DBEntry;

import java.util.List;



public interface ResourceManager<T extends DBEntry> {
    void push(T obj); // add new obj
    T get(int key); // get object by id
    List<T> all(); // get all objects from table
    T removeByKey(int key); // remove obj by key
    T removeByObj(T obj); // key obj
    void clear(); // delete all records in table
    void recreate();  // drop table and create new
    T update(T obj); // rewrite object
    boolean isExists(); // if table exists in DB
}
