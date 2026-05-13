package com.arturfrimu.pattern.builder.java;

import lombok.Getter;

@Getter
public class Product {
    private final String name;
    private String description;
    private final int price;
    private final String manufacturer;
    private final int stock;
    private boolean isImported;

    public Product(String name, String description, int price, String manufacturer, int stock, boolean isImported) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.manufacturer = manufacturer;
        this.stock = stock;
        this.isImported = isImported;
    }

    public Product(String name, int price, String manufacturer, int stock) {
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.stock = stock;
    }

    private Product(ProductBuilder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.manufacturer = builder.manufacturer;
        this.stock = builder.stock;
        this.isImported = builder.isImported;
    }

    // ProductBuilder Class
    public static class ProductBuilder {
        private String name;
        private String description;
        private int price;
        private String manufacturer;
        private int stock;
        private boolean isImported;

        public ProductBuilder(String name) {
            this.name = name;
        }

        public ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder setPrice(int price) {
            this.price = price;
            return this;
        }

        public ProductBuilder setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public ProductBuilder setStock(int stock) {
            this.stock = stock;
            return this;
        }

        public ProductBuilder setIsImported(boolean isImported) {
            this.isImported = isImported;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
