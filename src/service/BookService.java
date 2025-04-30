package service;

import models.Books;
import models.Library;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private Library library = Library.getInstance();

    public void addBook(Books book) {
        library.addBook(book);
        System.out.println("'" + book.getTitle() + "' kitabı kütüphaneye eklendi.");
    }

    public Books findBookById(int id) {
        return library.getBookById(id);
    }

    public List<Books> findBookByTitle(String title) {
        List<Books> result = new ArrayList<>();
        for (Books b : library.getBooks().values()) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                result.add(b);
            }
        }
        return result;
    }

    public List<Books> findBookByAuthor(String authorName) {
        List<Books> result = new ArrayList<>();
        for (Books b : library.getBooks().values()) {
            if (b.getAuthor().getName().equalsIgnoreCase(authorName)) {
                result.add(b);
            }
        }
        return result;
    }

    public void updateBook(Books book) {
        if (library.getBooks().containsKey(book.getId())) {
            library.getBooks().put(book.getId(), book);
            System.out.println("Kitap bilgileri güncellendi.");
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    public void deleteBook(int id) {
        if (library.getBooks().containsKey(id)) {
            library.removeBook(id);
            System.out.println("Kitap silindi.");
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }
}