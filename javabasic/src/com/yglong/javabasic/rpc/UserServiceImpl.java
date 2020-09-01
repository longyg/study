package com.yglong.javabasic.rpc;

public class UserServiceImpl implements UserService {
    @Override
    public User findUserById(int id) {
        return new User(id, "yglong");
    }

    public void printUser(User user) {
        System.out.println(user.getId() + "---" + user.getName());
    }
}
