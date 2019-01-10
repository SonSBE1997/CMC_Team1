package com.team1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.team1.entity.User;
import com.team1.service.UserService;
import com.team1.validation.UserValidator;

@Controller
public class UserController {
  @Autowired
  UserService userService;
  @Autowired
  UserValidator userValidator;

  @GetMapping(value = "list-user")
  public String getAllUser(ModelMap map) {
    List<User> users = userService.fillAll();
    map.addAttribute("list", users);
    return "listUser";

  }

  @GetMapping(value = "detail-user/{id}")
  public String getUser(ModelMap map, @PathVariable("id") int id) {
    map.addAttribute("user", userService.findOne(id));
    return "detailUser";

  }

  @GetMapping(value = "delete-user/{id}")
  public String delete(ModelMap map, @PathVariable("id") int id) {
    userService.delete(id);
    return "redirect:/list-user";
  }

  @GetMapping(value = "/edit-user/{id}")
  public String editUser(ModelMap map, @PathVariable("id") String id) {
    map.addAttribute("user", userService.findOne(Integer.parseInt(id)));
    return "editUser";
  }

  @PostMapping(value = "/edit-user")
  public String save(@ModelAttribute(name = "user") User user,
      BindingResult bindingResult) {
    userValidator.validate(user, bindingResult);
    userService.update(user);
    return "redirect:/list-user";
  }

  @GetMapping(value = "/add-user")
  public String addUser(ModelMap map) {
    map.addAttribute("user", new User());
    return "addUser";
  }

  @PostMapping(value = "/add-user")
  public String saveUser(@ModelAttribute("user") User user,
      BindingResult bindingResult) {
    userValidator.validate(user, bindingResult);
    if (bindingResult.hasErrors()) {
      return "addUser";
    }
    userService.addUser(user);
    return "redirect:/list-user";
  }
}
