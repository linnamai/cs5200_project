package com.dumplings.pleyt.model;

public class OrderDetails {
  protected int OrderDetailId;
  protected Orders order;
  protected Dishes dish;

  public OrderDetails(int orderDetailId, Orders order, Dishes dish) {
    OrderDetailId = orderDetailId;
    this.order = order;
    this.dish = dish;
  }

  public int getOrderDetailId() {
    return OrderDetailId;
  }

  public void setOrderDetailId(int orderDetailId) {
    this.OrderDetailId = orderDetailId;
  }

  public Orders getOrder() {
    return order;
  }

  public void setOrder(Orders order) {
    this.order = order;
  }

  public Dishes getDish() {
    return dish;
  }

  public void setDish(Dishes dish) {
    this.dish = dish;
  }
}
