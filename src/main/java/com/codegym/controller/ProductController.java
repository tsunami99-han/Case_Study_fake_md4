package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.servie.category.ICategoryService;
import com.codegym.servie.product.IProductService;
import com.sun.tracing.dtrace.Attributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product",new Product());
        modelAndView.addObject("categories", iCategoryService.findAll());
        return modelAndView;
    }
    @PostMapping("/create")
    public String saveProduct(@ModelAttribute("product") Product product){
        iProductService.save(product);
        return "redirect:/products";
    }

    @GetMapping("")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products", iProductService.findAll());
        return modelAndView;
    }

}
