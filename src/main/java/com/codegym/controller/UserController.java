package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.servie.IUerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    IUerService uerService;

    @GetMapping("users/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/user/create");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
    @PostMapping("users/create")
    public ModelAndView saveUser(@ModelAttribute("user") User user){
        uerService.save(user);
        ModelAndView modelAndView = new ModelAndView("/user/create");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("message", "Register account successfull!");
        return modelAndView;


    }






}
