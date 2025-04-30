package models;

public interface Borrowable {
    void borrowBook(Reader reader);
    void returnBook(Reader reader);
}
