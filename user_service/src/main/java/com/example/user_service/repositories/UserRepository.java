package com.example.user_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user_service.models.User;

public interface UserRepository extends JpaRepository<User, String> {

}
