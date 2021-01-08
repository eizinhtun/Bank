package com.upper.team1726.bankbox.model;

/**
 * Created by acer on 10/22/2017.
 */

public class ATM {

    int atmId;
    int bankId;
    String bankName;
    String atmName, atmAddress, atmOpenTime;
    double atmLatitude, atmLongitude;
    int township_id, city_id;

    public ATM() {

    }

    public ATM(String bankName, String atmName, String atmAddress, String atmOpenTime, double atmLatitude, double atmLongitude) {
        this.bankName = bankName;
        this.atmName = atmName;
        this.atmAddress = atmAddress;
        this.atmOpenTime = atmOpenTime;
        this.atmLatitude = atmLatitude;
        this.atmLongitude = atmLongitude;
    }

    public ATM(String bankName, String atmName, String atmAddress, double atmLatitude,
               double atmLongitude, int township_id, int city_id) {
        //this.atmId = atmId;
        this.bankName = bankName;
        this.atmName = atmName;
        this.atmAddress = atmAddress;
        this.atmLatitude = atmLatitude;
        this.atmLongitude = atmLongitude;
        this.township_id = township_id;
        this.city_id = city_id;
    }

    public ATM(int atmId, String bankName, String atmName, String atmAddress, double atmLatitude,
               double atmLongitude, int township_id, int city_id) {
        this.atmId = atmId;
        this.bankName = bankName;
        this.atmName = atmName;
        this.atmAddress = atmAddress;
        this.atmLatitude = atmLatitude;
        this.atmLongitude = atmLongitude;
        this.township_id = township_id;
        this.city_id = city_id;
    }

    public int getAtmId() {
        return atmId;
    }

    public void setAtmId(int atmId) {
        this.atmId = atmId;
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

    public String getAtmName() {
        return atmName;
    }

    public void setAtmName(String atmName) {
        this.atmName = atmName;
    }

    public String getAtmAddress() {
        return atmAddress;
    }

    public void setAtmAddress(String atmAddress) {
        this.atmAddress = atmAddress;
    }

    public String getAtmOpenTime() {
        return atmOpenTime;
    }

    public void setAtmOpenTime(String atmOpenTime) {
        this.atmOpenTime = atmOpenTime;
    }

    public double getAtmLatitude() {
        return atmLatitude;
    }

    public void setAtmLatitude(double atmLatitude) {
        this.atmLatitude = atmLatitude;
    }

    public double getAtmLongitude() {
        return atmLongitude;
    }

    public void setAtmLongitude(double atmLongitude) {
        this.atmLongitude = atmLongitude;
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
