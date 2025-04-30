package models;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Library {
    // Map yapısı: ID -> Book, Reader, Author, Invoice
    private Map<Integer, Books> books = new HashMap<>();
    private Map<Integer, Reader> readers = new HashMap<>();
    private Map<Integer, Author> authors = new HashMap<>();
    private Map<Integer, Invoice> invoices = new HashMap<>();
    private Map<Integer, Librarian> librarian = new HashMap<>();

    // Ek olarak kategorileri Set içinde saklayarak tekrar eden kategorileri engelleyebiliriz.
    private Set<Category> categories = new HashSet<>();

    // Singleton pattern (isteğe bağlı)
    private static Library instance;

    private Library() {}

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public Map<Integer, Books> getBooks() {
        return books;
    }

    public Map<Integer, Reader> getReaders() {
        return readers;
    }

    public Map<Integer, Author> getAuthors() {
        return authors;
    }

    public Map<Integer, Invoice> getInvoices() {
        return invoices;
    }

    public Map<Integer, Librarian> getLibrarian() {
        return librarian;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    // Kitap işlemleri
    public void addBook(Books book) {
        books.put(book.getId(), book);
        categories.add(book.getCategory());
    }

    public Books getBookById(int id) {
        return books.get(id);
    }

    public void removeBook(int id) {
        books.remove(id);
    }
}