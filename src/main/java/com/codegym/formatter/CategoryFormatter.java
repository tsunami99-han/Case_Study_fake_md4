package com.codegym.formatter;

import com.codegym.model.Category;
import com.codegym.servie.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class CategoryFormatter implements Formatter<Category> {

    private ICategoryService iCategoryService;

    @Autowired
    public CategoryFormatter(ICategoryService iCategoryService){
        this.iCategoryService = iCategoryService;
    }

    @Override
    public Category parse(String text, Locale locale) throws ParseException {
        return this.iCategoryService.findById(Long.valueOf(text)).get();
    }

    @Override
    public String print(Category object, Locale locale) {
        return null;
    }
}