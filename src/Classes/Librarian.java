package Classes;

import DataBase.Helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static DataBase.Helper.executeQuery;
import static DataBase.Helper.executeUpdate;

public class Librarian extends User {
    public Librarian() {
    }

    public Librarian(String name, String email, String password, String id) {
        super(name, email, password, id);
    }

    public Librarian(String name, String email, String password) {
        super(name, email, password, generate_id());
    }

    //Methods
    public static String generate_id() {
        String query = "select count(*) from librarian";
        ResultSet resultSet = Helper.executeQuery(query);
        int count;
        try {
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "ST" + String.format("%04d", (count + 1));
    }

    public static void newLibrarian(Librarian librarian) {
        String query = "insert into librarian (ID, Email, Name, Password)" + "values ('" + librarian.getId() + "', '" + librarian.getEmail() + "', '" + librarian.getName() + "', '" + librarian.getPassword() + "')";
        executeUpdate(query);
    }

    public static Librarian getLibrarian(String userId, String password) {
        Librarian librarian;
        String query = "select * from librarian where id= ? and password= ?";
        Connection connection = Helper.getConnection();
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                librarian = new Librarian(resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("id"));
            } else {
                librarian = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return librarian;
    }

    public static void CheckStudentBorrowedBooksByID(String userId) {
        String query = "select * from main.borrowed_books inner join main.books on borrowed_books.ISBN = main.books.ISBN where Student_ID='" + userId + "'";
        ResultSet resultSet = executeQuery(query);
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
                    System.out.println("    Due Date: " + resultSet.getString("due_date"));
                    System.out.println("---------------------------------------------------");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addNewBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author Name: ");
        String authorName = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String ISBN = scanner.next();
        Date publicationDate = Date.enterDate();
        System.out.print("Enter the Number Of Copies: ");
        int numOfCopies = scanner.nextInt();
        System.out.print("Enter break Due Date fines: ");
        String breakDueDate = scanner.next();
        System.out.print("Enter if user lost the book how much he will pay: ");
        String lost = scanner.next();
        String query = "insert into books (ISBN, title, Author_Name, Publication_Date, Copies, Delay_Fine, Lost_Fine)" + "values ('" + ISBN + "', '" + title + "', '" + authorName + "', '" + publicationDate + "', '" + numOfCopies + "', '" + breakDueDate + "', '" + lost + "');";
        executeUpdate(query);
    }

    public void showInfo() {
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getId());
        System.out.println("Email: " + getEmail());
    }

    @Override
    public String toString() {
        return getName() + "/" +
                getEmail() + "/" +
                getPassword() + "/" +
                getId() + "\n";
    }
}
