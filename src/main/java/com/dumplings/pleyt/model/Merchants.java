package com.dumplings.pleyt.model;

public class Merchants extends Users {
  protected Restaurants restaurant;

  public Merchants(int userId, String userName, String password, String firstName, String lastName, Restaurants restaurant) {
    super(userId, userName, password, firstName, lastName);
    this.restaurant = restaurant;
  }

  public Restaurants getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(Restaurants restaurant) {
    this.restaurant = restaurant;
  }
}
