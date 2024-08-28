package Classes;

import java.util.Random;

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

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        Random random = new Random();
        this.id = "ST" + String.format("%04d", random.nextInt(10000));
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
