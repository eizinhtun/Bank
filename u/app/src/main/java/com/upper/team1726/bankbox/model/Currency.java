package com.upper.team1726.bankbox.model;

import java.util.Date;

/**
 * Created by MICRO on 10/25/2017.
 */

public class Currency {

    private int currency_id;
    private int bank_id;
    private String currency_type;
    private int logo;
    private int currencyLogo;
    private String bank_name;
    private int currency_exchange_id;
    private Date currency_date;
    private double buy;
    private double sell;
    private double rate;
    private int mark_id;

    public Currency() {
    }

    public Currency(int currency_id, String currency_type, int logo) {
        this.currency_id = currency_id;
        this.currency_type = currency_type;
        this.logo = logo;
    }

    public Currency(String bank_name, double buy, double sell, int logo) {
        this.bank_name = bank_name;
        this.buy = buy;
        this.sell = sell;
        this.logo = logo;
    }

    public Currency(int currency_id, int bank_id, String currency_type, int logo, int currencyLogo, String bank_name, double buy, double sell) {
        this.currency_id = currency_id;
        this.bank_id = bank_id;
        this.currency_type = currency_type;
        this.logo = logo;
        this.currencyLogo = currencyLogo;
        this.bank_name = bank_name;
        this.buy = buy;
        this.sell = sell;
    }

    public Currency(String currency_type, int currencyLogo, double buy, double sell) {
        this.currency_type = currency_type;
        this.currencyLogo = currencyLogo;
        this.buy = buy;
        this.sell = sell;
    }

    public int getMark_id() {
        return mark_id;
    }

    public void setMark_id(int mark_id) {
        this.mark_id = mark_id;
    }

    public int getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(int currency_id) {
        this.currency_id = currency_id;
    }

    public int getBank_id() {
        return bank_id;
    }

    public void setBank_id(int bank_id) {
        this.bank_id = bank_id;
    }

    public String getCurrency_type() {
        return currency_type;
    }

    public void setCurrency_type(String currency_type) {
        this.currency_type = currency_type;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public int getCurrencyLogo() {
        return currencyLogo;
    }

    public void setCurrencyLogo(int currencyLogo) {
        this.currencyLogo = currencyLogo;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }


    public int getCurrency_exchange_id() {
        return currency_exchange_id;
    }

    public void setCurrency_exchange_id(int currency_exchange_id) {
        this.currency_exchange_id = currency_exchange_id;
    }

    public Date getCurrency_date() {
        return currency_date;
    }

    public void setCurrency_date(Date currency_date) {
        this.currency_date = currency_date;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }
}
