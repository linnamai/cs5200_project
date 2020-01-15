package com.dumplings.pleyt.dao;

import com.dumplings.pleyt.model.Customers;
import com.dumplings.pleyt.model.Merchants;
import com.dumplings.pleyt.model.Reservations;
import com.dumplings.pleyt.model.Restaurants;
import com.dumplings.pleyt.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MerchantsDao {

  protected ConnectionManager connectionManager;
  private static MerchantsDao instance = null;

  public MerchantsDao() {
    connectionManager = new ConnectionManager();
  }

  public static MerchantsDao getInstance() {
    if (instance == null) {
      instance = new MerchantsDao();
    }
    return instance;
  }

  public Merchants getMerchantById(int merchantId) throws SQLException {
    String sql = "select * from Merchants where UserId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, merchantId);
      results = stmt.executeQuery();

      if (results.next()) {
        UsersDao usersDao = UsersDao.getInstance();
        Users user = usersDao.getUserById(merchantId);

        int restaurantId = results.getInt("RestaurantId");
        RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
        Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);

        Merchants merchant = new Merchants(user.getUserId(), user.getUserName(), user.getPassword(),
                user.getFirstName(), user.getLastName(), restaurant);

        return merchant;
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

  public Restaurants getRestaurantsByMerchantId(int merchantId) throws SQLException {
    String sql = "select * from Merchants where UserId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, merchantId);
      results = stmt.executeQuery();

      if (results.next()) {
        int restaurantId = results.getInt("RestaurantId");

        RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
        Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
        return restaurant;
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
}
