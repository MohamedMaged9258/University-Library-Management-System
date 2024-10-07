package GUI;

import Classes.Book;
import Classes.Librarian;
import Classes.Library;
import Classes.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LibraryGUI extends JFrame {
    private ArrayList<Student> studentArrayList;
    private ArrayList<Librarian> librarianArrayList;
    private ArrayList<Book> bookArrayList;
    private ArrayList<Book> lostBookArrayList;
    private ArrayList<Book> borrowedBookArrayList;
    private Student student;
    private Librarian librarian;
    private Library library;

    public JPanel signInPanel;
    private JLabel signInTitleLabel;
    private JLabel signInChoiceLabel;
    private JButton signInButton;
    private JButton signUpButton;

    public JPanel signUpPanel;
    private JLabel signUpTitleLabel;
    private JLabel signUpChoiceLabel;
    private JLabel signUpStudentIDLabel;
    private JTextField signUpStudentIDField;
    private JLabel signUpStudentPasswordLabel;
    private JPasswordField signUpStudentPasswordField;
    private JLabel signUpLibrarianNameLabel;
    private JTextField signUpLibrarianNameField;
    private JLabel signUpLibrarianPasswordLabel;
    private JPasswordField signUpLibrarianPasswordField;
    private JButton signUpStudentButton;
    private JButton signUpLibrarianButton;

    public JPanel studentPanel;
    private JLabel studentTitleLabel;
    private JLabel studentInfoLabel;
    private JButton studentBorrowedBooksButton;
    private JButton studentReturnedBooksButton;
    private JButton studentBorrowBookButton;
    private JButton studentReturnBookButton;
    private JButton studentLostBookButton;
    private JButton studentFinesButton;
    private JButton studentQuitButton;

    public JPanel librarianPanel;
    private JLabel librarianTitleLabel;
    private JLabel librarianInfoLabel;
    private JButton librarianAddBookButton;
    private JButton librarianStudentBorrowedBooksButton;
    private JButton librarianBorrowedBooksButton;
    private JButton librarianLostBooksButton;
    private JButton librarianBooksButton;
    private JButton librarianSortTitleButton;
    private JButton librarianSortAuthorButton;
    private JButton librarianSortISBNButton;
    private JButton librarianQuitButton;

    public LibraryGUI(ArrayList<Student> studentArrayList, ArrayList<Librarian> librarianArrayList,
                      ArrayList<Book> bookArrayList, ArrayList<Book> lostBookArrayList,
                      ArrayList<Book> borrowedBookArrayList) {
        this.studentArrayList = studentArrayList;
        this.librarianArrayList = librarianArrayList;
        this.bookArrayList = bookArrayList;
        this.lostBookArrayList = lostBookArrayList;
        this.borrowedBookArrayList = borrowedBookArrayList;
        this.student = new Student();
        this.librarian = new Librarian();
        this.library = new Library(studentArrayList, librarianArrayList, bookArrayList, lostBookArrayList, borrowedBookArrayList);

        // Set up sign-in panel
        signInPanel = new JPanel(new BorderLayout());
        signInTitleLabel = new JLabel("Welcome to the Library");
        signInTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        signInPanel.add(signInTitleLabel, BorderLayout.NORTH);
        signInChoiceLabel = new JLabel("Please choose an option to proceed:");
        signInPanel.add(signInChoiceLabel, BorderLayout.CENTER);
        signInButton = new JButton("Sign In");
        signUpButton = new JButton("Sign Up");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(signInButton);
        buttonPanel.add(signUpButton);
        signInPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Set up sign-up panel
        signUpPanel = new JPanel(new BorderLayout());
        signUpTitleLabel = new JLabel("Create an Account");
        signUpTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        signUpPanel.add(signUpTitleLabel, BorderLayout.NORTH);
        signUpChoiceLabel = new JLabel("Please choose an account type to create:");
        JPanel signUpTypePanel = new JPanel();

// Add radio buttons for account types
        JRadioButton studentRadioButton = new JRadioButton("Student");
        JRadioButton librarianRadioButton = new JRadioButton("Librarian");
        ButtonGroup accountTypeGroup = new ButtonGroup();
        accountTypeGroup.add(studentRadioButton);
        accountTypeGroup.add(librarianRadioButton);
        signUpTypePanel.add(studentRadioButton);
        signUpTypePanel.add(librarianRadioButton);

// Add text fields and buttons for sign-up
        signUpStudentIDLabel = new JLabel("Student ID:");
        signUpStudentIDField = new JTextField(10);
        signUpStudentPasswordLabel = new JLabel("Password:");
        signUpStudentPasswordField = new JPasswordField(10);
        signUpLibrarianNameLabel = new JLabel("Name:");
        signUpLibrarianNameField = new JTextField(20);
        signUpLibrarianPasswordLabel = new JLabel("Password:");
        signUpLibrarianPasswordField = new JPasswordField(10);
        signUpStudentButton = new JButton("Create Student Account");
        signUpLibrarianButton = new JButton("Create Librarian Account");
        JPanel signUpFormPanel = new JPanel();
        signUpFormPanel.setLayout(new GridLayout(0, 2));
        signUpFormPanel.add(signUpStudentIDLabel);
        signUpFormPanel.add(signUpStudentIDField);
        signUpFormPanel.add(signUpStudentPasswordLabel);
        signUpFormPanel.add(signUpStudentPasswordField);
        signUpFormPanel.add(signUpLibrarianNameLabel);
        signUpFormPanel.add(signUpLibrarianNameField);
        signUpFormPanel.add(signUpLibrarianPasswordLabel);
        signUpFormPanel.add(signUpLibrarianPasswordField);
        JPanel signUpButtonPanel = new JPanel();
        signUpButtonPanel.add(signUpStudentButton);
        signUpButtonPanel.add(signUpLibrarianButton);

// Add components to sign-up panel
        signUpPanel.add(signUpChoiceLabel, BorderLayout.NORTH);
        signUpPanel.add(signUpTypePanel, BorderLayout.CENTER);
        signUpPanel.add(signUpFormPanel, BorderLayout.SOUTH);
        signUpPanel.add(signUpButtonPanel, BorderLayout.SOUTH);

    }
}