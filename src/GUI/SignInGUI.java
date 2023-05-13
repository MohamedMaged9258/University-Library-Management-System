package GUI;

import Classes.Librarian;
import Classes.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SignInGUI extends JFrame {
    private ArrayList<Student> students;
    private ArrayList<Librarian> librarians;
    private JTextField idField;
    private JPasswordField passwordField;

    public SignInGUI(ArrayList<Student> students, ArrayList<Librarian> librarians) {
        this.students = students;
        this.librarians = librarians;
        setTitle("Sign In");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        JButton signInButton = new JButton("Sign In");

        panel.add(idLabel);
        panel.add(idField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(signInButton);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String password = new String(passwordField.getPassword());
                String userType = checkSignInCredentials(id, password);
                if (userType != null) {
                    JOptionPane.showMessageDialog(SignInGUI.this, "Sign in success. Welcome " + getUserFullName(userType));
                    setVisible(false);
                    openMainMenu(userType);
                } else {
                    JOptionPane.showMessageDialog(SignInGUI.this, "Invalid ID or password. Please try again.");
                    idField.setText("");
                    passwordField.setText("");
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    private String checkSignInCredentials(String id, String password) {
        for (Student student : students) {
            if (student.getId().equals(id) && student.getPassword().equals(password)) {
                return "student";
            }
        }
        for (Librarian librarian : librarians) {
            if (librarian.getId().equals(id) && librarian.getPassword().equals(password)) {
                return "librarian";
            }
        }
        return null;
    }

    private String getUserFullName(String userType) {
        String id = idField.getText();
        String fullName = "";
        if (userType.equals("student")) {
            for (Student student : students) {
                if (student.getId().equals(id)) {
                    fullName = student.getName();
                    break;
                }
            }
        } else if (userType.equals("librarian")) {
            for (Librarian librarian : librarians) {
                if (librarian.getId().equals(id)) {
                    fullName = librarian.getName();
                    break;
                }
            }
        }
        return fullName;
    }

    private void openMainMenu(String userType) {
        if (userType.equals("student")) {
            // Open student main menu
        } else if (userType.equals("librarian")) {
            // Open librarian main menu
        }
    }
}
