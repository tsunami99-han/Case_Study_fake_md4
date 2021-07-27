package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.role.IRoleService;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    IUserService userService;

    @Value("${file-upload}")
    private String fileUpload;


    @Autowired
    IRoleService roleService;
    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/user/create");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("roles",roleService.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute("user") User user, @RequestParam("fileImage")MultipartFile multipartFile){
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        user.setImage(fileName);
        userService.save(user);
        ModelAndView modelAndView=new ModelAndView("/user/create");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("roles",roleService.findAll());
        modelAndView.addObject("message","Đăng kí thành công");
        return modelAndView;
    }
}
