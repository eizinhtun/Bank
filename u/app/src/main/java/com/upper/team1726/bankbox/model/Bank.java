package com.upper.team1726.bankbox.model;

/**
 * Created by UCSM on 10/20/2017.
 */
public class Bank {
    private int bankId;
    private String name;
    private int imageURL;
    private String color;


    public Bank(int id, String name, int image, String color) {
        this.bankId = id;
        this.name = name;
        this.imageURL = image;
        this.color = color;

    }

    public Bank() {
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public int getImageURL() {
        return imageURL;
    }

    public void setImageURL(int imageURL) {
        this.imageURL = imageURL;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
