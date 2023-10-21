package com.example.hotel_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel_service.models.Hotel;
import com.example.hotel_service.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

  @Autowired
  private HotelService hotelService;

  @PostMapping
  public ResponseEntity<Hotel> createHotel(
      @RequestBody Hotel hotel) {
    Hotel createdHotel = hotelService.createHotel(hotel);
    // return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
    return new ResponseEntity<Hotel>(createdHotel, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Hotel>> getAllHotels() {
    List<Hotel> allHotels = hotelService.getAllHotels();
    return new ResponseEntity<List<Hotel>>(allHotels, HttpStatus.OK);
  }

  @GetMapping("/{hotelId}")
  public ResponseEntity<Hotel> getSingleHotel(
      @PathVariable String hotelId) {
    Hotel singleHotel = hotelService.getSingleHotel(hotelId);
    return new ResponseEntity<Hotel>(singleHotel, HttpStatus.OK);
  }

}
