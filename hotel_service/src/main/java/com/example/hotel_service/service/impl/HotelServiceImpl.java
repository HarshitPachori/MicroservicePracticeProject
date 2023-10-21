package com.example.hotel_service.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel_service.exception.ResourceNotFoundException;
import com.example.hotel_service.models.Hotel;
import com.example.hotel_service.repository.HotelRepository;
import com.example.hotel_service.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

  @Autowired
  private HotelRepository hotelRepository;

  @Override
  public Hotel createHotel(Hotel hotel) {
    String hotelID = UUID.randomUUID().toString();
    hotel.setHotelId(hotelID);
    Hotel createdHotel = hotelRepository.save(hotel);
    return createdHotel;
  }

  @Override
  public Hotel updateHotel(Hotel hotel, String hotelId) {
    Hotel fetchedHotel = hotelRepository.findById(hotelId)
        .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with this hotelId"));
    hotel.setHotelId(fetchedHotel.getHotelId());
    Hotel updatedHotel = hotelRepository.save(hotel);
    return updatedHotel;
  }

  @Override
  public Hotel getSingleHotel(String hotelId) {
    Hotel fetchedHotel = hotelRepository.findById(hotelId)
        .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with this hotelId"));
    return fetchedHotel;
  }

  @Override
  public List<Hotel> getAllHotels() {
    List<Hotel> fetchedHotels = hotelRepository.findAll();
    return fetchedHotels;
  }

  @Override
  public void deleteHotel(String hotelId) {
    Hotel fetchedHotel = hotelRepository.findById(hotelId)
        .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with this hotelId"));
    hotelRepository.delete(fetchedHotel);
  }

}
