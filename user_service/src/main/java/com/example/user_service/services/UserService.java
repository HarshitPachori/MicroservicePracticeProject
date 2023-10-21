package com.example.user_service.services;

import java.util.List;

import com.example.user_service.models.User;

public interface UserService {
  
  User createUser(User user);

  List<User> getAllUsers();

  User getUserById(String userId);

  User updateUser(User user, String userId);

  void deleteUser(String userId);
}
