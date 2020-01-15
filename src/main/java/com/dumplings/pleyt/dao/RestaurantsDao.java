package com.dumplings.pleyt.dao;

import com.dumplings.pleyt.model.Restaurants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class RestaurantsDao {
  protected ConnectionManager connectionManager;
  private static RestaurantsDao instance = null;

  public RestaurantsDao() {
    connectionManager = new ConnectionManager();
  }

  public static RestaurantsDao getInstance() {
    if (instance == null) {
      instance = new RestaurantsDao();
    }
    return instance;
  }

  public List<Restaurants> getRestaurantByCity(String city) throws SQLException {
    String sql = "select Restaurants.Name, avg(Dishes.BasePrice) as AveragePrice, Restaurants.City as City," +
            " Restaurants.RestaurantId, Restaurants.StreetAddress, Restaurants.State, Restaurants.Category, " +
            "Restaurants.Deliverable, Restaurants.DeliveryPrice, Restaurants.Capacity, Restaurants.BusinessId " +
            "from Restaurants left join Dishes on Restaurants.RestaurantId = Dishes.RestaurantId " +
            "where Restaurants.City = ? and Dishes.DishId is not null " +
            "group by Restaurants.RestaurantId " +
            "order by AveragePrice " +
            "limit 5;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;
    List<Restaurants> restaurantList = new ArrayList<>();

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setString(1, city);
      results = stmt.executeQuery();

      while (results.next()) {
        int restaurantId = results.getInt("RestaurantId");
        String restaurantName = results.getString("Name");
        String restaurantBusinessId = results.getString("BusinessId");
        String restaurantStreetAddress = results.getString("StreetAddress");
        String restaurantCity = results.getString("City");
        String restaurantState = results.getString("State");
        String restaurantCategory = results.getString("Category");
        Boolean restaurantDeliverable = results.getBoolean("Deliverable");
        double restaurantDeliveryPrice = results.getDouble("DeliveryPrice");
        int restaurantCapacity = results.getInt("Capacity");

        Restaurants restaurant = new Restaurants(restaurantId, restaurantName, restaurantBusinessId,
                restaurantStreetAddress, restaurantCity, restaurantState, restaurantCategory,
                restaurantDeliverable, restaurantDeliveryPrice, restaurantCapacity);
        restaurantList.add(restaurant);
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
    return restaurantList;
  }

  public Restaurants getRestaurantById(int restaurantId) throws SQLException {
    String sql = "select * from Restaurants where RestaurantId = ?;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, restaurantId);
      results = stmt.executeQuery();

      if (results.next()) {
        int resultrestaurantId = results.getInt("RestaurantId");
        String restaurantName = results.getString("Name");
        String restaurantBusinessId = results.getString("BusinessId");
        String restaurantStreetAddress = results.getString("StreetAddress");
        String restaurantCity = results.getString("City");
        String restaurantState = results.getString("State");
        String restaurantCategory = results.getString("Category");
        Boolean restaurantDeliverable = results.getBoolean("Deliverable");
        double restaurantDeliveryPrice = results.getDouble("DeliveryPrice");
        int restaurantCapacity = results.getInt("Capacity");

        Restaurants restaurant = new Restaurants(resultrestaurantId, restaurantName, restaurantBusinessId,
                restaurantStreetAddress, restaurantCity, restaurantState, restaurantCategory,
                restaurantDeliverable, restaurantDeliveryPrice, restaurantCapacity);
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

  public HashSet<Integer> getRestaurantIdListByDishName(String dishName) throws SQLException {
//    String sql = "select d.DishName,d.BasePrice,r.Name,r.RestaurantId" +
//            "from Dishes as d " +
//            "left join Restaurants as r on r.RestaurantId = d.RestaurantId " +
//            "where DishName like '%?%';";
    // simple version sql
    String sql = "select Restaurants.RestaurantId \n" +
            "from Dishes \n" +
            "left join Restaurants on Restaurants.RestaurantId = Dishes.RestaurantId \n" +
            "where Dishes.DishName like ? and Restaurants.City = 'boston';";

    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;
    HashSet<Integer> restaurantIdSet = new HashSet<>();

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setString(1, "%"+dishName+"%");
      results = stmt.executeQuery();

      while (results.next()) {
        int restaurantId = results.getInt("RestaurantId");
        restaurantIdSet.add(restaurantId);
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
    return restaurantIdSet;
  }

}

