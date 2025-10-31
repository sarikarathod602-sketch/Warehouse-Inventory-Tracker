package com.jit.warehouse;

public class Product {
    private String productId;
    private String name;
    private int quantity;

    public Product(String productId, String name, int quantity) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }


    public void increaseStock(int amount) {
        if (amount > 0) {
            this.quantity += amount;
        }
    }

    public void decreaseStock(int amount) {
        if (amount > 0 && amount <= this.quantity) {
            this.quantity -= amount;
        } else {
            System.out.println("❌ Invalid quantity to reduce for product: " + name);
        }
    }

}
