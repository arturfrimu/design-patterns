package com.arturfrimu.pattern.builder.java;

public class Client {
    public static void main(String[] args) {
        Store store = new Store();

        // Low readability
        store.addProduct(new Product("Iphone X", "Best Iphone", 1300, "India", 10, true));
        // Optional parameters:
        store.addProduct(new Product("Iphone 11", null, 0, null, 0, true));
        // Telescoping constructor
        store.addProduct(new Product("Iphone 12", 1300, "China", 10));

        /*
        High readability
        Required parameters in constructor
        Optional parameters using .set
        No telescopic constructors
        */
        store.addProduct(new Product.ProductBuilder("Iphone 13")
                .setDescription("Best Iphone")
                .setPrice(1300)
                .setManufacturer("Corea")
                .setStock(10)
                .setIsImported(true)
                .build());

        store.addProduct(new Product.ProductBuilder("Iphone 14")
                .setPrice(1300)
                .setManufacturer("Taiwan")
                .setStock(10)
                .build());
    }
}
