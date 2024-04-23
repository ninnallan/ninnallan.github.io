import java.util.Arrays;

class Product {
    private int id;
    private String name;
    private String category;
    private double price;
    private int quantity;

    public Product(int id, String name, String category, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product id=" + id +", name='" + name + '\'' +", category='" + category + '\'' +", price=" + price +", quantity=" + quantity;
    }
}

class Inventory {
    private Product[] products;
    private int size;

    public Inventory(int capacity) {
        products = new Product[capacity];
        size = 0;
    }

    public void add(Product product) {
        if (size < products.length) {
            products[size++] = product;
        } else {
            System.out.println("Full.");
        }
    }

    public void remove(int id) {
        for (int i = 0; i < size; i++) {
            if (products[i].getId() == id) {
                for (int j = i; j < size - 1; j++) {
                    products[j] = products[j + 1];
                }
                size--;
                return;
            }
        }
        System.out.println("not found.");
    }

    public void update(int id, double price, int quantity) {
        for (int i = 0; i < size; i++) {
            if (products[i].getId() == id) {
                products[i].setPrice(price);
                products[i].setQuantity(quantity);
                return;
            }
        }
        System.out.println("not found.");
    }

    public Product search(String keyword) {
        for (Product product : products) {
            if (product != null && (product.getName().equalsIgnoreCase(keyword) ||
                    product.getCategory().equalsIgnoreCase(keyword) ||
                    String.valueOf(product.getId()).equals(keyword))) {
                return product;
            }
        }
        return null;
    }

    public void all() {
        for (int i = 0; i < size; i++) {
            System.out.println(products[i]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory(10);

        //Adding
        inventory.add(new Product(1, "Laptop", "Electronics", 100, 10));
        inventory.add(new Product(2, "Chair", "Furniture", 40, 30));

        //All
        System.out.println("All products:");
        inventory.all();
        //Search
        Product foundProduct = inventory.search("2");
        if (foundProduct != null) {
            System.out.println(foundProduct);
        } else {
            System.out.println("not found.");
        }

        //Removing
        inventory.remove(1);

        //Updating
        inventory.update(2, 59, 25);
        System.out.println("Updated:");
        System.out.println(inventory.search("2"));
    }
}
