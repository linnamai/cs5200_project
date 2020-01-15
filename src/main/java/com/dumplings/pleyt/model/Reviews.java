package com.dumplings.pleyt.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Reviews {
  protected int ReviewId;
  protected Customers customer;
  protected Restaurants restaurant;
  protected BigDecimal Rating;
  protected String Content;
  protected String CreateDate;

  public Reviews(int reviewId, Customers customer, Restaurants restaurant, BigDecimal rating, String content, String createDate) {
    this.ReviewId = reviewId;
    this.customer = customer;
    this.restaurant = restaurant;
    this.Rating = rating;
    this.Content = content;
    this.CreateDate = createDate;
  }

  public int getReviewId() {
    return ReviewId;
  }

  public void setReviewId(int reviewId) {
    this.ReviewId = reviewId;
  }

  public Customers getCustomer() {
    return customer;
  }

  public void setCustomer(Customers customer) {
    this.customer = customer;
  }

  public Restaurants getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(Restaurants restaurant) {
    this.restaurant = restaurant;
  }

  public BigDecimal getRating() {
    return Rating;
  }

  public void setRating(BigDecimal rating) {
    this.Rating = rating;
  }

  public String getContent() {
    return Content;
  }

  public void setContent(String content) {
    this.Content = content;
  }

  public String getCreateDate() {
    return CreateDate;
  }

  public void setCreateDate(String createDate) {
    this.CreateDate = createDate;
  }
}
