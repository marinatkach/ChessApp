package com.example.chessapp.storage.model;

public class Puzzles {
    public int id;
    public String name;
    public String solution;
    public String clubName;
    public boolean isPublic = true;
    public String image;

    public Puzzles() {
    }

    public Puzzles(String name, String solution, String clubName, boolean isPublic, String image) {
        this.id = -1;
        this.name = name;
        this.solution = solution;
        this.clubName = clubName;
        this.isPublic = isPublic;
        this.image = image;
    }

    public Puzzles(int id, String name, String solution, String clubName, boolean isPublic, String image) {
        this.id = id;
        this.name = name;
        this.solution = solution;
        this.clubName = clubName;
        this.isPublic = isPublic;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Puzzles{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", solution='" + solution + '\'' +
                ", clubName='" + clubName + '\'' +
                ", isPublic=" + isPublic +
                ", image='" + image + '\'' +
                '}';
    }
}
