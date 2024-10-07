package Classes;

import static DataBase.Helper.generate_id;

public class Librarian extends User {
    public Librarian() {
    }

    public Librarian(String name, String email, String password, String id) {
        super(name, email, password, id);
    }

    public Librarian(String name, String email, String password) {
        super(name, email, password, generate_id('l'));
    }

    //Methods
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
