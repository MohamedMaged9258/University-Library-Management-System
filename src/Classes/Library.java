package Classes;

import java.util.ArrayList;

public class Library {
    ArrayList<Book> bookArrayList = new ArrayList<>();
    ArrayList<Student> studentArrayList = new ArrayList<>();
    ArrayList<Staff> staffArrayList = new ArrayList<>();
    ArrayList<Book> lostBookArrayList = new ArrayList<>();
    ArrayList<Book> borrowedBookArrayList = new ArrayList<>();

    public Library(ArrayList<Student> studentArrayList, ArrayList<Staff> staffArrayList, ArrayList<Book> bookArrayList, ArrayList<Book> lostBookArrayList, ArrayList<Book> borrowedBookArrayList) {
        this.bookArrayList = bookArrayList;
        this.studentArrayList = studentArrayList;
        this.staffArrayList = staffArrayList;
        this.lostBookArrayList = lostBookArrayList;
        this.borrowedBookArrayList = borrowedBookArrayList;
    }

    // Getters
    public ArrayList<Book> getBookArrayList() {
        return bookArrayList;
    }
    public ArrayList<Student> getStudentArrayList() {
        return studentArrayList;
    }
    public ArrayList<Staff> getStaffArrayList() {
        return staffArrayList;
    }
    public ArrayList<Book> getLostBookArrayList() {
        return lostBookArrayList;
    }
    public ArrayList<Book> getBorrowedBookArrayList() {
        return borrowedBookArrayList;
    }

    // Methods
    public Object searchBookByTitle(String title){
        for (Book value : bookArrayList) {
            if (value.getTitle().equals(title)) {
                return value;
            }
        }
        return "Not Available";
    }
    public Object searchBookByAuthorName(String authorName){
        for (Book value : bookArrayList) {
            if (value.getAuthorName().equals(authorName)) {
                return value;
            }
        }
        return "Not Available";
    }
    public Object searchBookByISPN(int ISBN){
        for (Book value : bookArrayList) {
            if (value.getISBN() == ISBN) {
                return value;
            }
        }
        return "Not Available";
    }
    public void presentBooks(){
        System.out.println("Library Books is :");
        for (int i = 0; i < bookArrayList.size(); i++) {
            System.out.println((i+1) + "." + bookArrayList.get(i));
        }
    }
    public void presentBorrowedBooks(){
        System.out.println("Your Borrowed Books is :");
        for (int i = 0; i < borrowedBookArrayList.size(); i++) {
            System.out.println((i+1) + "." + borrowedBookArrayList.get(i));
        }
    }
    public void presentLostBooks(){
        System.out.println("Your Lost Books is :");
        for (int i = 0; i < lostBookArrayList.size(); i++) {
            System.out.println((i+1) + "." + lostBookArrayList.get(i));
        }
    }
    public void addToBorrowedBooksList(Book book){
        borrowedBookArrayList.add(book);
        book.setBorrowed(true);
        book.setDueDate(Date.setDueDate());
    }
    public void returnBook(Book book){
        borrowedBookArrayList.remove(book);
        book.setBorrowed(false);
        book.setDueDate(new Date("0000","00","00"));
    }

}
