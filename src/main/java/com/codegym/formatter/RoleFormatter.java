package com.codegym.formatter;

import com.codegym.model.Role;
import com.codegym.repository.IRoleRepository;
import com.codegym.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RoleFormatter implements Formatter<Role> {
    private IRoleService roleService;

    @Autowired
    public RoleFormatter(IRoleService roleService){
        this.roleService=roleService;
    }

    @Override
    public Role parse(String text, Locale locale) throws ParseException {
        return this.roleService.findById(Long.valueOf(text)).get();
    }

    @Override
    public String print(Role object, Locale locale) {
        return null;
    }
}
