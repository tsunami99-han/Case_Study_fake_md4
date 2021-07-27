package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/products")

public class ProductController {
    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("categories", iCategoryService.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveProduct(@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        product.setImage(fileName);
        iProductService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Created new product successfully !");
        return modelAndView;
    }

    @GetMapping
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", iProductService.findAll());
        return modelAndView;
    }
    @GetMapping("{id}/edit")
    public ModelAndView edit(@PathVariable Long id){
        Optional<Product> product = iProductService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", product.get());
        modelAndView.addObject("categories", iCategoryService.findAll());
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView update(@ModelAttribute Product product,@RequestParam("fileImage") MultipartFile multipartFile){
        if (multipartFile.getOriginalFilename()!=""){
            String fileName = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            product.setImage(fileName);
            iProductService.save(product);
        }else {
            Product product1=iProductService.findById(product.getId()).get();
            product.setImage(product1.getImage());
            iProductService.save(product);
        }
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("message","Update thành công");
        modelAndView.addObject("products",iProductService.findAll());
        return modelAndView;
    }
    @GetMapping("{id}/delete")
    public ModelAndView delete(@PathVariable Long id){
        Optional<Product> product = iProductService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/product/delete");
        modelAndView.addObject("product", product.get());
        return modelAndView;
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Product product){
        iProductService.remove(product.getId());
        return "redirect:/products";
    }
}
