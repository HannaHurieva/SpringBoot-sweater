package com.alevel.springMVC.sweater.repository;

import com.alevel.springMVC.sweater.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
