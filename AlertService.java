package com.jit.warehouse;

public class AlertService implements StockObserver {
    public void onLowStock(Product product) {
        System.out.println("⚠️ Restock Alert: Low stock for " + product.getName() + 
                           " – only " + product.getQuantity() + " left!");
    }
}
