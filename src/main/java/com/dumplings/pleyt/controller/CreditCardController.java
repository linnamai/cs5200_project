package com.dumplings.pleyt.controller;

import com.dumplings.pleyt.dao.CreditCardsDao;
import com.dumplings.pleyt.model.CreditCards;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class CreditCardController {

  @RequestMapping(value = "/getCreditCardByCustomerId", method = RequestMethod.GET)
  public CreditCards showCreditCardByCustomerId(@RequestParam int customerId) throws SQLException {
    System.out.println("CreditCardController.showCreditCardByCustomerId()");
    CreditCardsDao creditsCardDao = CreditCardsDao.getInstance();
    CreditCards creditCard = creditsCardDao.getCreditCardByCustomerId(customerId);
    return creditCard;
  }
}
