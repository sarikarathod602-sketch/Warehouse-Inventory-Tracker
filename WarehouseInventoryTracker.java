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
class Warehouse {
    private Map<String, Product> products = new HashMap<>();
    private List<StockObserver> observers = new ArrayList<>();

    public void addObserver(StockObserver observer) {
        observers.add(observer);
}

    private void notifyLowStock(Product product) {
        for (StockObserver obs : observers) {
            obs.onLowStock(product);
}
}

    public void addProduct(Product product) {
        if (products.containsKey(product.getId())) {
            System.out.println("⚠ Product ID already exists!");
            return;
}
        products.put(product.getId(), product);
        System.out.println("✅ Product added successfully!");
}

    public void deleteProduct(String id) {
        if (products.remove(id) != null) {
            System.out.println("🗑 Product deleted successfully!");
} else {
            System.out.println("❌ Product not found!");
}
}

    public void receiveShipment(String id, int qty) {
        Product p = products.get(id);
        if (p != null) {
            p.increaseQuantity(qty);
            System.out.println("📦 Shipment received for " + p.getName() +
                    " (+ " + qty + " units, total = " + p.getQuantity() + ")");
} else {
            System.out.println("❌ Product not found!");
}
}

    public void fulfillOrder(String id, int qty) {
        Product p = products.get(id);
        if (p != null) {
            int before = p.getQuantity();
            if (p.decreaseQuantity(qty)) {
                System.out.println("🛒 Order fulfilled for " + p.getName() +
                        " (- " + qty + " units, remaining = " + p.getQuantity() + ")");
                if (p.getQuantity() < p.getReorderThreshold() && before >= p.getReorderThreshold()) {
                    notifyLowStock(p);
}
}
} else {
            System.out.println("❌ Product not found!");
}
}

    public void showAllProducts() {
        if (products.isEmpty()) {
            System.out.println("📦 No products in inventory.");
            return;
}
        System.out.println("\n📋 Current Inventory:");
        for (Product p : products.values()) {
            System.out.println(p);
}
}

    public boolean hasProducts() {
        return !products.isEmpty();
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
