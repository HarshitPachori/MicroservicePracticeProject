package com.example.rating_service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.rating_service.models.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {

  // custom finder methods
  List<Rating> findByUserId(String userId);

  List<Rating> findByHotelId(String hotelId);
}
