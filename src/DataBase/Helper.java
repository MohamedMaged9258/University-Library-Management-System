package DataBase;

import Classes.Librarian;
import Classes.Student;

import java.sql.*;

public class Helper {
    static String url = "jdbc:mysql://127.0.0.1:3306/main";
    static String user = "root";
    static String password = "root";

    private static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static ResultSet executeQuery(String query) {
        Connection connection = getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeUpdate(String query) {
        Connection connection = getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Librarian Methods
    public void newLibrarian(Librarian librarian) {
        String query = "insert into librarian (ID, Email, Name, Password)" + "values ('" + librarian.getId() + "', '" + librarian.getEmail() + "', '" + librarian.getName() + "', '" + librarian.getPassword() + "')";
        executeUpdate(query);
    }

    public Object getLibrarian(String userId, String password) {
        Object librarian;
        String query = "select * from librarian where id='" + userId + "' and password='" + password + "'";
        ResultSet resultSet = executeQuery(query);
        try {
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

    //Student Methods
    public void newStudent(Student student) {
        Connection connection = getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            String query = "insert into student (ID, Email, Name, Password)" + "values ('" + student.getId() + "', '" + student.getEmail() + "', '" + student.getName() + "', '" + student.getPassword() + "')";
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getStudent(String userId, String password) {
        Connection connection = getConnection();
        Object student;
        Statement statement;
        try {
            statement = connection.createStatement();
            String query = "select * from student where id='" + userId + "' and password='" + password + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                student = new Student(resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("id"));
            } else {
                student = null;
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }
}
