package com.yglong.spring.service;

import com.yglong.spring.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@CacheConfig(cacheNames = "test:users")
@Slf4j
public class UserServiceImpl implements UserService {
    @Cacheable
    @Override
    public List<User> findAllUsers() {
        log.info("fetching user list...");
        return Collections.singletonList(new User("yglong", "test@test.com", 30));
    }

    @CachePut(value = "user", key = "#user.name")
    @Override
    public User save(User user) {
        log.info("saving user: " + user);
        return user;
    }

    @CachePut(value = "user", key = "#user.name")
    @Override
    public User update(User user) {
        log.info("updating user: " + user);
        return user;
    }

    @Cacheable(value = "user", key = "#name")
    @Override
    public User getByName(String name) {
        log.info("get user by name: " + name);
        return new User(name, "hehe@test.com", 10);
    }

    @CacheEvict(value = "user", key = "#user.name")
    @Override
    public void delete(User user) {
        log.info("deleting user: " + user);
    }
}
