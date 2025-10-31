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
