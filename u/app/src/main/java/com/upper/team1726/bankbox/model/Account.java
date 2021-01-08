package com.upper.team1726.bankbox.model;

/**
 * Created by acer on 11/8/2017.
 */

public class Account {
    private int accId;
    private String bankName, name, accountPurpose, initialDeposit, type, depositTime, withdrawTime, description;

    public Account() {

    }

    public Account(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Account(int accId, String bankName, String name, String accountPurpose, String initialDeposit, String type, String depositTime, String withdrawTime, String description) {
        this.accId = accId;
        this.bankName = bankName;
        this.name = name;
        this.accountPurpose = accountPurpose;
        this.initialDeposit = initialDeposit;
        this.type = type;
        this.depositTime = depositTime;
        this.withdrawTime = withdrawTime;
        this.description = description;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountPurpose() {
        return accountPurpose;
    }

    public void setAccountPurpose(String accountPurpose) {
        this.accountPurpose = accountPurpose;
    }

    public String getInitialDeposit() {
        return initialDeposit;
    }

    public void setInitialDeposit(String initialDeposit) {
        this.initialDeposit = initialDeposit;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getDepositTime() {
        return depositTime;
    }

    public void setDepositTime(String depositTime) {
        this.depositTime = depositTime;
    }

    public String getWithdrawTime() {
        return withdrawTime;
    }

    public void setWithdrawTime(String withdrawTime) {
        this.withdrawTime = withdrawTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
