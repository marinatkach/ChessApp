package com.example.chessapp.storage;

import java.util.List;

public interface ResourceManager<T> {
    void push(T obj); // add new obj
    T get(int key);
    List<T> all();
    T removeByKey(int key); // remove obj by key
    T removeByObj(T obj); // key obj
    void clear(); // delete all records in table
    void recreate();  // drop table and create new
    T update(T obj);
    boolean isExists();
}
