package com.thoughtworks;

import com.thoughtworks.model.Role;
import com.thoughtworks.model.User;
import com.thoughtworks.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DojoCiWebApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(DojoCiWebApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DojoCiWebApplication.class, args);
        initData(context);
    }

    private static void initData(ConfigurableApplicationContext context) {
        UserService userService = (UserService) context.getBean("userServiceImpl");
        User user = new User();
        user.setAge(26);
        user.setSex(User.Sex.MALE);
        user.setName("sjyuan");
        user.setPassword("000");
        user.setPhone("18192235667");

        Role role = new Role();
        role.setUser(user);
        role.setName(Role.Name.ADMIN);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);

        userService.save(user);
        LOGGER.info(user.toString());
    }
}
