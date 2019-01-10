package com.team1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team1.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
