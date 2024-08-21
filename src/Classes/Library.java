package Classes;

import DataBase.Helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Library {
    private ArrayList<Book> bookArrayList;
    private ArrayList<Student> studentArrayList;
    private ArrayList<Librarian> librarianArrayList;
    private ArrayList<Book> lostBookArrayList;
    private ArrayList<Book> borrowedBookArrayList;

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

    public ArrayList<Book> getLostBookArrayList() {
        return lostBookArrayList;
    }

    public ArrayList<Book> getBorrowedBookArrayList() {
        return borrowedBookArrayList;
    }

    // Methods


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

    public static void CheckBooks() {
        String query = "select * from books";
        listBooks(query);
    }

    public static void SortByTitle() {
        String query = "select * from books order by title";
        listBooks(query);
    }

    public static void SortByAuthorName() {
        String query = "select * from books order by author_name";
        listBooks(query);
    }

    public static void SortByISBN() {
        String query = "select * from books order by isbn";
        listBooks(query);
    }

    private static void listBooks(String query) {
        ResultSet resultSet;
        try {
            resultSet = Helper.executeQuery(query);
            int i = 0;
            if (!resultSet.isBeforeFirst()) {
                System.out.println("There is No books");
            } else {
                while (resultSet.next()) {
                    i++;
                    System.out.println("Book " + i + ": ");
                    System.out.println("    Title: " + resultSet.getString("title"));
                    System.out.println("    Author Name: " + resultSet.getString("author_name"));
                    System.out.println("    ISBN: " + resultSet.getString("isbn"));
                    System.out.println("---------------------------------------------------");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void presentBook(Book book) {
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author Name: " + book.getAuthorName());
        System.out.println("ISBN: " + book.getISBN());
        System.out.println("Number Of Available: " + (book.getNumOfCopies() - 1));
        System.out.println("If you return the book after Due Date you will Pay: " + book.getBreakDueDate());
        System.out.println("If you lost the book you will Pay: " + book.getLost());
        System.out.println("----------------------------------------");
    }

    public static void CheckBorrowedBooks() {
        String query = "select * from main.borrowed_books inner join main.books on borrowed_books.ISBN = main.books.ISBN";
        ResultSet resultSet = Helper.executeQuery(query);
        int i = 0;
        try {
            if (!resultSet.isBeforeFirst()) {
                System.out.println("There is No borrowed books");
            } else {
                while (resultSet.next()) {
                    i++;
                    System.out.println("Book " + i + ": ");
                    System.out.println("    Title: " + resultSet.getString("title"));
                    System.out.println("    Author Name: " + resultSet.getString("author_name"));
                    System.out.println("    ISBN: " + resultSet.getString("isbn"));
                    System.out.println("    Borrowed By: " + resultSet.getString("student_id"));
                    System.out.println("    Due Date: " + resultSet.getString("due_date"));
                    System.out.println("---------------------------------------------------");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void CheckLostBooks() {
        String query = "select * from main.books join main.lost_books on lost_books.isbn = main.books.ISBN";
        ResultSet resultSet = Helper.executeQuery(query);
        int i = 0;
        try {
            if (!resultSet.isBeforeFirst()) {
                System.out.println("There is No Lost books");
            } else {
                while (resultSet.next()) {
                    i++;
                    System.out.println("Book " + i + ": ");
                    System.out.println("    Title: " + resultSet.getString("title"));
                    System.out.println("    Author Name: " + resultSet.getString("author_name"));
                    System.out.println("    ISBN: " + resultSet.getString("isbn"));
                    System.out.println("    Lost By: " + resultSet.getString("student_id"));
                    System.out.println("---------------------------------------------------");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void presentStudents() {
        String query = "select * from main.student";
        try {
            ResultSet resultSet = Helper.executeQuery(query);
            int i = 0;
            while (resultSet.next()) {
                i++;
                System.out.println("Student " + i + ": ");
                System.out.println("    Name: " + resultSet.getString("name"));
                System.out.println("    Id: " + resultSet.getString("id"));
                System.out.println("---------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ;
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