package com.puppies.controller;

import com.puppies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ModelAndView addUser (
            HttpSession session,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email){
        if (!userService.usernameAlreadyTaken(username)) {
            return new ModelAndView("index")
                    .addObject("InvalidInput",
                            "Username already taken");
        }
        int userId = userService.createUser(username, password, email);
        session.setAttribute("userId", userId);
        session.setAttribute("user", email);
        session.setAttribute("user", username);

        return new ModelAndView("loggedin");
    }

    @PostMapping("/login")
    public ModelAndView getInfoFromLoginForm(
            HttpSession session,
            @RequestParam String username,
            @RequestParam String password) {
        Integer userId = userService.checkLogin(username, password);
        if (userId != null) {
            session.setAttribute("user", username);
            session.setAttribute("userId", userId);
            return new ModelAndView("loggedin");
        }
        return new ModelAndView("index")
                .addObject("IncorrectPasswordOrUsername",
                        "Password or username incorrect. Please try again.");
    }
}
