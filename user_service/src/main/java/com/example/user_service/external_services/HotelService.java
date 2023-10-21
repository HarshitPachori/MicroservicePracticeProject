package com.example.user_service.external_services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.user_service.models.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

  // Feign client works on the declarative approach and used for http req just
  // like the resttemplate

  @GetMapping("/hotels/{hotelId}")
  Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
