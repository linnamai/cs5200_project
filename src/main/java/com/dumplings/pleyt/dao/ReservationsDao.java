package com.dumplings.pleyt.dao;

import com.dumplings.pleyt.model.Customers;
import com.dumplings.pleyt.model.Reservations;
import com.dumplings.pleyt.model.Restaurants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReservationsDao {
  protected ConnectionManager connectionManager;
  private static ReservationsDao instance = null;

  public ReservationsDao() {
    connectionManager = new ConnectionManager();
  }

  public static ReservationsDao getInstance() {
    if (instance == null) {
      instance = new ReservationsDao();
    }
    return instance;
  }

  public Reservations create(Reservations reservation) throws SQLException {
    String sql = "insert into Reservations(UserId,RestaurantId,PartySize,StartTime,EndTime,Status) values(?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet resultKey = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, reservation.getCustomer().getUserId());
      stmt.setInt(2, reservation.getRestaurant().getRestaurantId());
      stmt.setInt(3, reservation.getPartySize());
      stmt.setTimestamp(4, Timestamp.valueOf(reservation.getStartTime()));
      stmt.setTimestamp(5, Timestamp.valueOf(reservation.getEndTime()));
      stmt.setString(6, reservation.getStatus());
      stmt.executeUpdate();

      resultKey = stmt.getGeneratedKeys();
      int reservationId = -1;
      if (resultKey.next()) {
        reservationId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      reservation.setReservationId(reservationId);

      return reservation;
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

  public Reservations delete(Reservations reservation) throws SQLException {
    String sql = "delete from Reservations where ReservationId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, reservation.getReservationId());
      int affectedRows = stmt.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException("No records available to delete for RestaurantId="
                + reservation.getReservationId());
      }

      return reservation;
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

  public List<Reservations> getReservationsByCustomerId(int customerId) throws SQLException {
    String sql = "select * from Reservations where UserId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;
    List<Reservations> reservationList = new ArrayList<>();

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, customerId);
      results = stmt.executeQuery();

      while (results.next()) {
        int reservationId = results.getInt("ReservationId");
        int restaurantId = results.getInt("RestaurantId");
        int partySize = results.getInt("PartySize");
        String start = results.getString("StartTime");
        String end = results.getString("EndTime");
        Reservations.Status status
                = Reservations.Status.valueOf(results.getString("Status"));

        CustomersDao customersDao = CustomersDao.getInstance();
        Customers customer = customersDao.getCustomerById(customerId);
        RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
        Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
        Reservations reservation = new Reservations(reservationId, customer, restaurant,
                partySize, start, end, status);
        reservationList.add(reservation);
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
    return reservationList;
  }

  public Reservations getReservationsById(int reservationId) throws SQLException {
    String sql = "select * from Reservations where ReservationId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;
    Reservations reservation = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, reservationId);
      results = stmt.executeQuery();

      if (results.next()) {
        int restaurantId = results.getInt("RestaurantId");
        int customerId = results.getInt("UserId");
        int partySize = results.getInt("PartySize");
        String start = results.getString("StartTime");
        String end = results.getString("EndTime");
        Reservations.Status status
                = Reservations.Status.valueOf(results.getString("Status"));

        CustomersDao customersDao = CustomersDao.getInstance();
        Customers customer = customersDao.getCustomerById(customerId);
        RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
        Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
        reservation = new Reservations(reservationId, customer, restaurant,
                partySize, start, end, status);
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
    return reservation;
  }

  public Reservations updateReservation(Reservations newReservation) throws SQLException {
    String sql = "update Reservations set PartySize=?,StartTime=?,EndTime=? where ReservationId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);

      stmt.setInt(1, newReservation.getPartySize());
      stmt.setTimestamp(2, Timestamp.valueOf(newReservation.getStartTime()));
      stmt.setTimestamp(3, Timestamp.valueOf(newReservation.getEndTime()));
      stmt.setInt(4, newReservation.getReservationId());
      stmt.executeUpdate();

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
    return newReservation;
  }


  public Reservations updatePartySizeById(Reservations oldReservation, int partySize) throws SQLException {
    String sql = "update Reservations set PartySize=? where ReservationId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);


      stmt.setInt(1, partySize);
      stmt.setInt(2, oldReservation.getReservationId());
      stmt.executeUpdate();

      oldReservation.setPartySize(partySize);
      return oldReservation;
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

  public Reservations updateStartTimeById(Reservations oldReservation, String startTime) throws SQLException {
    String sql = "update Reservations set StartTime=? where ReservationId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);


      stmt.setTimestamp(1, Timestamp.valueOf(startTime));
      stmt.setInt(2, oldReservation.getReservationId());
      stmt.executeUpdate();

      oldReservation.setStartTime(startTime);
      return oldReservation;
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

  public Reservations updateEndTimeById(Reservations oldReservation, String endTime) throws SQLException {
    String sql = "update Reservations set EndTime=? where ReservationId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);


      stmt.setTimestamp(1, Timestamp.valueOf(endTime));
      stmt.setInt(2, oldReservation.getReservationId());
      stmt.executeUpdate();

      oldReservation.setEndTime(endTime);
      return oldReservation;
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

  public Reservations updateStatusById(Reservations oldReservation, Reservations.Status status) throws SQLException {
    String sql = "update Reservations set Status=? where ReservationId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);


      stmt.setString(1, status.name());
      stmt.setInt(2, oldReservation.getReservationId());
      stmt.executeUpdate();

      oldReservation.setStatus(status);
      return oldReservation;
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
