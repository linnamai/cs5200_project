package com.dumplings.pleyt.controller;

import com.dumplings.pleyt.dao.DishesDao;
import com.dumplings.pleyt.model.Dishes;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class DishController {

  @RequestMapping(value = "/getDishesByRestaurantId", method = RequestMethod.GET)
  public List<Dishes> showDishListByRestaurantId(@RequestParam int restaurantId) throws SQLException {
    System.out.println("DishController.showDishListByRestaurantId()");
    DishesDao dishesDao = DishesDao.getInstance();
    List<Dishes> list = dishesDao.getDishesByRestaurantId(restaurantId);

    return list;
  }

  @RequestMapping(value = "/getDishesByDishName", method = RequestMethod.GET)
  public List<Dishes> showDishListByDishName(@RequestParam String dishName) throws SQLException {
    System.out.println("DishController.showDishListByDishName()");
    DishesDao dishesDao = DishesDao.getInstance();
    List<Dishes> list = dishesDao.getDishesByDishName(dishName);

    return list;
  }
}
