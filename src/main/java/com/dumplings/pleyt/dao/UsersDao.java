package com.dumplings.pleyt.dao;

import com.dumplings.pleyt.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDao {

  protected ConnectionManager connectionManager;
  private static UsersDao instance = null;

  public UsersDao() {
    connectionManager = new ConnectionManager();
  }

  public static UsersDao getInstance() {
    if (instance == null) {
      instance = new UsersDao();
    }
    return instance;
  }

  public Users getUserById(int userId) throws SQLException {
    String sql = "select * from Users where UserId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, userId);
      results = stmt.executeQuery();

      if (results.next()) {
        String username = results.getString("UserName");
        int resultUserId = results.getInt("UserId");
        String password = results.getString("Password");
        String fname = results.getString("FirstName");
        String lname = results.getString("LastName");
        Users user = new Users(resultUserId, username, password, fname, lname);
        return user;

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
