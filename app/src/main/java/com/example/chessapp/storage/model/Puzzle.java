package com.example.chessapp.storage.model;

public class Puzzle {
    public int id;
    public String name;
    public String solution;
    public String clubName;
    public String playerText;
    public boolean isPublic = true;
    public String image;

    public Puzzle() {
    }

    public Puzzle(String name, String solution, String clubName, String playerText,  boolean isPublic, String image) {
        this.id = -1;
        this.name = name;
        this.solution = solution;
        this.clubName = clubName;
        this.isPublic = isPublic;
        this.image = image;
        this.playerText = playerText;
    }

    public Puzzle(int id, String name, String solution, String clubName, String playerText, boolean isPublic, String image) {
        this.id = id;
        this.name = name;
        this.solution = solution;
        this.clubName = clubName;
        this.isPublic = isPublic;
        this.image = image;
        this.playerText = playerText;
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", solution='" + solution + '\'' +
                ", clubName='" + clubName + '\'' +
                ", playerText='" + playerText + '\'' +
                ", isPublic=" + isPublic +
                ", image='" + image + '\'' +
                '}';
    }
}
