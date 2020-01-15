package com.dumplings.pleyt.controller;

import com.dumplings.pleyt.dao.ReviewsDao;
import com.dumplings.pleyt.model.Reviews;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@RestController
public class ReviewController {

  @RequestMapping(value = "/getReviewsByCustomerId", method = RequestMethod.GET)
  public List<Reviews> showReviewList(@RequestParam int customerId) throws SQLException {
    System.out.println("ReviewController.showReviewList()");
    ReviewsDao reviewsDao = ReviewsDao.getInstance();
    List<Reviews> list = reviewsDao.getReviewsByCustomerId(customerId);
    return list;
  }

  @RequestMapping(value = "/getReviewsByRestaurantId", method = RequestMethod.GET)
  public List<Reviews> showReviewListForMerchant(@RequestParam int restaurantId) throws SQLException {
    System.out.println("ReviewController.showReviewListForMerchant()");
    ReviewsDao reviewsDao = ReviewsDao.getInstance();
    List<Reviews> list = reviewsDao.getReviewsByRestaurantId(restaurantId);
    return list;
  }



  @RequestMapping(value = "/createReview", method = RequestMethod.POST)
  public Reviews createReview(@RequestBody Reviews review) throws SQLException {
    System.out.println("ReviewController.createReview()");
    ReviewsDao reviewsDao = ReviewsDao.getInstance();
    return reviewsDao.create(review);
  }

  @RequestMapping(value = "/updateReservationRating", method = RequestMethod.PUT)
  public Reviews updateReservationRating(@RequestBody Reviews oldReview,
                                         @RequestParam BigDecimal rating) throws SQLException {
    System.out.println("ReviewController.updateReservationRating()");
    ReviewsDao reviewsDao = ReviewsDao.getInstance();
    return reviewsDao.updateRatingById(oldReview, rating);
  }

  @RequestMapping(value = "/updateReservationContent", method = RequestMethod.PUT)
  public Reviews updateReservationContent(@RequestBody Reviews oldReview,
                                          @RequestParam String content) throws SQLException {
    System.out.println("ReviewController.updateReservationContent()");
    ReviewsDao reviewsDao = ReviewsDao.getInstance();
    return reviewsDao.updateContentById(oldReview, content);
  }

  @RequestMapping(value = "/deleteReview", method = RequestMethod.DELETE)
  public Reviews deleteReview(@RequestBody Reviews review) throws SQLException {
    System.out.println("ReviewController.deleteReview()");
    ReviewsDao reviewsDao = ReviewsDao.getInstance();
    return reviewsDao.delete(review);
  }
}
