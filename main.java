package com.jit.warehouse;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Warehouse warehouse = new Warehouse();

        while (true) {
            System.out.println("\n=== Warehouse Inventory Tracker ===");
            System.out.println("1. Add Product");
            System.out.println("2. Receive Shipment");
            System.out.println("3. Fulfill Order");
            System.out.println("4. View All Products");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter Reorder Threshold: ");
                    int th = sc.nextInt();
                    warehouse.addProduct(new Product(id, name, qty, th));
                    break;

                case 2:
                    System.out.print("Enter Product ID: ");
                    id = sc.nextLine();
                    System.out.print("Enter Shipment Quantity: ");
                    qty = sc.nextInt();
                    warehouse.receiveShipment(id, qty);
                    break;

                case 3:
                    System.out.print("Enter Product ID: ");
                    id = sc.nextLine();
                    System.out.print("Enter Order Quantity: ");
                    qty = sc.nextInt();
                    warehouse.fulfillOrder(id, qty);
                    break;

                case 4:
                    warehouse.viewAllProducts();
                    break;

                case 5:
                    System.out.println("🚪 Exiting... Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }
        }
    }
}
