package Classes;

import static DataBase.Helper.generate_id;

public class Student extends User {
    private int borrowedBooks;
    private final int maximumNumberOfBooksBorrowed = 5;
    private int fines = 0;

    public Student() {
    }

    public Student(String name, String email, String password) {
        super(name, email, password, generate_id('s'));
    }

    public Student(String name, String email, String password, String id, int borrowedBooks, int fines) {
        super(name, email, password, id);
        this.borrowedBooks = borrowedBooks;
        this.fines = fines;
    }

    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    public int getMaximumNumberOfBooksBorrowed() {
        return maximumNumberOfBooksBorrowed;
    }

    public String getFines() {
        return "$" + fines;
    }

    public int getIntFines(){
        return fines;
    }

    public void setBorrowedBooks(int borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public void setFines(int fines) {
        this.fines = fines;
    }

    // Methods
    public void showInfo() {
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getId());
        System.out.println("email: " + getEmail());
        System.out.println("Borrowed Books Numbers: " + borrowedBooks);
    }

    @Override
    public String toString() {
        return getName() + "," +
                getId() + "," +
                getEmail() + "," +
                getPassword() + "," +
                borrowedBooks + "," +
                maximumNumberOfBooksBorrowed + "," +
                fines + "\n";
    }
}
