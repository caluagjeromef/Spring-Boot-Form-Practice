package com.formpractice.springboot.controller;

import com.formpractice.springboot.model.UserModel;
import com.formpractice.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UserModel());
        return "registration_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UserModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel){
        System.out.println("register request: " +userModel);
        UserModel registeredUser = userService.registerUser(userModel.getUsername(), userModel.getPassword(), userModel.getEmail());

        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, Model model){
        System.out.println("register request: "+userModel);
        UserModel authenticatedUser = userService.authenticate(userModel.getUsername(), userModel.getPassword());

        if (authenticatedUser != null) {
            model.addAttribute("userLogin", authenticatedUser.getUsername());
            return "home_page";
        } else {
            return "error_page";
        }
    }

}
