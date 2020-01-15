package com.dumplings.pleyt.dao;

import com.dumplings.pleyt.model.CreditCards;
import com.dumplings.pleyt.model.Customers;
import com.dumplings.pleyt.model.Orders;
import com.dumplings.pleyt.model.Restaurants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrdersDao {

  protected ConnectionManager connectionManager;
  private static OrdersDao instance = null;

  public OrdersDao() {
    connectionManager = new ConnectionManager();
  }

  public static OrdersDao getInstance() {
    if (instance == null) {
      instance = new OrdersDao();
    }
    return instance;
  }

  public Orders create(Orders order) throws SQLException {
    String sql = "insert into Orders(UserId,RestaurantId,OrderPrice,Status,CreateTime," +
            "CardId,EndTime) values(?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet resultKey = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, order.getCustomer().getUserId());
      stmt.setInt(2, order.getRestaurant().getRestaurantId());
      stmt.setDouble(3, order.getOrderPrice());
      stmt.setString(4, order.getStatus());
      stmt.setTimestamp(5, Timestamp.valueOf(order.getCreateTime()));
      stmt.setInt(6, order.getCreditCard().getCardId());
      stmt.setTimestamp(7, Timestamp.valueOf(order.getEndTime()));
      stmt.executeUpdate();

      resultKey = stmt.getGeneratedKeys();
      int orderId = -1;
      if (resultKey.next()) {
        orderId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      order.setOrderId(orderId);

      return order;
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

  public List<Orders> getOrderByCustomerId(int customerId) throws SQLException {
    String sql = "select * from Orders where UserId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;
    List<Orders> orderList = new ArrayList<>();

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, customerId);
      results = stmt.executeQuery();

      while (results.next()) {
        int orderId = results.getInt("OrderId");
        int userId = results.getInt("UserId");
        int restaurantId = results.getInt("RestaurantId");
        double orderPrice = results.getDouble("OrderPrice");
        String start = results.getString("CreateTime");
        int cardId = results.getInt("CardId");
        String end = results.getString("EndTime");
        Orders.Status status
                = Orders.Status.valueOf(results.getString("Status"));

        CustomersDao customersDao = CustomersDao.getInstance();
        Customers customer = customersDao.getCustomerById(userId);
        RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
        Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
        CreditCardsDao creditCardsDao = CreditCardsDao.getInstance();
        CreditCards creditCard = creditCardsDao.getCreditCardById(cardId);
        Orders order = new Orders(orderId, customer, restaurant,
                orderPrice, status, start, creditCard, end);
        orderList.add(order);
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
    return orderList;
  }

  public List<Orders> getOrdersByRestaurantId(int restaurantId) throws SQLException {
    String sql = "select * from Orders where RestaurantId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;
    List<Orders> orderList = new ArrayList<>();

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, restaurantId);
      results = stmt.executeQuery();

      while (results.next()) {
        int orderId = results.getInt("OrderId");
        int userId = results.getInt("UserId");
        int theRestaurantId = results.getInt("RestaurantId");
        double orderPrice = results.getDouble("OrderPrice");
        String start = results.getString("CreateTime");
        int cardId = results.getInt("CardId");
        String end = results.getString("EndTime");
        Orders.Status status
                = Orders.Status.valueOf(results.getString("Status"));

        CustomersDao customersDao = CustomersDao.getInstance();
        Customers customer = customersDao.getCustomerById(userId);
        RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
        Restaurants restaurant = restaurantsDao.getRestaurantById(theRestaurantId);
        CreditCardsDao creditCardsDao = CreditCardsDao.getInstance();
        CreditCards creditCard = creditCardsDao.getCreditCardById(cardId);
        Orders order = new Orders(orderId, customer, restaurant,
                orderPrice, status, start, creditCard, end);
        orderList.add(order);
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
    return orderList;
  }

  public Orders getOrderById(int orderId) throws SQLException {
    String sql = "select * from Orders where OrderId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, orderId);
      results = stmt.executeQuery();

      if (results.next()) {
        int resultOrderId = results.getInt("OrderId");
        int userId = results.getInt("UserId");
        int restaurantId = results.getInt("RestaurantId");
        double orderPrice = results.getDouble("OrderPrice");
        String start = results.getString("CreateTime");
        int cardId = results.getInt("CardId");
        String end = results.getString("EndTime");
        Orders.Status status
                = Orders.Status.valueOf(results.getString("Status"));

        CustomersDao customersDao = CustomersDao.getInstance();
        Customers customer = customersDao.getCustomerById(userId);
        RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
        Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
        CreditCardsDao creditCardsDao = CreditCardsDao.getInstance();
        CreditCards creditCard = creditCardsDao.getCreditCardById(cardId);

        Orders order = new Orders(resultOrderId, customer, restaurant, orderPrice, status, start,
                creditCard, end);
        return order;
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
    return null;
  }

  public Orders updateEndTimeById(Orders oldOrder, String endTime) throws SQLException {
    String sql = "update Orders set EndTime=? where OrderId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);


      stmt.setTimestamp(1, Timestamp.valueOf(endTime));
      stmt.setInt(2, oldOrder.getOrderId());
      stmt.executeUpdate();

      oldOrder.setEndTime(endTime);
      return oldOrder;
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

  public Orders updateStatusById(Orders oldOrder, Orders.Status status) throws SQLException {
    String sql = "update Orders set Status=? where OrderId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);


      stmt.setString(1, status.name());
      stmt.setInt(2, oldOrder.getOrderId());
      stmt.executeUpdate();

      oldOrder.setStatus(status);
      return oldOrder;
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


}
