package com.demogroup.demoweb.repository;

import com.demogroup.demoweb.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private final static Map<Long, User> userDB=new HashMap<>();

    @Override
    public void save(User user) {
        userDB.put(user.getUid(), user);
    }

    @Override
    public User findByUserId(Long userId) {
        User user = userDB.get(userId);
        return user;
    }

    @Override
    public Collection<User> findAll() {
        Collection<User> values = userDB.values();
        return values;
    }
}
