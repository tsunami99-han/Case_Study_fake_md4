package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/create")
    public String showFormCreate(){
        return "/user/create";
    }

    @PostMapping("/create")
    public ModelAndView create(User user){
        userService.save(user);
        ModelAndView modelAndView=new ModelAndView("/user/create");
        modelAndView.addObject("message","Đăng kí thành công");
        return  modelAndView;
    }
}
