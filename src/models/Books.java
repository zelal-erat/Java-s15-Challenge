package models;



public class Books implements Borrowable{
    private int id;
    private String title;
    private Author author;
    private Category category;
    private double price;
    private boolean available;

    public Books(int id, String title, Author author, Category category, double price) {
        this(id, title, author, category, price, false);
    }

    public Books(int id, String title, Author author, Category category, double price, boolean available) {
        this.id = id;
        this.title=title;
        this.author=author;
        this.category=category;
        this.price=price;
        this.available=available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void borrowBook(Reader reader) {
        if (!isAvailable()) {
            if (reader.addBorrowedBook(this)) {
                available = true;
                System.out.println("'" + title + "' kitabı ödünç alındı.");
            }
        } else {
            System.out.println("Kitap zaten ödünç alınmış.");
        }

    }

    @Override
    public void returnBook(Reader reader) {
        if (isAvailable()) {
            available = false;
            reader.removeBorrowedBook(this);
            System.out.println("'" + title + "' kitabı iade edildi.");
        } else {
            System.out.println("Kitap zaten kütüphanede.");
        }

    }
}
