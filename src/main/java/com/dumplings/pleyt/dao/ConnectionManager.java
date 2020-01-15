package com.dumplings.pleyt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

  private final String user = "root";
  private final String password = "admin";
  private final String hostName = "localhost";
  private final int port = 3306;
  private final String schema = "finalProject";


  public Connection getConnection() throws SQLException {
    Connection connection = null;
    try {
      Properties connectionProperties = new Properties();
      connectionProperties.put("user", this.user);
      connectionProperties.put("password", this.password);
      // Ensure the JDBC driver is loaded by retrieving the runtime Class descriptor.
      // Otherwise, Tomcat may have issues loading libraries in the proper order.
      // One alternative is calling this in the HttpServlet init() override.
      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
        throw new SQLException(e);
      }
      connection = DriverManager.getConnection(
              "jdbc:mysql://" + this.hostName + ":" + this.port + "/" + this.schema + "?useSSL=false" +
                      "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EST5EDT",
              connectionProperties);
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
    return connection;
  }

  public void closeConnection(Connection connection) throws SQLException {
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }
}
