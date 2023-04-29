package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginBtn;
    private JButton signUpBtn;

    public LoginScreen() {
        // Set up the JFrame
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel for the login form
        JPanel panel = new JPanel(new GridLayout(3, 2));

        // Create username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        panel.add(usernameLabel);
        panel.add(usernameField);

        // Create password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        panel.add(passwordLabel);
        panel.add(passwordField);

        // Create login button
        loginBtn = new JButton("Login");
        loginBtn.addActionListener(this);
        panel.add(loginBtn);

        // Create sign up button
        signUpBtn = new JButton("Sign Up");
        signUpBtn.addActionListener(this);
        panel.add(signUpBtn);

        // Add panel to JFrame
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginBtn) {
            // Handle login button click
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            // Perform login logic here
            JOptionPane.showMessageDialog(this, "Login clicked!\nUsername: " + username + "\nPassword: " + password);
        } else if (e.getSource() == signUpBtn) {
            // Create and display SignUpScreen
            SignUpScreen signUpScreen = new SignUpScreen();
            signUpScreen.setVisible(true);
            this.dispose(); // Close the current LoginScreen window
        }
    }}