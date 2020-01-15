package com.dumplings.pleyt.model;

import java.math.BigDecimal;

public class Customers extends Users{
    protected String StreetAddress;
    protected String YelpId;
    protected String City;
    protected String State;
    protected int Zip;
    protected int ReviewCounts;
    protected BigDecimal AverageRating;

    public Customers(int userId, String userName, String password, String firstName, String lastName,
                     String streetAddress, String yelpId, String city, String state, int zip, int reviewCounts, BigDecimal averageRating) {
        super(userId, userName, password, firstName, lastName);
        this.StreetAddress = streetAddress;
        this.YelpId = yelpId;
        this.City = city;
        this.State = state;
        this.Zip = zip;
        this.ReviewCounts = reviewCounts;
        this.AverageRating = averageRating;
    }

    public Customers(){
        super();
    }

    public String getStreetAddress() {
        return StreetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.StreetAddress = streetAddress;
    }

    public String getYelpId() {
        return YelpId;
    }

    public void setYelpId(String yelpId) {
        this.YelpId = yelpId;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        this.City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        this.State = state;
    }

    public int getZip() {
        return Zip;
    }

    public void setZip(int zip) {
        this.Zip = zip;
    }

    public int getReviewCounts() {
        return ReviewCounts;
    }

    public void setReviewCounts(int reviewCounts) {
        this.ReviewCounts = reviewCounts;
    }

    public BigDecimal getAverageRating() {
        return AverageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.AverageRating = averageRating;
    }
}
