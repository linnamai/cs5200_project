package com.dumplings.pleyt.model;

public class Restaurants {

    protected int RestaurantId;
    protected String Name;
    protected String BusinessId;
    protected String StreetAddress;
    protected String City;
    protected String State;
    protected String Category;
    protected boolean Deliverable;
    protected double DeliveryPrice;
    protected int Capacity;

    public Restaurants(int restaurantId, String name, String businessId, String streetAddress, String city, String state,
                       String category, boolean deliverable, double deliveryPrice, int capacity) {
        this.RestaurantId = restaurantId;
        this.Name = name;
        this.BusinessId = businessId;
        this.StreetAddress = streetAddress;
        this.City = city;
        this.State = state;
        this.Category = category;
        this.Deliverable = deliverable;
        this.DeliveryPrice = deliveryPrice;
        this.Capacity = capacity;
    }

    public Restaurants() {}

    public int getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.RestaurantId = restaurantId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getBusinessId() {
        return BusinessId;
    }

    public void setBusinessId(String businessId) {
        this.BusinessId = businessId;
    }

    public String getStreetAddress() {
        return StreetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.StreetAddress = streetAddress;
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

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        this.Category = category;
    }

    public boolean isDeliverable() {
        return Deliverable;
    }

    public void setDeliverable(boolean deliverable) {
        this.Deliverable = deliverable;
    }

    public double getDeliveryPrice() {
        return DeliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.DeliveryPrice = deliveryPrice;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        this.Capacity = capacity;
    }
}
