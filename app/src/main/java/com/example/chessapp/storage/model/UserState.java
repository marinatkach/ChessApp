package com.example.chessapp.storage.model;

public class UserState implements DBEntry {
    public int id;
    public String accessPassword = "1234"; // expected password
    public String accessType = "Admin"; // type
    public boolean hasAccess = false;  // if true -> has current user has admin access


    @Override
    public int getId() {
        return id;
    }


    public UserState() {
    }

    public UserState(boolean hasAccess, String accessPassword, String accessType) {
        this.hasAccess = hasAccess;
        this.accessPassword = accessPassword;
        this.accessType = accessType;
        this.id = -1;
    }

    public UserState(int id, boolean hasAccess, String accessPassword, String accessType) {
        this.hasAccess = hasAccess;
        this.accessPassword = accessPassword;
        this.accessType = accessType;
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserState{" +
                "id=" + id +
                ", accessPassword='" + accessPassword + '\'' +
                ", accessType='" + accessType + '\'' +
                ", hasAccess=" + hasAccess +
                '}';
    }
}
