package com.jit.warehouse;

public interface StockObserver {
    void onLowStock(Product product);
}
