package com.example.rating_service.services;

import java.util.List;

import com.example.rating_service.models.Rating;

public interface RatingService {

  // Create
  Rating createRating(Rating rating);

  // get all ratings
  List<Rating> getAllRatings();

  // get all rating by user
  List<Rating> getAllRatingsByUserId(String userId);

  // get all rating by hotel
  List<Rating> getAllRatingsByHotelId(String hotelId);
}
