package DataBase;

import java.sql.*;

public class Helper {
    public static void main(String[] args) {
        Connection connection;
        {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/main",
                        "root",
                        "root"
                );

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from librarian");
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("name"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
