package com.upper.team1726.bankbox.model;

/**
 * Created by MICRO on 11/13/2017.
 */

public class CentralCurrency {


    private String info, description;
    private long timestamp;
    private Rate rates;

    public CentralCurrency() {

    }

    public CentralCurrency(String info, String description, long timestamp, Rate rates) {
        this.info = info;
        this.description = description;
        this.timestamp = timestamp;
        this.rates = rates;
    }


    public Rate getRates() {
        return rates;
    }


    public String getInfo() {
        return info;
    }

    public String getDescription() {
        return description;
    }


    public long getTimestamp() {
        return timestamp;
    }

}
