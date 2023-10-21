package com.example.user_service.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "micro_users")
public class User {

  @Id
  private String userId;

  private String name;
  private String email;
  private String about;

  // transien to not store in the database
  @Transient
  private List<Rating> ratings = new ArrayList<>();
}
