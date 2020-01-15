package com.dumplings.pleyt.model;

public class Dishes {
    protected int DishId;
    protected String DishName;
    protected String DishTYpe;
    protected String Description;
    protected double BasePrice;
    protected Restaurants restaurant;

    public Dishes(int dishId, String dishName, String dishTYpe, String description, double basePrice, Restaurants restaurant) {
        this.DishId = dishId;
        this.DishName = dishName;
        this.DishTYpe = dishTYpe;
        this.Description = description;
        this.BasePrice = basePrice;
        this.restaurant = restaurant;
    }

    public int getDishId() {
        return DishId;
    }

    public void setDishId(int dishId) {
        this.DishId = dishId;
    }

    public String getDishName() {
        return DishName;
    }

    public void setDishName(String dishName) {
        this.DishName = dishName;
    }

    public String getDishTYpe() {
        return DishTYpe;
    }

    public void setDishTYpe(String dishTYpe) {
        this.DishTYpe = dishTYpe;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public double getBasePrice() {
        return BasePrice;
    }

    public void setBasePrice(double basePrice) {
        this.BasePrice = basePrice;
    }

    public Restaurants getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurants restaurant) {
        this.restaurant = restaurant;
    }
}
