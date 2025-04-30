package service;

import models.Books;
import models.Invoice;
import models.Library;
import models.Reader;

import java.time.LocalDate;

public class LibraryService {
    private Library library = Library.getInstance();

    // Synchronized veya AtomicInteger kullanılmadan tanımlanan fatura sayacı
    private static int invoiceCounter = 0;

    // Synchronized olmadan fatura numarası üretimi
    private static int getNextInvoiceId() {
        return ++invoiceCounter;
    }

    // Kitap ödünç alma işlemi
    public void borrowBook(int bookId, int readerId) {
        Books book = library.getBooks().get(bookId);
        Reader reader = library.getReaders().get(readerId);

        if (book == null || reader == null) {
            System.out.println("Kitap veya okuyucu bulunamadı!");
            return;
        }

        if (!book.isAvailable()) {
            // Basit fatura numarası oluşturuluyor
            int invoiceId = getNextInvoiceId();
            Invoice invoice = new Invoice(invoiceId, readerId, bookId, LocalDate.now(), book.getPrice());
            library.getInvoices().put(invoiceId, invoice);

            // Kitabı ödünç al
            book.borrowBook(reader);
        } else {
            System.out.println("Kitap zaten ödünç alınmış!");
        }
    }

    // Kitap iade etme işlemi
    public void returnBook(int bookId, int readerId) {
        Books book = library.getBooks().get(bookId);
        Reader reader = library.getReaders().get(readerId);

        if (book == null || reader == null) {
            System.out.println("Kitap veya okuyucu bulunamadı!");
            return;
        }

        if (book.isAvailable()) {
            // İlgili faturayı bulup güncelliyoruz (iade tarihi ve iade ücreti)
            for (Invoice inv : library.getInvoices().values()) {
                if (inv.getBookId() == bookId && inv.getReaderId() == readerId && inv.getReturnDate() == null) {
                    inv.setReturnDate(LocalDate.now());
                    // Örneğin iade edildiğinde ücret iadesi yapılabilir, burada 0 olarak belirleniyor
                    inv.setAmount(0);
                    break;
                }
            }
            // Kitabı iade et
            book.returnBook(reader);
        } else {
            System.out.println("Kitap zaten kütüphanede!");
        }
    }
}