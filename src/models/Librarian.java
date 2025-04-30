package models;



public class Librarian extends Person {
    private int employeeId;

    public Librarian(String name, int employeeId) {
        super(name);
        this.employeeId=employeeId;
    }


    // Getter & Setter
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public void whoYouAre() {
        System.out.println("I am a librarian: " + name);

    }

    // Kütüphaneciye özgü metodlar (örnek: kitap ekleme, silme)
}
