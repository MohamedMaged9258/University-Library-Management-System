package Classes;

import DataBase.Helper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Library {

    //Constructors
    public Library() {

    }

    public static Object searchBookByISPN(int ISBN) {
        String query = "SELECT * FROM books WHERE ISBN = '" + ISBN + "'";
        ResultSet resultSet = Helper.executeQuery(query);
        try {
            if (!resultSet.isBeforeFirst()) {
                return "Not Available";
            } else {
                resultSet.next();
                return new Book(resultSet.getString("title"), resultSet.getString("author_Name"), resultSet.getString("isbn"), resultSet.getString("publication_date"), resultSet.getString("delay_fine"), resultSet.getString("lost_fine"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public static void listBooks(String query) {
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
        }
    }
}