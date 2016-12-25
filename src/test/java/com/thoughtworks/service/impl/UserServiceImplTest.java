package com.thoughtworks.service.impl;

import com.thoughtworks.model.Role;
import com.thoughtworks.model.User;
import com.thoughtworks.repository.RoleRepository;
import com.thoughtworks.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void should_find_all_users() throws Exception {
        List<User> users = new ArrayList<>();
        Collections.addAll(users, mock(User.class), mock(User.class), mock(User.class));
        when(userRepository.findAll()).thenReturn(users);

        assertThat(userService.findAll(), is(users));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void should_save_user() throws Exception {
        User user = new User("sjyuan", "000");
        User savedUser = new User("sjyuan", new BCryptPasswordEncoder().encode(user.getPassword()));
        when(userRepository.save(user)).thenReturn(savedUser);

        userService.save(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void should_load_user_by_username() throws Exception {
        String username = "sjyuan";
        User user = new User(username, "000");
        List<Role> roles = new ArrayList<>();
        Collections.addAll(roles, mock(Role.class));
        user.setRoles(roles);
        when(userRepository.findByName(username)).thenReturn(user);
        when(roleRepository.findByUser(user)).thenReturn(roles);

        assertThat(userService.loadUserByUsername(username), is(user));
        verify(userRepository, times(1)).findByName(username);
    }
}