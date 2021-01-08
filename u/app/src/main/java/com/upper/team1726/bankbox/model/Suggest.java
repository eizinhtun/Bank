package com.upper.team1726.bankbox.model;

/**
 * Created by acer on 11/10/2017.
 */

public class Suggest {
    private int accID;
    private int count;
    private double probability;

    public Suggest(int accID, int count) {
        this.accID = accID;
        this.count = count;
    }

    public Suggest(int accID, double probability) {
        this.accID = accID;
        this.probability = probability;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
