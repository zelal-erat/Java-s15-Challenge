package models;

import java.util.HashSet;
import java.util.Set;

public class Reader extends Person{
    private int readerId;
    private Set<Books> borrowedBooks;
    private static final int MAX_BOOKS = 5;

    public Reader(String name, int readerId) {
        super(name);
        this.readerId=readerId;
        this.borrowedBooks = new HashSet<>();

    }

    public int getReaderId() {
        return readerId;
    }

    public Set<Books> getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean addBorrowedBook(Books book) {
        if (borrowedBooks.size() < MAX_BOOKS) {
            borrowedBooks.add(book);
            return true;
        } else {
            System.out.println("5 kitap limitine ulaşıldı!");
            return false;
        }
    }

    public void removeBorrowedBook(Books book) {
        borrowedBooks.remove(book);
    }

    @Override
    public void whoYouAre() {
        System.out.println("Reader: " + getName());

    }
}
