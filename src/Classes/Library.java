package Classes;

import java.util.ArrayList;
import java.util.Comparator;

public class Library {
    ArrayList<Book> bookArrayList;
    ArrayList<Student> studentArrayList;
    ArrayList<Librarian> librarianArrayList;
    ArrayList<Book> lostBookArrayList;
    ArrayList<Book> borrowedBookArrayList;

    //Constructors
    public Library(ArrayList<Student> studentArrayList, ArrayList<Librarian> librarianArrayList, ArrayList<Book> bookArrayList, ArrayList<Book> lostBookArrayList, ArrayList<Book> borrowedBookArrayList) {
        this.bookArrayList = bookArrayList;
        this.studentArrayList = studentArrayList;
        this.librarianArrayList = librarianArrayList;
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

    public ArrayList<Librarian> getLibrarianArrayList() {
        return librarianArrayList;
    }

    public ArrayList<Book> getLostBookArrayList() {
        return lostBookArrayList;
    }

    public ArrayList<Book> getBorrowedBookArrayList() {
        return borrowedBookArrayList;
    }

    // Methods
    public void sortBooksByTitle() {
        bookArrayList.sort(new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getTitle().compareTo(b2.getTitle());
            }
        });
    }

    public void sortBooksByAuthorName() {
        bookArrayList.sort(new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getAuthorName().compareTo(b2.getAuthorName());
            }
        });
    }

    public void sortBooksByISBN() {
        bookArrayList.sort(new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getISBN().compareTo(b2.getISBN());
            }
        });
    }

    public void addToStudentList(Student student) {
        this.studentArrayList.add(student);
    }

    public void addToLibrarianList(Librarian librarian) {
        this.librarianArrayList.add(librarian);
    }

    public void addNewBook(Book book) {
        this.bookArrayList.add(book);
    }

    public Object searchBookByTitle(String title) {
        for (Book value : bookArrayList) {
            if (value.getTitle().equals(title)) {
                return value;
            }
        }
        return "Not Available";
    }

    public Object searchBookByAuthorName(String authorName) {
        for (Book value : bookArrayList) {
            if (value.getAuthorName().equals(authorName)) {
                return value;
            }
        }
        return "Not Available";
    }

    public Object searchBookByISPN(String ISBN) {
        for (Book value : bookArrayList) {
            if (value.getISBN().equals(ISBN)) {
                return value;
            }
        }
        return "Not Available";
    }

    public Object searchBorrowedBookByStudentIdAndISBN(String studentId, String isbn) {
        for (Book value : borrowedBookArrayList) {
            if (value.getStudentId().equals(studentId)) {
                if (value.getISBN().equals(isbn)) {
                    return value;
                }
            }
        }
        return "Not Available";
    }

    public void presentBooks() {
        System.out.println("Library Books is :");
        for (int i = 0; i < bookArrayList.size(); i++) {
            System.out.println("Book " + (i + 1) + ": ");
            System.out.println("Title: " + bookArrayList.get(i).getTitle());
            System.out.println("ISBN: " + bookArrayList.get(i).getISBN());
            System.out.println("If you return the book after Due Date you will Pay: " + bookArrayList.get(i).getBreakDueDate());
            System.out.println("If you lost the book you will Pay: " + bookArrayList.get(i).getLost());
            System.out.println("----------------------------------------");
        }
    }

    public void presentBorrowedBooks() {
        System.out.println("The Borrowed Books is :");
        for (int i = 0; i < borrowedBookArrayList.size(); i++) {
            System.out.println("Book " + (i + 1) + ": ");
            System.out.println("Title: " + borrowedBookArrayList.get(i).getTitle());
            System.out.println("Author Name: " + borrowedBookArrayList.get(i).getAuthorName());
            System.out.println("Due Date: " + borrowedBookArrayList.get(i).getDueDate().toString());
            System.out.println("Student ID: " + borrowedBookArrayList.get(i).getStudentId());
            System.out.println("----------------------------------------");
        }
    }

    public void presentLostBooks() {
        System.out.println("The Lost Books is :");
        for (int i = 0; i < lostBookArrayList.size(); i++) {
            System.out.println("Book " + (i + 1) + ": ");
            System.out.println("Title: " + lostBookArrayList.get(i).getTitle());
            System.out.println("Author Name: " + lostBookArrayList.get(i).getAuthorName());
            System.out.println("----------------------------------------");
        }
    }

    public void presentStudents() {
        for (int i = 0; i < studentArrayList.size(); i++) {
            System.out.println("Student " + (i + 1) + ": ");
            System.out.println("Name: " + studentArrayList.get(i).getName());
            System.out.println("Id: " + studentArrayList.get(i).getId());
            System.out.println("----------------------------------------");
        }
    }

    public void addToBorrowedBooksList(Book book) {
        borrowedBookArrayList.add(book);
        bookArrayList.get(bookArrayList.indexOf(book)).numOfCopies--;
    }

    public void returnBook(Book book) {
        if (searchBookByISPN(book.getISBN()) instanceof Book) {
            book = (Book) searchBookByISPN(book.getISBN());
        }
        bookArrayList.get(bookArrayList.indexOf(book)).numOfCopies++;
        if (searchBorrowedBookByStudentIdAndISBN(book.getStudentId(), book.getISBN()) instanceof Book) {
            book = (Book) searchBorrowedBookByStudentIdAndISBN(book.getStudentId(), book.getISBN());
        }
        borrowedBookArrayList.remove(book);
    }

    public static void saveNewFiles(Library library) {
        for (Student value : library.studentArrayList) {
            Student.saveStudentToFile(value);
        }
        for (Librarian value : library.librarianArrayList) {
            Librarian.saveLibrarianToFile(value);
        }
        for (int i = 0; i < library.getBorrowedBookArrayList().size(); i++) {
            Book.saveBorrowedBookToFile(library.getBorrowedBookArrayList().get(i), true);
        }
        for (int i = 0; i < library.getLostBookArrayList().size(); i++) {
            Book.saveLostBookToFile(library.getLostBookArrayList().get(i), true);
        }
        for (int i = 0; i < library.getBookArrayList().size(); i++) {
            library.getBookArrayList().get(i).setDueDate(Date.resetDueDate());
            library.getBookArrayList().get(i).setStudentId("");
            Book.saveBookToFile(library.getBookArrayList().get(i), true);
        }
    }
}