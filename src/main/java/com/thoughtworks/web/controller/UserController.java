package com.thoughtworks.web.controller;

import com.thoughtworks.model.User;
import com.thoughtworks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "users")
    public List<User> loadAll() throws IOException, SQLException {
        return userService.findAll();
    }
}
