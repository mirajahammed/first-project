
import java.util.Scanner;

class Product {
    
    private String name;
    private double price;
    private int quantity;

    
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Invalid price! Price cannot be negative.");
        }
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Invalid quantity! Quantity cannot be negative.");
        }
    }

    
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    
    public void showProductInfo() {
        System.out.println("\n Product Information ");
        System.out.println("Name     : " + name);
        System.out.println("Price    : " + price);
        System.out.println("Quantity : " + quantity);
        System.out.println("Total Value : " + (price * quantity));
    }
}

public class Productprice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Product p = new Product();

        
        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();
        p.setName(name);

        System.out.print("Enter Product Price: ");
        double price = sc.nextDouble();
        p.setPrice(price);

        System.out.print("Enter Product Quantity: ");
        int quantity = sc.nextInt();
        p.setQuantity(quantity);

        
        p.showProductInfo();

        sc.close();
    }
}