package com.codegym.controller;
import com.codegym.model.Category;
import com.codegym.repository.ICategoryRepository;
import com.codegym.servie.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("/create")
    public String showFormCreate(){
        return "/category/create";
    }
    @PostMapping("/create")
    public String saveNew(Category category){
        iCategoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("categories", iCategoryService.findAll());
        return modelAndView;
    }

    @GetMapping("{id}/edit")
    public ModelAndView showFormEdit(@PathVariable int id){
        Optional<Category> category = iCategoryService.findById((long) id);
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category", category.get());
        return modelAndView;
    }
    @PostMapping("edit")
    public String updateCategory(@ModelAttribute Category category){
        iCategoryService.save(category);
        return "redirect:/categories";
    }

}
