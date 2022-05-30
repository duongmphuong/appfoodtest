package com.example.orderfood.Model;

public class Notification_rcv {
    private int image;

    private String nameStore;

    private String content;

    public Notification_rcv(int image, String nameStore, String content) {
        this.image = image;
        this.nameStore = nameStore;
        this.content = content;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
