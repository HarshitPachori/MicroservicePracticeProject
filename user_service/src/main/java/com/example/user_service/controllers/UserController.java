package com.example.user_service.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.models.User;
import com.example.user_service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  private Logger logger = LoggerFactory.getLogger(UserService.class);

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User createdUser = userService.createUser(user);
    return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
  }

  int retryCount = 1;

  @GetMapping("/{userId}")
  // @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod =
  // "ratingHotelFallbackMethod")
  // @Retry(name = "ratingHotelServiceRetry", fallbackMethod =
  // "ratingHotelFallbackMethod")
  @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallbackMethod")
  public ResponseEntity<User> getSingleUser(
      @PathVariable String userId) {

    logger.info("retry count : {} ", retryCount);
    retryCount++;

    User user = userService.getUserById(userId);
    return new ResponseEntity<User>(user, HttpStatus.OK);
  }

  public ResponseEntity<User> ratingHotelFallbackMethod(String userId, Exception ex) {
    logger.info("Fallback is executed because service is down : ", ex.getMessage());

    User user = User.builder()
        .email("dummy@gmail.com")
        .name("dummy")
        .about("This user is created dummy because some service is down")
        .build();
    return new ResponseEntity<User>(user, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> allUsers = userService.getAllUsers();
    return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<User> updateUser(
      @PathVariable String userId, @RequestBody User user) {
    User updatedUser = userService.updateUser(user, userId);
    return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<String> deleteUser(@PathVariable String userId) {
    userService.deleteUser(userId);
    return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
  }
}
