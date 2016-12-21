package com.thoughtworks.web.controller;

import com.thoughtworks.model.User;
import com.thoughtworks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "users",
            method = RequestMethod.GET)
    public List<User> getAll() throws IOException, SQLException {
        User user = new User();
        user.setName("sjyuan");
        List<User> result  = new ArrayList<>();
        result.add(user);
        return userService.getAll();
    }
}
