package com.dumplings.pleyt.controller;

import com.dumplings.pleyt.dao.OrdersDao;
import com.dumplings.pleyt.model.Orders;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class OrderController {

  @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
  public Orders createOrder(@RequestBody Orders order) throws SQLException {
    System.out.println("OrderController.createOrder()");
    OrdersDao ordersDao = OrdersDao.getInstance();
    return ordersDao.create(order);
  }

  @RequestMapping(value = "/getOrdersByRestaurantId", method = RequestMethod.GET)
  public List<Orders> showOrderListForRestaurant(@RequestParam int restaurantId) throws SQLException {
    System.out.println("OrderController.showOrderListForRestaurant()");
    OrdersDao ordersDao = OrdersDao.getInstance();
    List<Orders> list = ordersDao.getOrdersByRestaurantId(restaurantId);
    return list;
  }

  @RequestMapping(value = "/getOrderByCustomerId", method = RequestMethod.GET)
  public List<Orders> showOrderList(@RequestParam int customerId) throws SQLException {
    System.out.println("OrderController.showOrderList()");
    OrdersDao ordersDao = OrdersDao.getInstance();
    List<Orders> list = ordersDao.getOrderByCustomerId(customerId);
    return list;
  }

  @RequestMapping(value = "/updateEndTimeById", method = RequestMethod.PUT)
  public Orders updateEndTimeById(@RequestBody Orders oldOrder,
                                  @RequestParam String endTime) throws SQLException {
    System.out.println("OrderController.updateEndTimeById()");
    OrdersDao ordersDao = OrdersDao.getInstance();
    return ordersDao.updateEndTimeById(oldOrder, endTime);
  }

  @RequestMapping(value = "/updateStatusById", method = RequestMethod.PUT)
  public Orders updateStatusById(@RequestBody Orders oldOrder,
                                 @RequestParam Orders.Status status) throws SQLException {
    System.out.println("OrderController.updateStatusById()");
    OrdersDao ordersDao = OrdersDao.getInstance();
    return ordersDao.updateStatusById(oldOrder, status);
  }
}
