package com.proenca.flightsmanagement.dao;

import com.proenca.flightsmanagement.domain.Passenger;

public interface PassengerDao {
    Passenger getPassenger(int id);
    void insertPassenger(Passenger passenger);
}
