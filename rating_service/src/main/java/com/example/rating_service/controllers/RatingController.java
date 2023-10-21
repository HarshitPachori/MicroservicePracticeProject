package com.example.rating_service.controllers;

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

import com.example.rating_service.models.Rating;
import com.example.rating_service.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

  @Autowired
  private RatingService ratingService;

  @PostMapping
  public ResponseEntity<Rating> createRating(
      @RequestBody Rating rating) {
    Rating createdRating = ratingService.createRating(rating);
    return new ResponseEntity<Rating>(createdRating, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Rating>> getAllRatings() {
    List<Rating> allRatings = ratingService.getAllRatings();
    return new ResponseEntity<List<Rating>>(allRatings, HttpStatus.OK);
  }

  @GetMapping("/users/{userId}")
  public ResponseEntity<List<Rating>> getAllRatingsByUser(
      @PathVariable("userId") String userId) {
    List<Rating> allRatingsByUser = ratingService.getAllRatingsByUserId(userId);
    return new ResponseEntity<List<Rating>>(allRatingsByUser, HttpStatus.OK);
  }

  @GetMapping("/hotels/{hotelId}")
  public ResponseEntity<List<Rating>> getAllRatingsByHotel(
      @PathVariable("hotelId") String hotelId) {
    List<Rating> allRatingsByHotel = ratingService.getAllRatingsByHotelId(hotelId);
    return new ResponseEntity<List<Rating>>(allRatingsByHotel, HttpStatus.OK);
  }
}
