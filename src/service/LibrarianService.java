// service/LibrarianService.java
package service;

import models.Library;
import models.Librarian;

import java.util.Map;

public class LibrarianService {
    private Map<Integer, Librarian> librarians = Library.getInstance().getLibrarian();
    // Dilerseniz ayrı bir Map<id,Librarian> tutabilirsiniz.

    public void addLibrarian(int id, String name) {
        if (librarians.containsKey(id)) {
            System.out.println("Bu ID'ye sahip bir kütüphaneci zaten var!");
            return;
        }
        librarians.put(id, new Librarian(name, id));
        System.out.println("Kütüphaneci eklendi: " + name);
    }

    public boolean login(int id, String name) {
        Librarian lib = librarians.get(id);
        if (lib != null && lib.getName().equalsIgnoreCase(name)) {
            System.out.println("Giriş başarılı. Hoşgeldiniz, " + lib.getName());
            return true;
        }
        System.out.println("Giriş başarısız! ID veya isim hatalı.");
        return false;
    }
}
