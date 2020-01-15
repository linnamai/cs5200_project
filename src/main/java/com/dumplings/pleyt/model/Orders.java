package com.dumplings.pleyt.model;


public class Orders {
  protected int OrderId;
  protected Customers customer;
  protected Restaurants restaurant;
  protected double OrderPrice;
  protected String Status;

  public enum Status {
    Pending, Confirmed, Finished
  }

  protected String CreateTime;
  protected CreditCards creditCard;
  protected String EndTime;

  public Orders(int orderId, Customers customer, Restaurants restaurant, double orderPrice,
                Orders.Status status,
                String createTime, CreditCards creditCard, String endTime) {
    this.OrderId = orderId;
    this.customer = customer;
    this.restaurant = restaurant;
    this.OrderPrice = orderPrice;
    this.Status = status.name();
    this.CreateTime = createTime;
    this.creditCard = creditCard;
    this.EndTime = endTime;
  }

  public int getOrderId() {
    return OrderId;
  }

  public void setOrderId(int orderId) {
    this.OrderId = orderId;
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

  public double getOrderPrice() {
    return OrderPrice;
  }

  public void setOrderPrice(double orderPrice) {
    this.OrderPrice = orderPrice;
  }

  public String getStatus() {
    return Status;
  }

  public void setStatus(Orders.Status status) {
    this.Status = status.name();
  }

  public String getCreateTime() {
    return CreateTime;
  }

  public void setCreateTime(String createTime) {
    this.CreateTime = createTime;
  }

  public CreditCards getCreditCard() {
    return creditCard;
  }

  public void setCreditCard(CreditCards creditCard) {
    this.creditCard = creditCard;
  }

  public String getEndTime() {
    return EndTime;
  }

  public void setEndTime(String endTime) {
    this.EndTime = endTime;
  }
}
