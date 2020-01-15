package com.dumplings.pleyt.dao;

import com.dumplings.pleyt.model.CreditCards;
import com.dumplings.pleyt.model.Customers;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCardsDao {

  protected ConnectionManager connectionManager;
  private static CreditCardsDao instance = null;

  public CreditCardsDao() {
    connectionManager = new ConnectionManager();
  }

  public static CreditCardsDao getInstance() {
    if (instance == null) {
      instance = new CreditCardsDao();
    }
    return instance;
  }

  public CreditCards getCreditCardByCustomerId(int customerId) throws SQLException {
    String sql = "select * from CreditCards where UserId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, customerId);
      results = stmt.executeQuery();

      if (results.next()) {
        int cardId = results.getInt("CardId");
        BigInteger cardNumber = results.getBigDecimal("CardNumber").toBigInteger();
        int expirationMonth = results.getInt("ExpirationMonth");
        int expirationYear = results.getInt("ExpirationYear");

        CustomersDao customersDao = CustomersDao.getInstance();
        Customers customer = customersDao.getCustomerById(customerId);
        CreditCards creditCard = new CreditCards(cardId, cardNumber, expirationMonth, expirationYear, customer);
        return creditCard;
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

  public CreditCards getCreditCardById(int cardId) throws SQLException {
    String sql = "select * from CreditCards where CardId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, cardId);
      results = stmt.executeQuery();

      while (results.next()) {
        int resultCardId = results.getInt("CardId");
        BigInteger cardNumber = results.getBigDecimal("CardNumber").toBigInteger();
        int expirationMonth = results.getInt("ExpirationMonth");
        int expirationYear = results.getInt("ExpirationYear");
        int userId = results.getInt("UserId");

        CustomersDao customersDao = CustomersDao.getInstance();
        Customers customer = customersDao.getCustomerById(userId);
        CreditCards creditCard = new CreditCards(resultCardId, cardNumber, expirationMonth,
                expirationYear, customer);
        return creditCard;
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
