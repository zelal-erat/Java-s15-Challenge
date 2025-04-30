package models;

import java.awt.print.Book;
import java.util.HashSet;
import java.util.Set;

public class Author extends Person {
    private Set<Book> books;
    public Author(String name) {
        super(name);
        this.books = new HashSet<>();
    }



    @Override
    public void whoYouAre() {
        System.out.println("I am an author: " + getName());

    }

}
