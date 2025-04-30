package models;

import java.time.LocalDate;

public class Invoice {
    private int invoiceId;
    private int readerId;
    private int bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private double amount;

    public Invoice(int invoiceId, int readerId, int bookId, LocalDate borrowDate, double amount) {
        this.invoiceId = invoiceId;
        this.readerId = readerId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.amount = amount;
    }

    // Getter & Setter metodları
    public int getInvoiceId() {
        return invoiceId;
    }

    public int getReaderId() {
        return readerId;
    }

    public int getBookId() {
        return bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}