package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

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
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", iProductService.findAll());
        return modelAndView;
    }

    @GetMapping("{id}/edit")
    public ModelAndView edit(@PathVariable Long id){
        Optional<Product> product = iProductService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("categories", iCategoryService.findAll());
        return modelAndView;
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute Product product){
        iProductService.save(product);
        return "redirect:/products";
    }
    @GetMapping("{id}/delete")
    public ModelAndView delete(@PathVariable Long id){
        Optional<Product> product = iProductService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/product/delete");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Product product){
        iProductService.remove(product.getId());
        return "redirect:/products";
    }
}
