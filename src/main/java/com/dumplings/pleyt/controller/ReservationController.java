package com.dumplings.pleyt.controller;

import com.dumplings.pleyt.dao.ReservationsDao;
import com.dumplings.pleyt.model.Reservations;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class ReservationController {

  @RequestMapping(value="/getReservationsByCustomerId", method = RequestMethod.GET)
  public List<Reservations> showReservationList(@RequestParam int customerId) throws SQLException {
    System.out.println("ReservationController.showReservationList()");
    ReservationsDao reservationsDao = ReservationsDao.getInstance();
    List<Reservations> list = reservationsDao.getReservationsByCustomerId(customerId);

    return list;
  }

  @RequestMapping(value="/createReservation", method = RequestMethod.POST)
  public Reservations createReservation(@RequestBody Reservations reservation) throws SQLException {
    System.out.println("ReservationController.createReservation()");
    ReservationsDao reservationsDao = ReservationsDao.getInstance();
    return reservationsDao.create(reservation);
  }

  @RequestMapping(value="/updateReservationPartySize", method = RequestMethod.PUT)
  public Reservations updateReservationPartySize(@RequestBody Reservations oldReservation,
                                                 @RequestParam int partySize) throws SQLException {
    System.out.println("ReservationController.updateReservationPartySize()");
    ReservationsDao reservationsDao = ReservationsDao.getInstance();
    return reservationsDao.updatePartySizeById(oldReservation,partySize);
  }

  @RequestMapping(value="/updateReservationStartTime", method = RequestMethod.PUT)
  public Reservations updateReservationStartTime(@RequestBody Reservations oldReservation,
                                                 @RequestParam String startTime) throws SQLException {
    System.out.println("ReservationController.updateReservationStartTime()");
    ReservationsDao reservationsDao = ReservationsDao.getInstance();
    return reservationsDao.updateStartTimeById(oldReservation,startTime);
  }

  @RequestMapping(value="/updateReservationEndTime", method = RequestMethod.PUT)
  public Reservations updateReservationEndTime(@RequestBody Reservations oldReservation,
                                               @RequestParam String endTime) throws SQLException {
    System.out.println("ReservationController.updateReservationEndTime()");
    ReservationsDao reservationsDao = ReservationsDao.getInstance();
    return reservationsDao.updateEndTimeById(oldReservation,endTime);
  }

  @RequestMapping(value="/updateReservationStatus", method = RequestMethod.PUT)
  public Reservations updateReservationStatus(@RequestBody Reservations oldReservation,
                                               @RequestParam Reservations.Status status) throws SQLException {
    System.out.println("ReservationController.updateReservationStatus()");
    ReservationsDao reservationsDao = ReservationsDao.getInstance();
    return reservationsDao.updateStatusById(oldReservation,status);
  }

  @RequestMapping(value="/updateReservation", method = RequestMethod.POST)
  public Reservations updateReservationStartTime(@RequestBody Reservations newReservation) throws SQLException {
    System.out.println("ReservationController.updateReservation()");

    ReservationsDao reservationsDao = ReservationsDao.getInstance();

    return reservationsDao.updateReservation(newReservation);
  }

  @RequestMapping(value = "/deleteReservation", method = RequestMethod.GET)
  public Reservations deleteReservation(@RequestParam Integer reservationId) throws SQLException {
    System.out.println("ReservationController.deleteReservation()");
    ReservationsDao reservationsDao = ReservationsDao.getInstance();
    Reservations reserv = reservationsDao.getReservationsById(reservationId);
    Reservations reserv1 = reservationsDao.delete(reserv);
    
    return reserv1;
  }

}
