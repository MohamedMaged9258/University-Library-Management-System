package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Student {
    private String name;
    private String email;
    private String password;
    private String id;
    private int borrowedBooks;
    private final int maximumNumberOfBooksBorrowed = 5;
    private int fines = 0;
    private ArrayList<Book> borrowedBooksList = new ArrayList<>();
    private ArrayList<Book> lostBookArrayList = new ArrayList<>();
    private ArrayList<Book> returnedBookArrayList = new ArrayList<>();

    //Constructors
    public Student() {
    }

    public Student(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = generateId();
    }

    public Student(String name, String email, String password, String id, int borrowedBooks, ArrayList<Book> borrowedBooksList, ArrayList<Book> lostBookArrayList, ArrayList<Book> returnedBookArrayList, int fines) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
        this.borrowedBooks = borrowedBooks;
        this.borrowedBooksList = borrowedBooksList;
        this.lostBookArrayList = lostBookArrayList;
        this.returnedBookArrayList = returnedBookArrayList;
        this.fines = fines;
    }

    // Getters
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

    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    public int getMaximumNumberOfBooksBorrowed() {
        return maximumNumberOfBooksBorrowed;
    }

    public String getFines() {
        return "$" + fines;
    }

    public ArrayList<Book> getBorrowedBooksList() {
        return borrowedBooksList;
    }

    public ArrayList<Book> getLostBookArrayList() {
        return lostBookArrayList;
    }

    public ArrayList<Book> getReturnedBookArrayList() {
        return returnedBookArrayList;
    }

    // Methods
    public Object searchBookByISPN(String ISBN) {
        for (Book value : borrowedBooksList) {
            if (value.getISBN().equals(ISBN)) {
                return value;
            }
        }
        return "Not Available";
    }

    public void presentBorrowedBooks() {
        if (borrowedBooksList.size() != 0) {
            System.out.println("Your Borrowed Books is :");
            for (int i = 0; i < borrowedBooksList.size(); i++) {
                System.out.println("Book " + (i + 1) + ": ");
                System.out.println("Title: " + borrowedBooksList.get(i).getTitle());
                System.out.println("Author Name: " + borrowedBooksList.get(i).getAuthorName());
                System.out.println("Due Date: " + borrowedBooksList.get(i).getDueDate().toString());
                System.out.println("---------------------------------");
            }
        } else System.out.println("You Don't Have Books to Show.");
    }

    public void presentLostBooks() {
        if (lostBookArrayList.size() != 0){
        System.out.println("Your Lost Books is :");
        for (int i = 0; i < lostBookArrayList.size(); i++) {
            System.out.println("Book " + (i + 1) + ": ");
            System.out.println("Title: " + lostBookArrayList.get(i).getTitle());
            System.out.println("Author Name: " + lostBookArrayList.get(i).getAuthorName());
            System.out.println("---------------------------------");
        }}else System.out.println("You Don't Have Books to Show.");
    }

    public void presentReturnedBooks() {
        if (returnedBookArrayList.size() != 0){
        System.out.println("Your Returned Books is :");
        for (int i = 0; i < returnedBookArrayList.size(); i++) {
            System.out.println("Book " + (i + 1) + ": ");
            System.out.println("Title: " + returnedBookArrayList.get(i).getTitle());
            System.out.println("Author Name: " + returnedBookArrayList.get(i).getAuthorName());
            System.out.println("Due Date was: " + returnedBookArrayList.get(i).getDueDate().toString());
            System.out.println("---------------------------------");
        }}
        else System.out.println("You Don't Have Books to Show.");
    }

    public void addToBorrowedBooksList(Book book) {
        book.setDueDate(Date.setDueDate());
        book.setStudentId(id);
        borrowedBooksList.add(book);
        borrowedBooks++;
    }

    public void addToReturnBooksList(Book book) {
        if (Date.brokeDueDate(book.getDueDate())) {
            fines = fines + Integer.parseInt(book.getBreakDueDate());
        }
        returnedBookArrayList.add(book);
        borrowedBooks--;
        borrowedBooksList.remove(book);
    }

    public void addToLostBooksList(Book book) {
        fines = fines + Integer.parseInt(book.getLost());
        lostBookArrayList.add(book);
        borrowedBooksList.remove(book);
        borrowedBooks--;
    }

    public static void BorrowBook(Student student, Library library, Book book) {
        if (student.getBorrowedBooks() < student.maximumNumberOfBooksBorrowed && book.getNumOfCopies() > 1) {
            if (library.searchBookByISPN(book.getISBN()) instanceof Book) {
                student.addToBorrowedBooksList(book);
                library.addToBorrowedBooksList(book);
            } else System.out.println("Sorry the Book isn't available right now.");
        } else System.out.println("Sorry the Book isn't available right now.");
    }

    public static void returnBook(Student student, Library library, Book book) {
        if (student.searchBookByISPN(book.getISBN()) instanceof Book) {
            student.addToReturnBooksList(book);
            library.returnBook(book);
        } else System.out.println("That book isn't with you.");
    }

    public static void lostBook(Student student, Library library, Book book) {
        if (student.searchBookByISPN(book.getISBN()) instanceof Book) {
            student.addToLostBooksList(book);
        } else System.out.println("That book isn't with you.");
    }

    private String generateId() {
        Random random = new Random();
        return "SD" + String.format("%04d", random.nextInt(10000));
    }

    public static void saveStudentToFile(Student student) {
        try {
            FileWriter writer = new FileWriter("Students.txt", true);
            writer.write(student.saveStyle());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> loadStudentsFromFile() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Students.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("/");
                ArrayList<Book> borrowedBooksList = new ArrayList<>();
                parts[6] = parts[6].replace("[", "").replace("]", "");
                if (!parts[6].equals("")) {
                    String[] borrowedBooks = parts[6].split("\\),");
                    for (int i = 0; i < borrowedBooks.length; i++) {
                        borrowedBooks[i] = borrowedBooks[i].replace("(", "");
                        for (int j = 0; j < 3; j++) {
                            borrowedBooks[i] = borrowedBooks[i].replace(" ", "");
                        }
                        borrowedBooks[i] = borrowedBooks[i].replace(")", "");
                    }
                    for (String borrowedBook : borrowedBooks) {
                        String[] bookParts = borrowedBook.split(",");
                        Book book = new Book(bookParts[0], bookParts[1], bookParts[2], Date.fromStringtoDate(bookParts[3]), Integer.parseInt(bookParts[4]), Date.fromStringtoDate(bookParts[5]), bookParts[6], bookParts[7], bookParts[8]);
                        borrowedBooksList.add(book);
                    }
                }

                ArrayList<Book> lostBookArrayList = new ArrayList<>();
                parts[7] = parts[7].replace("[", "").replace("]", "");
                if (!parts[7].equals("")) {
                    String[] lostBooks = parts[7].split("\\),");
                    for (int i = 0; i < lostBooks.length; i++) {
                        lostBooks[i] = lostBooks[i].replace("(", "");
                        lostBooks[i] = lostBooks[i].replace(")", "");
                    }
                    for (String lostBook : lostBooks) {
                        String[] bookParts = lostBook.split(",");
                        Book book = new Book(bookParts[0], bookParts[1], bookParts[2], Date.fromStringtoDate(bookParts[3]), Integer.parseInt(bookParts[4]), Date.fromStringtoDate(bookParts[5]), bookParts[6], bookParts[7], bookParts[8]);
                        lostBookArrayList.add(book);
                    }
                }

                ArrayList<Book> returnedBookArrayList = new ArrayList<>();
                parts[8] = parts[8].replace("[", "").replace("]", "");
                if (!parts[8].equals("")) {
                    String[] returnedBooks = parts[8].split("\\),");
                    for (int i = 0; i < returnedBooks.length; i++) {
                        returnedBooks[i] = returnedBooks[i].replace("(", "");
                        returnedBooks[i] = returnedBooks[i].replace(")", "");
                    }
                    for (String returnedBook : returnedBooks) {
                        String[] bookParts = returnedBook.split(",");
                        Book book = new Book(bookParts[0], bookParts[1], bookParts[2], Date.fromStringtoDate(bookParts[3]), Integer.parseInt(bookParts[4]), Date.fromStringtoDate(bookParts[5]), bookParts[6], bookParts[7], bookParts[8]);
                        returnedBookArrayList.add(book);
                    }
                }

                Student student = new Student(parts[2], parts[0], parts[1], parts[3], Integer.parseInt(parts[4]), borrowedBooksList, lostBookArrayList, returnedBookArrayList, Integer.parseInt(parts[9]));

                students.add(student);
            }
            br.close();
            FileWriter writer = new FileWriter("Students.txt");
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return students;
    }

    public void showInfo(){
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("email: " + email);
        System.out.println("Borrowed Books Numbers: " + borrowedBooks);
    }

    public String saveStyle() {
        return email + "/" +
                password + "/" +
                name + "/" +
                id + "/" +
                borrowedBooks + "/" +
                maximumNumberOfBooksBorrowed + "/" +
                borrowedBooksList.toString() + "/" +
                lostBookArrayList.toString() + "/" +
                returnedBookArrayList.toString() + "/" +
                fines + "\n";
    }

    @Override
    public String toString() {
        return name + "," +
                id + "," +
                email + "," +
                password + "," +
                borrowedBooks + "," +
                maximumNumberOfBooksBorrowed + "," +
                borrowedBooksList.toString() + "," +
                lostBookArrayList.toString() + "," +
                returnedBookArrayList.toString() + "," +
                fines + "\n";
    }
}
