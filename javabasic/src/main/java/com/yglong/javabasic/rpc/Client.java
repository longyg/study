package com.yglong.javabasic.rpc;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        UserService userService = Stub.getStub(UserServiceImpl.class);
        User user = userService.findUserById(2);
        System.out.println(user.getId() + " : " + user.getName());

        userService.printUser(user);

        ProductService productService = Stub.getStub(ProductServiceImpl.class);
        Product product = productService.getProductById(20);
        System.out.println(product.getId() + " : " + product.getName() + " : " + product.getPrice());
    }
}
