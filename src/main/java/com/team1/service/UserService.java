package com.team1.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team1.entity.User;
import com.team1.repository.UserRepository;

@Service
@Transactional
public class UserService {
  @Autowired
  UserRepository userRepository;
  @Autowired
  private EntityManager entityManager;

  public List<User> fillAll() {
    return userRepository.findAll();
  }

  public void delete(int id) {
    userRepository.deleteById(id);
  }

  public User findOne(int id) {
    return entityManager.find(User.class, id);
  }

  public void update(User user) {
    entityManager.merge(user);
  }

  public void addUser(User user) {
    entityManager.persist(user);
  }
}
