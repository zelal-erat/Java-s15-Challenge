package service;

import models.Library;
import models.Reader;

import java.util.Map;

public class ReaderService {
    // Library singleton'ındaki readers Map'ine erişim
    private Map<Integer, Reader> readers = Library.getInstance().getReaders();

    // Belirtilen ID ve isim ile yeni bir okuyucu ekler
    public void addReader(int id, String name) {
        if (readers.containsKey(id)) {
            System.out.println("Bu ID'ye sahip bir okuyucu zaten var!");
            return;
        }
        Reader reader = new Reader(name, id);
        readers.put(id, reader);
        System.out.println("Okuyucu başarıyla eklendi.");
    }

    // Belirtilen ID'ye sahip okuyucuyu siler
    public void deleteReader(int id) {
        Reader removed = readers.remove(id);
        if (removed == null) {
            System.out.println("Bu ID'ye ait okuyucu bulunamadı!");
        } else {
            System.out.println("Okuyucu başarıyla silindi.");
        }
    }

    // Tüm okuyucuların bulunduğu Map'i döndürür
    public Map<Integer, Reader> getAllReaders() {
        return readers;
    }
}
