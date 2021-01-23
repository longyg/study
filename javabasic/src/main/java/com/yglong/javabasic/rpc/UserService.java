package com.yglong.javabasic.rpc;

public interface UserService {

    User findUserById(int id);

    void printUser(User user);
}
