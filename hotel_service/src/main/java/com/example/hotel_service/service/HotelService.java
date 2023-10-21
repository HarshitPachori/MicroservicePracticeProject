package com.example.hotel_service.service;

import java.util.List;

import com.example.hotel_service.models.Hotel;

public interface HotelService {

  Hotel createHotel(Hotel hotel);

  Hotel updateHotel(Hotel hotel, String hotelId);

  Hotel getSingleHotel(String hotelId);

  List<Hotel> getAllHotels();

  void deleteHotel(String hotelId);
}
