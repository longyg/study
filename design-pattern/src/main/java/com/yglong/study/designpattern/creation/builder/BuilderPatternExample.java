package com.yglong.study.designpattern.creation.builder;

/**
 * 建造者模式
 */
public class BuilderPatternExample {

    public static void main(String[] args) {
        Product book = new ProductBuilder()
                .setName("book")
                .setPrice(56.5)
                .build();
        System.out.println(book);

        Product bingxiang = new ProductBuilder()
                .setName("bingxiang")
                .setPrice(3500)
                .build();
        System.out.println(bingxiang);
    }
}

class Product {
    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

interface IBuilder {
    Product build();
}

class ProductBuilder implements IBuilder {
    private Product product = new Product();

    public ProductBuilder setName(String name) {
        product.setName(name);
        return this;
    }

    public ProductBuilder setPrice(double price) {
        product.setPrice(price);
        return this;
    }

    @Override
    public Product build() {
        return product;
    }
}


