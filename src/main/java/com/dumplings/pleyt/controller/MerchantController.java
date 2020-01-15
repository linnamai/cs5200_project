package com.dumplings.pleyt.controller;

import com.dumplings.pleyt.dao.MerchantsDao;
import com.dumplings.pleyt.model.Restaurants;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class MerchantController {

  @RequestMapping(value = "/getRestaurantsByMerchantId", method = RequestMethod.GET)
  public Restaurants showRestaurant(@RequestParam int merchantId) throws SQLException {
    System.out.println("MerchantController.showRestaurant()");
    MerchantsDao merchantsDao = MerchantsDao.getInstance();
    Restaurants restaurant = merchantsDao.getRestaurantsByMerchantId(merchantId);
    return restaurant;
  }
}
