package com.upper.team1726.bankbox.model;

/**
 * Created by acer on 10/22/2017.
 */

public class Branch {
    int branchId, bankId;
    String bankName;
    String branchName, branchAddress, branchPhNo;
    double branchLatitude, branchLongitude;
    int branchTownship_id, branchCity_id;

    public Branch() {

    }

    public Branch(String bankName, String branchName, String branchAddress, String branchPhNo, double branchLatitude, double branchLongitude, int township, int city) {
        this.branchId = branchId;
        this.bankId = bankId;
        this.bankName = bankName;
        this.branchName = branchName;
        this.branchAddress = branchAddress;
        this.branchPhNo = branchPhNo;
        this.branchLatitude = branchLatitude;
        this.branchLongitude = branchLongitude;
        this.branchTownship_id = township;
        this.branchCity_id = city;
    }

    public Branch(String bankName, String branchName, String branchAddress, String branchPhNo, double branchLatitude, double branchLongitude) {
        this.bankName = bankName;
        this.branchName = branchName;
        this.branchAddress = branchAddress;
        this.branchPhNo = branchPhNo;
        this.branchLatitude = branchLatitude;
        this.branchLongitude = branchLongitude;
    }


    public Branch(int branchId, String bankName, String branchName, String branchAddress, String branchPhNo, double branchLatitude, double branchLongitude, int township, int city) {
        this.branchId = branchId;
        this.bankId = bankId;
        this.bankName = bankName;
        this.branchName = branchName;
        this.branchAddress = branchAddress;
        this.branchPhNo = branchPhNo;
        this.branchLatitude = branchLatitude;
        this.branchLongitude = branchLongitude;
        this.branchTownship_id = township;
        this.branchCity_id = city;
    }


    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getBranchPhNo() {
        return branchPhNo;
    }

    public void setBranchPhNo(String branchPhNo) {
        this.branchPhNo = branchPhNo;
    }

    public double getBranchLatitude() {
        return branchLatitude;
    }

    public void setBranchLatitude(double branchLatitude) {
        this.branchLatitude = branchLatitude;
    }

    public double getBranchLongitude() {
        return branchLongitude;
    }

    public void setBranchLongitude(double branchLongitude) {
        this.branchLongitude = branchLongitude;
    }

    public int getBranchTownship() {
        return branchTownship_id;
    }

    public void setBranchTownship(int branchTownship) {
        this.branchTownship_id = branchTownship;
    }

    public int getBranchCity() {
        return branchCity_id;
    }

    public void setBranchCity(int branchCity) {
        this.branchCity_id = branchCity;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getBranchTownship_id() {
        return branchTownship_id;
    }

    public void setBranchTownship_id(int branchTownship_id) {
        this.branchTownship_id = branchTownship_id;
    }

    public int getBranchCity_id() {
        return branchCity_id;
    }

    public void setBranchCity_id(int branchCity_id) {
        this.branchCity_id = branchCity_id;
    }
}
