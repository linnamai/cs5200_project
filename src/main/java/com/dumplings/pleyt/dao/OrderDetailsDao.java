package com.dumplings.pleyt.dao;

import com.dumplings.pleyt.model.Dishes;
import com.dumplings.pleyt.model.OrderDetails;
import com.dumplings.pleyt.model.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDao {

  protected ConnectionManager connectionManager;
  private static OrderDetailsDao instance = null;

  public OrderDetailsDao() {
    connectionManager = new ConnectionManager();
  }

  public static OrderDetailsDao getInstance() {
    if (instance == null) {
      instance = new OrderDetailsDao();
    }
    return instance;
  }

  public OrderDetails create(OrderDetails orderDetail) throws SQLException {
    String sql = "insert into OrderDetails(OrderId,DishId) values(?,?);";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet resultKey = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, orderDetail.getOrder().getOrderId());
      stmt.setInt(2, orderDetail.getDish().getDishId());
      stmt.executeUpdate();

      resultKey = stmt.getGeneratedKeys();
      int orderDetailId = -1;
      if (resultKey.next()) {
        orderDetailId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      orderDetail.setOrderDetailId(orderDetailId);

      return orderDetail;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (stmt != null) {
        stmt.close();
      }
    }
  }

  public List<OrderDetails> getOrderDetailByOrderId(int orderId) throws SQLException {
    String sql = "select * from OrderDetails where OrderId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;
    List<OrderDetails> orderDetailList = new ArrayList<>();

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, orderId);
      results = stmt.executeQuery();

      while (results.next()) {
        int orderDetailId = results.getInt("OrderDetailId");
        int resultOrderId = results.getInt("OrderId");
        int dishId = results.getInt("DishId");


        OrdersDao ordersDao = OrdersDao.getInstance();
        Orders order = ordersDao.getOrderById(resultOrderId);
        DishesDao dishesDao = DishesDao.getInstance();
        Dishes dish = dishesDao.getDishById(dishId);

        OrderDetails orderDetails = new OrderDetails(orderDetailId, order, dish);
        orderDetailList.add(orderDetails);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (stmt != null) {
        stmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return orderDetailList;
  }

}
