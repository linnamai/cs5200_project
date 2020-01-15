package com.dumplings.pleyt.model;


public class Reservations {
  protected int ReservationId;
  protected Customers customer;
  protected Restaurants restaurant;
  protected int PartySize;
  protected String StartTime;
  protected String EndTime;
  protected String Status;

  public enum Status {
    Pending, Confirmed, Finished;
  }

  public Reservations() {

  }

  public Reservations(int reservationId, Customers customer, Restaurants restaurant, int partySize,
                      String startTime, String endTime, Reservations.Status status) {
    this.ReservationId = reservationId;
    this.customer = customer;
    this.restaurant = restaurant;
    this.PartySize = partySize;
    this.StartTime = startTime;
    this.EndTime = endTime;
    this.Status = status.name();
  }

  public Reservations(Customers customer, Restaurants restaurant, int partySize,
                      String startTime, String endTime, Reservations.Status status) {
    this.customer = customer;
    this.restaurant = restaurant;
    this.PartySize = partySize;
    this.StartTime = startTime;
    this.EndTime = endTime;
    this.Status = status.name();
  }

  public int getReservationId() {
    return ReservationId;
  }

  public void setReservationId(int reservationId) {
    this.ReservationId = reservationId;
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

  public int getPartySize() {
    return PartySize;
  }

  public void setPartySize(int partySize) {
    this.PartySize = partySize;
  }

  public String getStartTime() {
    return StartTime;
  }

  public void setStartTime(String startTime) {
    this.StartTime = startTime;
  }

  public String getEndTime() {
    return EndTime;
  }

  public void setEndTime(String endTime) {
    this.EndTime = endTime;
  }

  public String getStatus() {
    return Status;
  }

  public void setStatus(Reservations.Status status) {
    this.Status = status.name();
  }
}
