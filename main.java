package com.jit.warehouse;

public class Main {
    public static void main(String[] args) {
        AlertService alertService = new AlertService();
        Warehouse warehouse = new Warehouse(alertService);

        Product laptop = new Product("P101", "Laptop", 0, 5);
        warehouse.addProduct(laptop);

        warehouse.receiveShipment("P101", 10);
        warehouse.fulfillOrder("P101", 6);
        warehouse.showInventory();

        // Adding another product
        Product phone = new Product("P102", "Smartphone", 3, 4);
        warehouse.addProduct(phone);
        warehouse.fulfillOrder("P102", 1);
        warehouse.showInventory();
    }
}