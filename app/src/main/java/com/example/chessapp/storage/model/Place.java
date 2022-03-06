package com.example.chessapp.storage.model;


public class Place implements DBEntry {
    public int id;
    public String clubName;
    public String address;
    public String info; // GM info (table), row split by '\n', columns by ';', example "Firouzja, Alireza ;2804 ; GM \n Eljanov, Pavel ;2683 ; GM \n Saric, Ivan ;2678 ; GM \n Jones, Gawain ;2671 ; GM\n Indjic, Aleksandar ;2604 ; GM"
    public String labels; // additional info for map and sescription (club -> cnt titel members, cafe -> short description)
    public String link;
    public Double longitude;
    public Double latitude;
    public String category; // cafe, club, outdoor
    public String logo; // name of image with lolo from drawable
    public boolean isVisited;
    public Integer membersCnt;

    public static final String CATEGORY_CAFE = "cafe";
    public static final String CATEGORY_CLUB = "club";
    public static final String CATEGORY_OUTDOOR = "outdoor";

    @Override
    public int getId() {
        return id;
    }


    public Place(){
    }

    public Place(String clubName, String address, String info, String labels,  String link, Double longitude, Double latitude, String category, String logo, boolean isVisited, Integer membersCnt) {
        this.id = -1;
        this.clubName = clubName;
        this.address = address;
        this.link = link;
        this.longitude = longitude;
        this.latitude = latitude;
        this.category = category;
        this.logo = logo;
        this.isVisited = isVisited;
        this.info = info;
        this.membersCnt = membersCnt;
        this.labels = labels;
    }


    public Place(int id, String clubName, String address,  String labels,  String info, String link, Double longitude, Double latitude, String category, String logo, boolean isVisited, Integer membersCnt) {
        this.id = id;
        this.clubName = clubName;
        this.address = address;
        this.link = link;
        this.longitude = longitude;
        this.latitude = latitude;
        this.category = category;
        this.logo = logo;
        this.isVisited = isVisited;
        this.info = info;
        this.membersCnt = membersCnt;
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", clubName='" + clubName + '\'' +
                ", address='" + address + '\'' +
                ", info='" + info + '\'' +
                ", labels='" + labels + '\'' +
                ", link='" + link + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", category='" + category + '\'' +
                ", logo='" + logo + '\'' +
                ", isVisited=" + isVisited +
                ", membersCnt=" + membersCnt +
                '}';
    }
}
