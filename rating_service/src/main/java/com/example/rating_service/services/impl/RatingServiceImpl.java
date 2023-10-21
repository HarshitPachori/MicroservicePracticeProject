package com.example.rating_service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rating_service.models.Rating;
import com.example.rating_service.repository.RatingRepository;
import com.example.rating_service.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

  @Autowired
  private RatingRepository ratingRepository;

  @Override
  public Rating createRating(Rating rating) {
    Rating createdRating = ratingRepository.save(rating);
    return createdRating;
  }

  @Override
  public List<Rating> getAllRatings() {
    List<Rating> allRatings = ratingRepository.findAll();
    return allRatings;
  }

  @Override
  public List<Rating> getAllRatingsByUserId(String userId) {
    List<Rating> allRatingsByUser = ratingRepository.findByUserId(userId);
    return allRatingsByUser;
  }

  @Override
  public List<Rating> getAllRatingsByHotelId(String hotelId) {
    List<Rating> allRatingsByHotel = ratingRepository.findByHotelId(hotelId);
    return allRatingsByHotel;
  }

}
