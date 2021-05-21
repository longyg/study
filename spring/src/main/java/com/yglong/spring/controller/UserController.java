package com.yglong.spring.controller;

import com.yglong.spring.entity.User;
import com.yglong.spring.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.findAllUsers();
    }

    @PostMapping
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @PutMapping
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping
    public void delete(@RequestBody User user) {
        userService.delete(user);
    }

    @GetMapping("/{name}")
    public User getByName(@PathVariable String name) {
        return userService.getByName(name);
    }
}
