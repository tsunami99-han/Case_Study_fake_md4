package com.codegym.formatter;

import com.codegym.model.User;
import com.codegym.servie.IUerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class UserFormatter implements Formatter<User> {

    private IUerService uerService;

    @Autowired
    public UserFormatter(IUerService uerService){
        this.uerService = uerService;
    }

    @Override
    public User parse(String text, Locale locale) throws ParseException {
        Optional<User> userOptional = uerService.findById(Long.parseLong(text));
        return userOptional.orElse(null);
    }

    @Override
    public String print(User object, Locale locale) {
        return null;
    }
}
