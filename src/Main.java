import models.*;
import service.BookService;
import service.LibraryService;
import service.ReaderService;
import service.LibrarianService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static BookService bookService = new BookService();
    private static LibraryService libraryService = new LibraryService();
    private static ReaderService readerService = new ReaderService();
    private static LibrarianService librarianService = new LibrarianService();

    public static void main(String[] args) {
        initializeData();

        while (true) {
            System.out.println("\n=== GİRİŞ MENÜSÜ ===");
            System.out.println("1. Kütüphaneci Girişi");
            System.out.println("2. Okuyucu Girişi");
            System.out.println("3. Yeni Kütüphaneci Ekle");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");

            int role;
            try {
                role = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lütfen geçerli bir sayı giriniz.");
                continue;
            }

            if (role == 0) {
                System.out.println("Programdan çıkılıyor...");
                break;
            }

            switch (role) {
                case 1:
                    librarianMenu();
                    break;
                case 2:
                    readerMenu();
                    break;
                case 3:
                    addLibrarian();
                    break;

                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private static void librarianMenu() {
        System.out.print("Kütüphaneci ID: ");
        int libId = Integer.parseInt(scanner.nextLine());
        System.out.print("Kütüphaneci Adı: ");
        String libName = scanner.nextLine();

        if (!librarianService.login(libId, libName)) {
            System.out.println("Giriş başarısız.");
            return;
        }

        boolean exit = false;
        while (!exit) {
            printLibrarianMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: addBook(); break;
                    case 2: searchBookById(); break;
                    case 3: searchBookByTitle(); break;
                    case 4: searchBookByAuthor(); break;
                    case 5: updateBook(); break;
                    case 6: deleteBook(); break;
                    case 7: listBooksByCategory(); break;
                    case 8: listBooksByAuthor(); break;
                    case 9: borrowBook(); break;
                    case 10: returnBook(); break;
                    case 11: addReader(); break;
                    case 12: deleteReader(); break;
                    case 13: exit = true; break;
                    default: System.out.println("Geçersiz seçim!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lütfen sayısal bir seçim giriniz!");
            }
        }
    }

    private static void readerMenu() {
        boolean exit = false;
        while (!exit) {
            printReaderMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: searchBookById(); break;
                    case 2: searchBookByTitle(); break;
                    case 3: searchBookByAuthor(); break;
                    case 4: listBooksByCategory(); break;
                    case 5: listBooksByAuthor(); break;
                    case 6: borrowBook(); break;
                    case 7: returnBook(); break;
                    case 8: exit = true; break;
                    default: System.out.println("Geçersiz seçim!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lütfen sayısal bir seçim giriniz!");
            }
        }
    }
    private static void addLibrarian() {
        try {
            System.out.print("Yeni Kütüphaneci ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (Library.getInstance().getLibrarian().containsKey(id)) {
                System.out.println("Bu ID'ye sahip bir kütüphaneci zaten var!");
                return;
            }
            System.out.print("Yeni Kütüphaneci Adı: ");
            String name = scanner.nextLine();
            librarianService.addLibrarian(id, name);
        } catch (NumberFormatException e) {
            System.out.println("Hatalı giriş! Lütfen sayısal bir ID giriniz.");
        }
    }


    private static void printLibrarianMenu() {
        System.out.println("\n===== KÜTÜPHANECİ MENÜSÜ =====");
        System.out.println("1. Yeni Kitap Ekle");
        System.out.println("2. Kitap Ara (ID ile)");
        System.out.println("3. Kitap Ara (İsim ile)");
        System.out.println("4. Kitap Ara (Yazar ile)");
        System.out.println("5. Kitap Güncelle");
        System.out.println("6. Kitap Sil");
        System.out.println("7. Kategoriye Göre Kitapları Listele");
        System.out.println("8. Yazara Göre Kitapları Listele");
        System.out.println("9. Kitap Ödünç Ver");
        System.out.println("10. Kitap İade Al");
        System.out.println("11. Okuyucu Ekle");
        System.out.println("12. Okuyucu Sil");
        System.out.println("13. Geri Dön");
        System.out.print("Seçiminiz: ");
    }

    private static void printReaderMenu() {
        System.out.println("\n===== OKUYUCU MENÜSÜ =====");
        System.out.println("1. Kitap Ara (ID ile)");
        System.out.println("2. Kitap Ara (İsim ile)");
        System.out.println("3. Kitap Ara (Yazar ile)");
        System.out.println("4. Kategoriye Göre Kitapları Listele");
        System.out.println("5. Yazara Göre Kitapları Listele");
        System.out.println("6. Kitap Ödünç Al");
        System.out.println("7. Kitap İade Et");
        System.out.println("8. Geri Dön");
        System.out.print("Seçiminiz: ");
    }

    private static void addBook() {
        try {
            System.out.print("Kitap ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            if (Library.getInstance().getBooks().containsKey(id)) {
                System.out.println("Bu ID'ye sahip bir kitap zaten mevcut! Lütfen farklı bir ID giriniz.");
                return;
            }

            System.out.print("Kitap Başlığı: ");
            String title = scanner.nextLine();

            System.out.print("Yazar Adı: ");
            String authorName = scanner.nextLine();
            Author author = new Author(authorName);

            System.out.print("Kategori (FICTION, SCIENCE, MAGAZINE, JOURNAL, STUDYBOOK): ");
            String catInput = scanner.nextLine().toUpperCase();
            Category category = Category.valueOf(catInput);

            System.out.print("Fiyat: ");
            double price = Double.parseDouble(scanner.nextLine());

            Books book = new Books(id, title, author, category, price);
            bookService.addBook(book);

        } catch (NumberFormatException e) {
            System.out.println("Hatalı giriş! Lütfen sayısal bir ID ve fiyat giriniz.");
        } catch (Exception e) {
            System.out.println("Hatalı giriş! Lütfen bilgileri kontrol ediniz.");
        }
    }

    private static void searchBookById() {
        try {
            System.out.print("Kitap ID giriniz: ");
            int id = Integer.parseInt(scanner.nextLine());
            Books book = bookService.findBookById(id);
            if (book == null) {
                System.out.println("Kitap bulunamadı.");
            } else {
                System.out.println("Kitap ID: " + book.getId() + ", Başlık: " + book.getTitle() +
                        ", Yazar: " + book.getAuthor().getName() + ", Kategori: " + book.getCategory());
            }
        } catch (NumberFormatException e) {
            System.out.println("Lütfen sayısal bir ID giriniz!");
        }
    }

    private static void searchBookByTitle() {
        System.out.print("Kitap ismi giriniz: ");
        String title = scanner.nextLine();
        List<Books> foundBooks = bookService.findBookByTitle(title);
        if (foundBooks.isEmpty()) {
            System.out.println("Hiçbir kitap bulunamadı.");
        } else {
            for (Books b : foundBooks) {
                System.out.println("Kitap ID: " + b.getId() + ", Başlık: " + b.getTitle() +
                        ", Yazar: " + b.getAuthor().getName() + ", Kategori: " + b.getCategory());
            }
        }
    }

    private static void searchBookByAuthor() {
        System.out.print("Yazar ismi giriniz: ");
        String authorName = scanner.nextLine();
        List<Books> books = bookService.findBookByAuthor(authorName);
        if (books.isEmpty()) {
            System.out.println("Bu yazara ait kitap bulunamadı.");
        } else {
            for (Books b : books) {
                System.out.println("Kitap ID: " + b.getId() + ", Başlık: " + b.getTitle() +
                        ", Yazar: " + b.getAuthor().getName() + ", Kategori: " + b.getCategory());
            }
        }
    }

    private static void updateBook() {
        try {
            System.out.print("Güncellenecek kitap ID'si: ");
            int id = Integer.parseInt(scanner.nextLine());
            Books book = bookService.findBookById(id);

            if (book == null) {
                System.out.println("Kitap bulunamadı!");
                return;
            }

            System.out.print("Yeni Başlık (mevcut: " + book.getTitle() + "): ");
            String title = scanner.nextLine();
            if (title.isEmpty()) title = book.getTitle();

            System.out.print("Yeni Yazar Adı (mevcut: " + book.getAuthor().getName() + "): ");
            String authorName = scanner.nextLine();
            Author author = authorName.isEmpty() ? book.getAuthor() : new Author(authorName);

            System.out.print("Yeni Kategori (mevcut: " + book.getCategory() + ") (FICTION, SCIENCE, MAGAZINE, JOURNAL, STUDYBOOK): ");
            String categoryInput = scanner.nextLine().toUpperCase();
            Category category = categoryInput.isEmpty() ? book.getCategory() : Category.valueOf(categoryInput);

            System.out.print("Yeni Fiyat (mevcut: " + book.getPrice() + "): ");
            String priceInput = scanner.nextLine();
            double price = priceInput.isEmpty() ? book.getPrice() : Double.parseDouble(priceInput);

            Books updatedBook = new Books(id, title, author, category, price);
            bookService.updateBook(updatedBook);

            System.out.println("Kitap başarıyla güncellendi.");
        } catch (NumberFormatException e) {
            System.out.println("Hatalı giriş! Lütfen sayısal bir ID veya fiyat giriniz.");
        } catch (Exception e) {
            System.out.println("Güncelleme sırasında hata oluştu!");
        }
    }

    private static void deleteBook() {
        try {
            System.out.print("Silinecek kitap ID'si: ");
            int id = Integer.parseInt(scanner.nextLine());
            bookService.deleteBook(id);
        } catch (NumberFormatException e) {
            System.out.println("Lütfen sayısal bir ID giriniz!");
        } catch (Exception e) {
            System.out.println("Silme işlemi sırasında hata oluştu!");
        }
    }

    private static void listBooksByCategory() {
        System.out.print("Listelemek istediğiniz kategori (FICTION, SCIENCE, MAGAZINE, JOURNAL, STUDYBOOK): ");
        try {
            String catInput = scanner.nextLine().toUpperCase();
            Category category = Category.valueOf(catInput);
            boolean found = false;
            for (Books b : Library.getInstance().getBooks().values()) {
                if (b.getCategory() == category) {
                    System.out.println("Kitap ID: " + b.getId() + ", Başlık: " + b.getTitle());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Bu kategoride kitap bulunamadı.");
            }
        } catch (Exception e) {
            System.out.println("Geçersiz kategori girişi!");
        }
    }

    private static void listBooksByAuthor() {
        System.out.print("Yazar adı: ");
        String authorName = scanner.nextLine();
        List<Books> books = bookService.findBookByAuthor(authorName);
        if (books.isEmpty()) {
            System.out.println("Bu yazara ait kitap bulunamadı.");
        } else {
            for (Books b : books) {
                System.out.println("Kitap ID: " + b.getId() + ", Başlık: " + b.getTitle());
            }
        }
    }

    private static void borrowBook() {
        try {
            System.out.print("Ödünç alınacak kitap ID'si: ");
            int bookId = Integer.parseInt(scanner.nextLine());
            System.out.print("Okuyucu ID'si: ");
            int readerId = Integer.parseInt(scanner.nextLine());

            libraryService.borrowBook(bookId, readerId);

            Books book = bookService.findBookById(bookId);
            Reader reader = Library.getInstance().getReaders().get(readerId);

            if (book != null && reader != null) {
                System.out.println("\n--- ÖDÜNÇ ALMA FATURASI ---");
                System.out.println("Kitap: " + book.getTitle());
                System.out.println("Yazar: " + book.getAuthor().getName());
                System.out.println("Fiyat: " + book.getPrice() + " TL");
                System.out.println("Okuyucu: " + reader.getName());
                System.out.println("İşlem: Ödünç Alma");
                System.out.println("Tarih: " + java.time.LocalDate.now());
                System.out.println("----------------------------\n");
            }

        } catch (NumberFormatException e) {
            System.out.println("Lütfen sayısal bir ID giriniz!");
        } catch (Exception e) {
            System.out.println("Ödünç alma işlemi sırasında hata oluştu!");
        }
    }

    private static void returnBook() {
        try {
            System.out.print("İade edilecek kitap ID'si: ");
            int bookId = Integer.parseInt(scanner.nextLine());
            System.out.print("Okuyucu ID'si: ");
            int readerId = Integer.parseInt(scanner.nextLine());

            libraryService.returnBook(bookId, readerId);

            Books book = bookService.findBookById(bookId);
            Reader reader = Library.getInstance().getReaders().get(readerId);

            if (book != null && reader != null) {
                System.out.println("\n--- İADE FATURASI ---");
                System.out.println("Kitap: " + book.getTitle());
                System.out.println("Yazar: " + book.getAuthor().getName());
                System.out.println("Fiyat: " + book.getPrice() + " TL");
                System.out.println("Okuyucu: " + reader.getName());
                System.out.println("İşlem: Kitap İade");
                System.out.println("Tarih: " + java.time.LocalDate.now());
                System.out.println("----------------------------\n");
            }

        } catch (NumberFormatException e) {
            System.out.println("Lütfen sayısal bir ID giriniz!");
        } catch (Exception e) {
            System.out.println("İade işlemi sırasında hata oluştu!");
        }
    }

    private static void addReader() {
        try {
            System.out.print("Okuyucu ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (Library.getInstance().getReaders().containsKey(id)) {
                System.out.println("Bu ID'ye sahip bir okuyucu zaten mevcut!");
                return;
            }
            System.out.print("Okuyucu Adı: ");
            String name = scanner.nextLine();
            readerService.addReader(id, name);
        } catch (NumberFormatException e) {
            System.out.println("Hatalı giriş! Lütfen sayısal bir ID giriniz.");
        }
    }

    private static void deleteReader() {
        try {
            System.out.print("Silinecek okuyucu ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            readerService.deleteReader(id);
        } catch (NumberFormatException e) {
            System.out.println("Hatalı giriş! Lütfen sayısal bir ID giriniz.");
        } catch (Exception e) {
            System.out.println("Okuyucu silme işlemi sırasında hata oluştu!");
        }
    }

    private static void initializeData() {
        Library.getInstance().getReaders().put(1, new Reader("Ali Veli", 1));
        Library.getInstance().getReaders().put(2, new Reader("Ayşe Fatma", 2));

        librarianService.addLibrarian(1, "Murat");
        librarianService.addLibrarian(2, "Zeynep");

        Author author1 = new Author("Orhan Pamuk");
        Books book1 = new Books(101, "Benim Adım Kırmızı", author1, Category.FICTION, 25.0);
        bookService.addBook(book1);

        Author author2 = new Author("Stephen Hawking");
        Books book2 = new Books(102, "Kısa Cevaplar", author2, Category.SCIENCE, 30.0);
        bookService.addBook(book2);
    }
}
