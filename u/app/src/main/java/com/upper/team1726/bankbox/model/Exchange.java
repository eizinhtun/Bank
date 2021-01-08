package com.upper.team1726.bankbox.model;

/**
 * Created by acer on 10/22/2017.
 */

public class Exchange {

    int township_id, city_id;
    private int exchangeId, bankId;
    private String bankName;
    private String exchangeName, exchangeAddress, exchangeOpenTime;
    private double exchangeLatitude, exchangeLongitude;

    public Exchange(String bankName, String exchangeName, String exchangeAddress, String exchangeOpenTime, double exchangeLatitude, double exchangeLongitude) {
        this.bankName = bankName;
        this.exchangeName = exchangeName;
        this.exchangeAddress = exchangeAddress;
        this.exchangeOpenTime = exchangeOpenTime;
        this.exchangeLatitude = exchangeLatitude;
        this.exchangeLongitude = exchangeLongitude;
    }

    public Exchange() {
    }

    public Exchange(int exchangeId, String bank_name, String exchangeName, String exchangeAddress, String exchangeOpenTime, double exchangeLatitude, double exchangeLongitude, int township_id, int city_id) {
        this.exchangeId = exchangeId;
        //this.bankId = bankId;
        this.bankName = bank_name;
        this.exchangeName = exchangeName;
        this.exchangeAddress = exchangeAddress;
        this.exchangeOpenTime = exchangeOpenTime;

        this.exchangeLatitude = exchangeLatitude;
        this.exchangeLongitude = exchangeLongitude;
        this.township_id = township_id;
        this.city_id = city_id;
    }

    public Exchange(String bank_name, String exchangeName, String exchangeAddress, String exchangeOpenTime, double exchangeLatitude, double exchangeLongitude, int township_id, int city_id) {
        //this.exchangeId = exchangeId;
        //this.bankId = bankId;
        this.bankName = bank_name;
        this.exchangeName = exchangeName;
        this.exchangeAddress = exchangeAddress;
        this.exchangeOpenTime = exchangeOpenTime;
        this.exchangeLatitude = exchangeLatitude;
        this.exchangeLongitude = exchangeLongitude;
        this.township_id = township_id;
        this.city_id = city_id;
    }

    public int getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(int exchangeId) {
        this.exchangeId = exchangeId;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getExchangeAddress() {
        return exchangeAddress;
    }

    public void setExchangeAddress(String exchangeAddress) {
        this.exchangeAddress = exchangeAddress;
    }

    public String getExchangeOpenTime() {
        return exchangeOpenTime;
    }

    public void setExchangeOpenTime(String exchangeOpenTime) {
        this.exchangeOpenTime = exchangeOpenTime;
    }

    public double getExchangeLatitude() {
        return exchangeLatitude;
    }

    public void setExchangeLatitude(double exchangeLatitude) {
        this.exchangeLatitude = exchangeLatitude;
    }

    public double getExchangeLongitude() {
        return exchangeLongitude;
    }

    public void setExchangeLongitude(double exchangeLongitude) {
        this.exchangeLongitude = exchangeLongitude;
    }

    public int getTownship_id() {
        return township_id;
    }

    public void setTownship_id(int township_id) {
        this.township_id = township_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }
}

