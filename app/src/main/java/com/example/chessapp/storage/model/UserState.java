package com.example.chessapp.storage.model;

public class UserState {
    public int id;
    public String accessPassword = "123456";
    public String accessType = "Admin";
    public boolean hasAccess = false;


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
