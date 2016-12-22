package com.thoughtworks.service.impl;

import com.thoughtworks.model.Role;
import com.thoughtworks.model.User;
import com.thoughtworks.repository.RoleRepository;
import com.thoughtworks.repository.UserRepository;
import com.thoughtworks.service.UserService;
import com.thoughtworks.util.EncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        user.setPassword(EncryptUtils.encryptPassword(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info(userRepository.findAll().toString());
        User user = userRepository.findByName(username);
        LOGGER.info("username = " + username);
        LOGGER.info(user.toString());
        List<Role> roles = roleRepository.findByUser(user);
        user.setRoles(roles);
        return user;
    }
}
