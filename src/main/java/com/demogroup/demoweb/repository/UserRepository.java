package com.demogroup.demoweb.repository;

import com.demogroup.demoweb.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


public interface UserRepository {
    void save(User user);
    User findByUserId(Long userId);
    Collection<User> findAll();
}
