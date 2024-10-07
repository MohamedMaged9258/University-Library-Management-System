package DataBase;

import java.sql.*;

public class Helper {
    static String url = "jdbc:mysql://127.0.0.1:3306/main";
    static String user = "root";
    static String password = "root";

    public static Connection getConnection() {
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

    public static String generate_id(char userType) {
        String query;
        String output;
        if (userType == 's') {
            query = "select id from student order by id desc limit 1;";
            output = "SD";
        } else {
            query = "select id from librarian order by id desc limit 1;";
            output = "ST";
        }
        ResultSet resultSet = Helper.executeQuery(query);
        String count;
        int id;
        try {
            resultSet.next();
            count = resultSet.getString(1);
            id = Integer.parseInt(count.substring(2, 6));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return output + String.format("%04d", (id + 1));
    }
}