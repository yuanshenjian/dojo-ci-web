package com.thoughtworks.repository;

import com.thoughtworks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    User findByNameAndPassword(String name, String password);
}
