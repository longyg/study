package com.yglong.javabasic.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        UserService userService = (UserService) getProxyBean(UserServiceImpl.class);
        User user = userService.findUserById(1);
        System.out.println(user.getId() + ":" + user.getName());
    }

    public static Object getProxyBean(Class clazz) {
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            System.out.println("before invoke...");
            Object result = method.invoke(clazz.newInstance(), args);
            System.out.println("after invoke...");
            return result;
        };
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), invocationHandler);
    }


}
