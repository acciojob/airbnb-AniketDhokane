package com.driver.service;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repository.HotelManagementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class HotelManagementService {


    @Autowired
    HotelManagementRepo hotelManagementRepo;
    public String addHotel(Hotel hotel) {

        if(hotelManagementRepo.hotelMap.containsKey(hotel.getHotelName())){
            return "FAILURE";
        }
        else {
            hotelManagementRepo.hotelMap.put(hotel.getHotelName(), hotel);

        }

        return " SUCCESS ";
    }


    public Integer addUser(User user) {

        if(!(hotelManagementRepo.userMap.containsKey((user.getaadharCardNo())))){

            this.hotelManagementRepo.userMap.put(user.getaadharCardNo(), user);
        }

        return user.getaadharCardNo();
    }

    public String getHotelWithMostFacilities() {

        List<Facility> facilities= new ArrayList<>();

        String hotelName=null;

        int max=Integer.MIN_VALUE;
        Set<String > set= this.hotelManagementRepo.hotelMap.keySet();

        for(String h:set){
            Hotel hotel=this.hotelManagementRepo.hotelMap.get(h);
            if(hotel.getFacilities().size()>max){
                max=hotel.getFacilities().size();
                hotelName=hotel.getHotelName();
            }
        }

        return hotelName;

    }

    public int bookRoom(Booking booking) {

        String hotelName= booking.getHotelName();

        Hotel hotel= this.hotelManagementRepo.hotelMap.get(hotelName);

        int availableRoom= hotel.getAvailableRooms();

        if(availableRoom<booking.getNoOfRooms()) {
            return -1;
        }

        this.hotelManagementRepo.bookingMap.put(booking.getBookingId(), booking);


        return booking.getAmountToBePaid();

    }

    public int getBooking(Integer aadharCard) {

        Set<String> set= this.hotelManagementRepo.bookingMap.keySet();

        for(String s: set){

          Booking booking= this.hotelManagementRepo.bookingMap.get(s);

          if(booking.getBookingAadharCard()==aadharCard){
              return booking.getBookingAadharCard();
          }
        }
        return -1;
    }


    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {

        Hotel hotel= this.hotelManagementRepo.hotelMap.get(hotelName);

        List<Facility> facilityList= hotel.getFacilities();

        if(!(newFacilities.equals(facilityList))){
            hotel.setFacilities(newFacilities);
            hotelManagementRepo.hotelMap.put(hotelName,hotel);
        }

        return hotel;
    }
}
