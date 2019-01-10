/**
 * Project name: Day4
 * Package name: com.team1.controller
 * File name: LoginController.java
 * Author: Sanero.
 * Created date: Jan 10, 2019
 * Created time: 8:56:58 AM
 */

package com.team1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.team1.entity.User;
import com.team1.service.UserService;

/*
 * @author Sanero.
 * Created date: Jan 10, 2019
 * Created time: 8:56:58 AM
 * Description: TODO - 
 */
@Controller
@RequestMapping("/login")
@SessionAttributes("userLogin")
public class LoginController {
  @Autowired
  UserService userService;

  @GetMapping
  public String login(ModelMap model) {
    model.addAttribute("user", new User());
    return "login";
  }

  @PostMapping
  public RedirectView handleLogin(@ModelAttribute User user, ModelMap model,
      RedirectAttributes attributes) {
    User userLogin = userService.getUserLogin(user.getUsername(),
        user.getPassword());

    if (userLogin == null) {
      attributes.addFlashAttribute("mess", "username or password incorrect");
      return new RedirectView("/login");
    }

    model.addAttribute("userLogin", userLogin);
    return new RedirectView("/list-user");
  }
}
