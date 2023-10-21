package com.example.user_service.external_services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.user_service.models.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

  // GET

  // POST

  @PostMapping("/ratings")
  public Rating createRating(Rating rating);

  // PUT

  // @PutMapping("/ratings/{ratingId}")
  // public Rating updateRating(Rating rating , @PathVariable("ratingId") String
  // ratingId);

  // DELETE
  // @DeleteMapping("/ratings/{ratingId}")
  // public void deleteRating(@PathVariable("ratingId") String ratingId);
}
