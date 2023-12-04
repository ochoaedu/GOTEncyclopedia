package com.example.gotencyclopedia;

public class Character {

    private String fullName;
    private String title;
    private String house;
    private String image;

    //Constructor
    public Character(String fullName, String title, String house, String image) {
        this.fullName = fullName;
        this.title = title;
        this.house = house;
        this.image = image;
    }

    // Getters for the fields
    public String getFullName() {
        return fullName;
    }

    public String getTitle() {
        return title;
    }

    public String getHouse() {
        return house;
    }

    public String getImage() {
        return image;
    }
}
