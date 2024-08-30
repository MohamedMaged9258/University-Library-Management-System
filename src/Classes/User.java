package Classes;

import DataBase.Helper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String name;
    private String email;
    private String password;
    private String id;

    public User() {
    }

    public User(String name, String email, String password, String id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public User(String name, String email, String password, char user_type) {
        this.name = name;
        this.email = email;
        this.password = password;
        String query;
        ResultSet resultSet;
        int count;
        if (user_type == 's') {
            query = "select count(*) from student";
            resultSet = Helper.executeQuery(query);
            try {
                resultSet.next();
                count = resultSet.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            this.id = "SD" + String.format("%04d", (count + 1));
        } else {
            query = "select count(*) from librarian";
            resultSet = Helper.executeQuery(query);
            try {
                resultSet.next();
                count = resultSet.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            this.id = "ST" + String.format("%04d", (count + 1));
        }
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }
}
