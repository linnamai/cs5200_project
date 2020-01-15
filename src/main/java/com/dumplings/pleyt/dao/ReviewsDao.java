package com.dumplings.pleyt.dao;

import com.dumplings.pleyt.model.Customers;
import com.dumplings.pleyt.model.Restaurants;
import com.dumplings.pleyt.model.Reviews;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReviewsDao {

  protected ConnectionManager connectionManager;
  private static ReviewsDao instance = null;

  public ReviewsDao() {
    connectionManager = new ConnectionManager();
  }

  public static ReviewsDao getInstance() {
    if (instance == null) {
      instance = new ReviewsDao();
    }
    return instance;
  }

  public Reviews create(Reviews review) throws SQLException {
    String sql = "insert into Reviews(UserId,RestaurantId,Rating,Content,CreateDate) values(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet resultKey = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, review.getCustomer().getUserId());
      stmt.setInt(2, review.getRestaurant().getRestaurantId());
      stmt.setBigDecimal(3, review.getRating());
      stmt.setString(4, review.getContent());
      stmt.setTimestamp(5, Timestamp.valueOf(review.getCreateDate()));
      stmt.executeUpdate();

      resultKey = stmt.getGeneratedKeys();
      int reviewId = -1;
      if (resultKey.next()) {
        reviewId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      review.setReviewId(reviewId);

      return review;
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

  public Reviews delete(Reviews review) throws SQLException {
    String sql = "delete from Reviews where ReviewId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, review.getReviewId());
      int affectedRows = stmt.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException("No records available to delete for RestaurantId="
                + review.getReviewId());
      }

      return null;
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

  public List<Reviews> getReviewsByCustomerId(int customerId) throws SQLException {
    String sql = "select * from Reviews where UserId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;
    List<Reviews> reviewList = new ArrayList<>();

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, customerId);
      results = stmt.executeQuery();

      while (results.next()) {
        int reviewId = results.getInt("ReviewId");
        int userId = results.getInt("UserId");
        int restaurantId = results.getInt("RestaurantId");
        BigDecimal rating = results.getBigDecimal("Rating");
        String content = results.getString("Content");
        String createDate = results.getString("CreateDate");


        CustomersDao customersDao = CustomersDao.getInstance();
        Customers customer = customersDao.getCustomerById(userId);
        RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
        Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
        Reviews review = new Reviews(reviewId, customer, restaurant,
                rating, content, createDate);
        reviewList.add(review);
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
    return reviewList;
  }

  public List<Reviews> getReviewsByRestaurantId(int restaurantId) throws SQLException {
    String sql = "select * from Reviews where RestaurantId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet results = null;
    List<Reviews> reviewList = new ArrayList<>();

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);
      stmt.setInt(1, restaurantId);
      results = stmt.executeQuery();

      while (results.next()) {
        int reviewId = results.getInt("ReviewId");
        int userId = results.getInt("UserId");
        int theRestaurantId = results.getInt("RestaurantId");
        BigDecimal rating = results.getBigDecimal("Rating");
        String content = results.getString("Content");
        String createDate = results.getString("CreateDate");


        CustomersDao customersDao = CustomersDao.getInstance();
        Customers customer = customersDao.getCustomerById(userId);
        RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
        Restaurants restaurant = restaurantsDao.getRestaurantById(theRestaurantId);
        Reviews review = new Reviews(reviewId, customer, restaurant,
                rating, content, createDate);
        reviewList.add(review);
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
    return reviewList;
  }

  public Reviews updateRatingById(Reviews oldReview, BigDecimal rating) throws SQLException {
    String sql = "update Reviews set Rating=? where ReviewId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);


      stmt.setBigDecimal(1, rating);
      stmt.setInt(2, oldReview.getReviewId());
      stmt.executeUpdate();

      oldReview.setRating(rating);
      return oldReview;
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

  public Reviews updateContentById(Reviews oldReview, String content) throws SQLException {
    String sql = "update Reviews set Content=? where ReviewId=?;";
    Connection connection = null;
    PreparedStatement stmt = null;

    try {
      connection = connectionManager.getConnection();
      stmt = connection.prepareStatement(sql);


      stmt.setString(1, content);
      stmt.setInt(2, oldReview.getReviewId());
      stmt.executeUpdate();

      oldReview.setContent(content);
      return oldReview;
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
