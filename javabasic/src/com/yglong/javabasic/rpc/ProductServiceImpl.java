package com.yglong.javabasic.rpc;

public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(int id) {
        return new Product(id, "apple", 30);
    }
}
