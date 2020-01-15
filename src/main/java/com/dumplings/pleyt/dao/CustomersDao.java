package com.dumplings.pleyt.dao;

import com.dumplings.pleyt.model.Customers;
import com.dumplings.pleyt.model.Users;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersDao {
  protected ConnectionManager connectionManager;
  private static CustomersDao instance = null;

  public CustomersDao() {
    connectionManager = new ConnectionManager();
  }

  public static CustomersDao getInstance() {
    if (instance == null) {
      instance = new CustomersDao();
    }
    return instance;
  }

  public Customers getCustomerById(int customerId) throws SQLException {
    String sql = "select * from Customers where UserId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, customerId);
      results = stmt.executeQuery();

      if (results.next()) {
        UsersDao usersDao = UsersDao.getInstance();
        Users user = usersDao.getUserById(customerId);

        String streetAddress = results.getString("StreetAddress");
        String yelpId = results.getString("YelpId");
        String city = results.getString("City");
        String state = results.getString("State");
        int zip = results.getInt("Zip");
        int reviewCounts = results.getInt("ReviewCounts");
        BigDecimal averageRating = results.getBigDecimal("AverageRating");

        Customers customer = new Customers(user.getUserId(), user.getUserName(), user.getPassword(),
                user.getFirstName(), user.getLastName(), streetAddress, yelpId, city, state, zip,
                reviewCounts, averageRating);

        return customer;
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
