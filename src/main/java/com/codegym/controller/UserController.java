package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.servie.IUerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
  @GetMapping("login")
    public ModelAndView showFormLogin(){
      ModelAndView modelAndView = new ModelAndView("/user/login");
      modelAndView.addObject("login", new User());
      return modelAndView;
    }
    @PostMapping("login")
    public ModelAndView login(@ModelAttribute("login") User user){
        ModelAndView modelAndView = new ModelAndView("user/index");
        ModelAndView modelAndView2 = new ModelAndView("user/login");
        Iterable<User> users = uerService.findAll();
        for (User u: users){
            if (u.getUsername().equals(user.getUsername())&& u.getPassword().equals(user.getPassword())){
                return modelAndView;
            }else {
                modelAndView.addObject("message", "sai mat khau hoac ten dang nhap");
                return modelAndView2;
            }
        }
        return modelAndView2;
    }
}
