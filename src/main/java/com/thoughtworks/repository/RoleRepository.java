package com.thoughtworks.repository;

import com.thoughtworks.model.Role;
import com.thoughtworks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByUser(User user);
}
