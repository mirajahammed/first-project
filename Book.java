
public class BOOK {
  
    private String title;
    private String author;
    private double price;

    public BOOK() {
        
        this.title = "A";
        this.author = "B";
        this.price = 350.00;
    }

    public BOOK(String title, String author) {
        this.title = title;
        this.author = author;
        this.price = 300.00;
    }

    public BOOK(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
    public static void main(String[] args) {
        BOOK book1 = new BOOK();
        System.out.println("Book1 Title: " + book1.title);
        System.out.println("Book1 Author: " + book1.author);
        System.out.println("Book1 Price: " + book1.price);

        BOOK book2 = new BOOK("C", "D");
        System.out.println("Book2 Title: " + book2.title);
        System.out.println("Book2 Author: " + book2.author);
        System.out.println("Book2 Price: " + book2.price);

        BOOK book3 = new BOOK("X", "Y", 400.00);
        System.out.println("Book3 Title: " + book3.title);
        System.out.println("Book3 Author: " + book3.author);
        System.out.println("Book3 Price: " + book3.price);
    }
}

