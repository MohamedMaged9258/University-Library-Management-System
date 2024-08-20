package DataBase;

import Classes.Librarian;
import Classes.Student;

import java.sql.*;

public class Helper {
    String url = "jdbc:mysql://127.0.0.1:3306/main";
    String user = "root";
    String password = "root";

    private Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    url,
                    user,
                    password
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    //Librarian Methods
    public void newLibrarian(Librarian librarian) {
        Connection connection = getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            String query = "insert into librarian (ID, Email, Name, Password)" +
                    "values ('" + librarian.getId() + "', '" + librarian.getEmail() + "', '" + librarian.getName() + "', '" + librarian.getPassword() + "')";
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getLibrarian(String userId, String password) {
        Connection connection = getConnection();
        Object librarian;
        Statement statement;
        try {
            statement = connection.createStatement();
            String query = "select * from librarian where id='" + userId + "' and password='" + password + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                librarian = new Librarian(resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("id"));
            }else {
                librarian = null;
            }
            statement.close();
            connection.close();
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
            String query = "insert into student (ID, Email, Name, Password)" +
                    "values ('" + student.getId() + "', '" + student.getEmail() + "', '" + student.getName() + "', '" + student.getPassword() + "')";
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
            }else {
                student = null;
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }
}
