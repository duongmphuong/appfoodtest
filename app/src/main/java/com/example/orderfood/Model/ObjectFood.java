package com.example.orderfood.Model;

import java.io.Serializable;

public class ObjectFood implements Serializable {
    private int id;

    private byte[] imageFood;

    private String nameFood;

    private String priceFood;

    private int number;

    private String detailFood;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImageFood() {
        return imageFood;
    }

    public void setImageFood(byte[] imageFood) {
        this.imageFood = imageFood;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getPriceFood() {
        return priceFood;
    }

    public void setPriceFood(String priceFood) {
        this.priceFood = priceFood;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDetailFood() {
        return detailFood;
    }

    public void setDetailFood(String detailFood) {
        this.detailFood = detailFood;
    }

    public ObjectFood(int id, byte[] imageFood, String nameFood, String priceFood) {
        this.id = id;
        this.imageFood = imageFood;
        this.nameFood = nameFood;
        this.priceFood = priceFood;

    }public ObjectFood(int id, byte[] imageFood, String nameFood, String priceFood, String detailFood) {
        this.id = id;
        this.imageFood = imageFood;
        this.nameFood = nameFood;
        this.priceFood = priceFood;
        this.detailFood = detailFood;
    }

    public ObjectFood(int id, byte[] imageFood, String nameFood, String priceFood, int number) {
        this.id = id;
        this.imageFood = imageFood;
        this.nameFood = nameFood;
        this.priceFood = priceFood;
        this.number = number;
    }

    public ObjectFood(byte[] imageFood, String nameFood, String priceFood, String detailFood) {
        this.imageFood = imageFood;
        this.nameFood = nameFood;
        this.priceFood = priceFood;
        this.detailFood = detailFood;
    }

    public ObjectFood(byte[] imageFood, String nameFood, String priceFood) {
        this.imageFood = imageFood;
        this.nameFood = nameFood;
        this.priceFood = priceFood;
    }

    public ObjectFood(byte[] imageFood, String nameFood) {
        this.imageFood = imageFood;
        this.nameFood = nameFood;
    }

    public ObjectFood(){

    }
}
