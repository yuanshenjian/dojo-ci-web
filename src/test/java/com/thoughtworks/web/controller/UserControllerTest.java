package com.thoughtworks.web.controller;

import com.thoughtworks.model.User;
import com.thoughtworks.service.UserService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends BaseControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Override
    public Object getMockController() {
        return userController;
    }

    @Test
    public void should_load_all() throws Exception {
        List<User> users = new ArrayList<>();
        User user = new User("sjyuan", "000");
        user.setAge(26);
        user.setSex(User.Sex.MALE);
        users.add(user);
        when(userService.findAll()).thenReturn(users);
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username", is("sjyuan")))
                .andExpect(jsonPath("$[0].password", is("000")))
                .andExpect(jsonPath("$[0].sex", is(User.Sex.MALE.toString())))
                .andExpect(jsonPath("$[0].roles", nullValue()))
                .andExpect(jsonPath("$[0].age", is(26)));
    }
}