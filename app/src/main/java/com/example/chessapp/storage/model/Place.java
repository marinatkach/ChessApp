package com.example.chessapp.storage.model;

public class Place {
    public int id;
    public String clubName;
    public String address;
    public String link;
    public Double longitude;
    public Double latitude;
    public String category;
    public String logo;
    public boolean isVisited;

    public static final String CATEGORY_CAFE = "cafe";
    public static final String CATEGORY_CLUB = "club";
    public static final String CATEGORY_OUTDOOR = "outdoor";


    public Place(){
    }

    public Place(String clubName, String address, String link, Double longitude, Double latitude, String category, String logo, boolean isVisited) {
        this.id = -1;
        this.clubName = clubName;
        this.address = address;
        this.link = link;
        this.longitude = longitude;
        this.latitude = latitude;
        this.category = category;
        this.logo = logo;
        this.isVisited = isVisited;
    }


    public Place(int id, String clubName, String address, String link, Double longitude, Double latitude, String category, String logo, boolean isVisited) {
        this.id = id;
        this.clubName = clubName;
        this.address = address;
        this.link = link;
        this.longitude = longitude;
        this.latitude = latitude;
        this.category = category;
        this.logo = logo;
        this.isVisited = isVisited;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", clubName='" + clubName + '\'' +
                ", address='" + address + '\'' +
                ", link='" + link + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", category='" + category + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
