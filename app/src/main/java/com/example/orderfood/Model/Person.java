package com.example.orderfood.Model;

public class Person {
    private int image;

    private String name_image;

    public Person(int image, String name_image) {
        this.image = image;
        this.name_image = name_image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName_image() {
        return name_image;
    }

    public void setName_image(String name_image) {
        this.name_image = name_image;
    }
}
