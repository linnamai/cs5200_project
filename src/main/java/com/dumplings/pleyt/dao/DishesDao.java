package com.dumplings.pleyt.dao;


import com.dumplings.pleyt.model.Dishes;
import com.dumplings.pleyt.model.Restaurants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishesDao {

  protected ConnectionManager connectionManager;
  private static DishesDao instance = null;

  public DishesDao() {
    connectionManager = new ConnectionManager();
  }

  public static DishesDao getInstance() {
    if (instance == null) {
      instance = new DishesDao();
    }
    return instance;
  }

  public List<Dishes> getDishesByRestaurantId(int restaurantId) throws SQLException {
    String sql = "select * from Dishes where RestaurantId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;
    List<Dishes> dishList = new ArrayList<>();

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, restaurantId);
      results = stmt.executeQuery();

      while (results.next()) {
        int dishId = results.getInt("DishId");
        String dishName = results.getString("DishName");
        String dishType = results.getString("DishType");
        String description = results.getString("Description");
        double basePrice = results.getDouble("BasePrice");

        RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
        Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
        Dishes dish = new Dishes(dishId, dishName, dishType,
                description, basePrice, restaurant);
        dishList.add(dish);
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
    return dishList;
  }

  public List<Dishes> getDishesByDishName(String dishName) throws SQLException {
    String sql = "select * from Dishes where DishName=?;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;
    List<Dishes> dishList = new ArrayList<>();

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setString(1, dishName);
      results = stmt.executeQuery();

      while (results.next()) {
        int dishId = results.getInt("DishId");
        String dishType = results.getString("DishType");
        String description = results.getString("Description");
        double basePrice = results.getDouble("BasePrice");
        int restaurantId = results.getInt("RestaurantId");

        RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
        Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);

        Dishes dish = new Dishes(dishId, dishName, dishType,
                description, basePrice, restaurant);
        dishList.add(dish);
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
    return dishList;
  }



  public Dishes getDishById(int dishId) throws SQLException {
    String sql = "select * from Dishes where DishId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, dishId);
      results = stmt.executeQuery();

      if (results.next()) {
        int resultDishId = results.getInt("DishId");
        String dishName = results.getString("DishName");
        String dishType = results.getString("DishType");
        String description = results.getString("Description");
        double basePrice = results.getDouble("BasePrice");
        int restaurantId = results.getInt("RestaurantId");

        RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
        Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);

        Dishes dish = new Dishes(resultDishId, dishName, dishType,
                description, basePrice, restaurant);
        return dish;
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
