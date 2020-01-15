package com.dumplings.pleyt.controller;


import com.dumplings.pleyt.dao.RestaurantsDao;
import com.dumplings.pleyt.model.*;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@RestController
public class RestaurantController {

  @RequestMapping(value = "/RestaurantList", method = RequestMethod.GET)
  public List<Restaurants> showRestaurantList(@RequestParam String dishName) throws SQLException {
    System.out.println("RestaurantController.showRestaurantList()");
    RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
    HashSet<Integer> restaurantIdSet = restaurantsDao.getRestaurantIdListByDishName(dishName);

    List<Restaurants> list = new ArrayList<>();
    for (int id:restaurantIdSet) {
      Restaurants rest = restaurantsDao.getRestaurantById(id);
      list.add(rest);
    }

    return list;
  }


}
