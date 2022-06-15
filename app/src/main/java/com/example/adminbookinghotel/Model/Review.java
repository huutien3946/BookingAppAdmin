package com.example.adminbookinghotel.Model;

public class Review {
    private String name,review, date, star;

    public Review() {
    }

    public Review(String name, String review, String date, String star) {
        this.name = name;
        this.review = review;
        this.date = date;
        this.star = star;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
