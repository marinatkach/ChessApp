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

    public Place(){
    }

    public Place(int id, String clubName, String address, String link, Double longitude, Double latitude, String category, String logo) {
        this.id = id;
        this.clubName = clubName;
        this.address = address;
        this.link = link;
        this.longitude = longitude;
        this.latitude = latitude;
        this.category = category;
        this.logo = logo;
    }

    public Place(String clubName, String address, String link, Double longitude, Double latitude, String category, String logo) {
        this.clubName = clubName;
        this.address = address;
        this.link = link;
        this.longitude = longitude;
        this.latitude = latitude;
        this.category = category;
        this.logo = logo;
        id = -1;
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
