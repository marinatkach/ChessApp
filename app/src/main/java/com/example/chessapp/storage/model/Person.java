package com.example.chessapp.storage.model;

public class Person {
    public int id;
    public String name;
    public String info;
    public String citate;
    public String image;


    public Person(){
    }

    public Person(String name, String info, String citate, String image) {
        this.id = -1;
        this.name = name;
        this.info = info;
        this.citate = citate;
        this.image = image;
    }

    public Person(int id, String name, String info, String citate, String image) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.citate = citate;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", citate='" + citate + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
