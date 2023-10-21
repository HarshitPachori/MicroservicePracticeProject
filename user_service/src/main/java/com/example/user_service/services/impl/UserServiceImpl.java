package com.example.user_service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.user_service.exceptions.ResourceNotFoundException;
import com.example.user_service.external_services.HotelService;
import com.example.user_service.models.Hotel;
import com.example.user_service.models.Rating;
import com.example.user_service.models.User;
import com.example.user_service.repositories.UserRepository;
import com.example.user_service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private HotelService hotelService;

  // private Logger logger = LoggerFactory.getLogger(UserService.class);

  @Override
  public User createUser(User user) {
    String randomUserId = UUID.randomUUID().toString();
    user.setUserId(randomUserId);
    User savedUser = userRepository.save(user);
    return savedUser;
  }

  @Override
  public List<User> getAllUsers() {
    List<User> allUsers = userRepository.findAll();
    allUsers = allUsers.stream().map((userObj) -> {
      Rating[] ratingsOfUser = restTemplate
          .getForObject("http://RATING-SERVICE/ratings/users/" + userObj.getUserId(), Rating[].class);
      List<Rating> ratingsList = Arrays.stream(ratingsOfUser).toList();
      List<Rating> ratings = ratingsList.stream().map((rating) -> {
        ResponseEntity<Hotel> forEntity = restTemplate
            .getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
        Hotel hotel = forEntity.getBody();
        rating.setHotel(hotel);
        return rating;
      }).collect(Collectors.toList());
      userObj.setRatings(ratings);
      return userObj;
    }).toList();
    return allUsers;
  }

  @Override
  public User getUserById(String userId) {
    // get single user by userId from the userRepository
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with userId : " + userId));
    // fetch ratings for above user by calling RATING SERVICE
    Rating[] ratingsOfUser = restTemplate
        .getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
    List<Rating> ratingsList = Arrays.stream(ratingsOfUser).toList();

    List<Rating> ratings = ratingsList.stream().map((rating) -> {
      // ResponseEntity<Hotel> forEntity = restTemplate
      // .getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(),
      // Hotel.class);
      // Hotel hotel = forEntity.getBody();
      Hotel hotel = hotelService.getHotel(rating.getHotelId());
      rating.setHotel(hotel);
      return rating;
    }).collect(Collectors.toList());
    user.setRatings(ratings);
    return user;
  }

  @Override
  public User updateUser(User user, String userId) {
    User fetchedUser = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with userId : " + userId));
    user.setUserId(fetchedUser.getUserId());
    User savedUser = userRepository.save(user);
    return savedUser;
  }

  @Override
  public void deleteUser(String userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with userId : " + userId));
    userRepository.delete(user);
  }

}
