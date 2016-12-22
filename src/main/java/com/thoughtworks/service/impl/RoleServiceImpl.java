package com.thoughtworks.service.impl;

import com.thoughtworks.model.Role;
import com.thoughtworks.repository.RoleRepository;
import com.thoughtworks.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository repository;

    @Override
    public void save(Role role) {
        repository.save(role);
    }
}
