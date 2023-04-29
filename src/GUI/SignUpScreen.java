package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SignUpScreen extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signUpBtn;

    public SignUpScreen() {
        // Set up the JFrame
        setTitle("Sign Up");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel for the sign-up form
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

        // Create sign up button
        signUpBtn = new JButton("Sign Up");
        signUpBtn.addActionListener(this);
        panel.add(signUpBtn);

        // Add panel to JFrame
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpBtn) {
            // Handle sign up button click
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            // Perform sign up logic here
            JOptionPane.showMessageDialog(this, "Sign Up clicked!\nUsername: " + username + "\nPassword: " + password);
        }
    }
}