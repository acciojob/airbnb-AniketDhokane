package com.driver.repository;

import com.driver.model.Booking;
import com.driver.model.Hotel;
import com.driver.model.User;

import java.util.HashMap;
import java.util.Map;

public interface HotelManagementRepo {

    HashMap<String, Hotel> hotelMap= new HashMap<>();

    HashMap<Integer, User> userMap= new HashMap<>();

    HashMap<String, Booking> bookingMap= new HashMap<>();


}
