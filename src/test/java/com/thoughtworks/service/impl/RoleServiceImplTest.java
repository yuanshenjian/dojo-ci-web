package com.thoughtworks.service.impl;

import com.thoughtworks.model.Role;
import com.thoughtworks.model.User;
import com.thoughtworks.repository.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class RoleServiceImplTest {
    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    public void should_save_role() throws Exception {
        Role role = new Role();
        role.setName(Role.Name.ADMIN);
        role.setUser(mock(User.class));
        when(roleRepository.save(role)).thenReturn(role);
        roleService.save(role);
        verify(roleRepository, times(1)).save(role);
    }
}