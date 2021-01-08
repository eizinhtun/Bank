package com.upper.team1726.bankbox.model;

/**
 * Created by USER on 10/24/2017.
 */
public class Review {
    private String name, review, bank_name;
    private String reviewDate;

    public Review() {

    }

    public Review(String bank_name, String name, String review, String date) {
        this.name = name;
        this.review = review;
        this.reviewDate = date;
        this.bank_name = bank_name;
    }

    public Review(String bank_name, String review, String date) {
        this.bank_name = bank_name;
        this.review = review;
        this.reviewDate = date;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
}
