package com.jit.warehouse;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private Map<String, Product> inventory = new HashMap<>();
    private StockObserver observer;

    public Warehouse(StockObserver observer) {
        this.observer = observer;
    }

    public void addProduct(Product product) {
        if(!inventory.containsKey(product.getProductId())) {
            inventory.put(product.getProductId(), product);
            System.out.println("✅ Added new product: " + product.getName());
        } else {
            System.out.println("⚠ Product already exists with ID: " + product.getProductId());
        }
    }

    public void receiveShipment(String productId, int quantity) {
        Product product = inventory.get(productId);
        if(product != null) {
            product.increaseStock(quantity);
            System.out.println("📦 Shipment received: " + quantity + " units of " + product.getName());
        } else {
            System.out.println("❌ Invalid Product ID!");
        }
    }

    public void fulfillOrder(String productId, int quantity) {
        Product product = inventory.get(productId);
        if(product != null) {
            if(product.getQuantity() >= quantity) {
                product.decreaseStock(quantity);
                System.out.println("🛒 Fulfilled order of " + quantity + " units of " + product.getName());
                if(product.getQuantity() < product.getReorderThreshold()) {
                    observer.onLowStock(product);
                }
            } else {
                System.out.println("❌ Insufficient stock for product: " + product.getName());
            }
        } else {
            System.out.println("❌ Invalid Product ID!");
        }
    }

    public void showInventory() {
        System.out.println("\n📊 Current Inventory Status:");
        for(Product p : inventory.values()) {
            System.out.println("ID: " + p.getProductId() + ", Name: " + p.getName() + 
                               ", Quantity: " + p.getQuantity());
        }
    }
}