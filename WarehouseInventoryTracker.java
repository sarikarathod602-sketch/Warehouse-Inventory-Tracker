import java.util.*;

// ===================== Product Class =====================
class Product {
    private String id;
    private String name;
    private int quantity;

    public Product(String id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
}

    public String getId() {
        return id;
}

    public String getName() {
        return name;
}

    public int getQuantity() {
        return quantity;
}


    public void increaseQuantity(int amount) {
        if (amount > 0) {
            this.quantity += amount;
}
}

    public boolean decreaseQuantity(int amount) {
        if (amount > 0 && amount <= this.quantity) {
            this.quantity -= amount;
            return true;
} else {
            System.out.println("❌ Not enough stock for " + name);
            return false;
}
}

  
    public String toString() {
        return String.format("ID: %s | Name: %s | Quantity: %d | Reorder Threshold: %d",
                id, name, quantity, reorderThreshold);
}
}

// ===================== Observer Interface =====================
interface StockObserver {
    void onLowStock(Product product);
}

// ===================== Alert Service =====================
class AlertService implements StockObserver {
  
    public void onLowStock(Product product) {
        System.out.println("⚠ Restock Alert! Low stock for " + product.getName()
                + " — only " + product.getQuantity() + " left.");
}
}

// ===================== Warehouse Class =====================
import java.util.HashMap;

public class Warehouse {
    private HashMap<String, Product> products;
    private AlertService alertService;
    private int threshold; // ✅ warehouse-level threshold

    public Warehouse(AlertService alertService, int threshold) {
        products = new HashMap<>();
        this.alertService = alertService;
        this.threshold = threshold;
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
        System.out.println("✅ Product added: " + product.getName());
    }

    public void receiveShipment(String id, int quantity) {
        Product product = products.get(id);
        if (product != null) {
            product.setQuantity(product.getQuantity() + quantity);
            System.out.println("📦 Shipment received for " + product.getName() +
                               ". New Quantity: " + product.getQuantity());
        } else {
            System.out.println("❌ Product not found!");
        }
    }

    public void fulfillOrder(String id, int quantity) {
        Product product = products.get(id);
        if (product != null) {
            if (product.getQuantity() >= quantity) {
                product.setQuantity(product.getQuantity() - quantity);
                System.out.println("🛒 Order fulfilled for " + product.getName() +
                                   ". Remaining: " + product.getQuantity());

                // ✅ Check warehouse-level threshold
                if (product.getQuantity() < threshold) {
                    alertService.triggerAlert(product);
                }
            } else {
                System.out.println("⚠ Insufficient stock for " + product.getName());
            }
        } else {
            System.out.println("❌ Product not found!");
        }
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("📭 No products available!");
        } else {
            System.out.println("\n📋 Product List:");
            for (Product p : products.values()) {
                p.displayInfo();
            }
        }
    }
    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}

// ===================== Main Class =====================
public class WarehouseInventoryTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Warehouse warehouse = new Warehouse();
        warehouse.addObserver(new AlertService());

        while (true) {
            System.out.println("\n===============================");
            System.out.println("📦 WAREHOUSE INVENTORY SYSTEM");
            System.out.println("===============================");
            System.out.println("1️⃣  Add Product");
            System.out.println("2️⃣  Delete Product");
            System.out.println("3️⃣  Receive Shipment (+Stock)");
            System.out.println("4️⃣  Fulfill Order (-Stock)");
            System.out.println("5️⃣  View All Products");
            System.out.println("6️⃣  Exit");
            System.out.print("👉 Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Initial Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter Reorder Threshold: ");
                    int threshold = sc.nextInt();
                    warehouse.addProduct(new Product(id, name, qty, threshold));
                    break;

                case 2:
                    System.out.print("Enter Product ID to delete: ");
                    warehouse.deleteProduct(sc.nextLine());
                    break;

                case 3:
                    System.out.print("Enter Product ID: ");
                    String sid = sc.nextLine();
                    System.out.print("Enter quantity to add: ");
                    int sqty = sc.nextInt();
                    warehouse.receiveShipment(sid, sqty);
                    break;

                case 4:
                    System.out.print("Enter Product ID: ");
                    String oid = sc.nextLine();
                    System.out.print("Enter quantity to fulfill: ");
                    int oqty = sc.nextInt();
                    warehouse.fulfillOrder(oid, oqty);
                    break;

                case 5:
                    warehouse.showAllProducts();
                    break;

                case 6:
                    System.out.println("👋 Exiting system. Goodbye!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("❌ Invalid choice! Try again.");
}
}
}
    }
