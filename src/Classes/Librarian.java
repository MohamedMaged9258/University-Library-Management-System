package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Librarian {
    private String name;
    private String email;
    private String password;
    private String id;

    //Constructors
    public Librarian() {
    }

    public Librarian(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        Random random = new Random();
        this.id = "ST" + String.format("%04d", random.nextInt(10000));
    }

    public Librarian(String name, String email, String password, String id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    //Methods
    public static void checkStudentBorrowedBooks(Student student) {
        ArrayList<Book> books = student.getBorrowedBooksList();
        for (int i = 0; i < books.size(); i++) {
            System.out.println("Book " + (i + 1) + ": ");
            System.out.println("Title: " + books.get(i).getTitle());
            System.out.println("Author Name: " + books.get(i).getAuthorName());
            System.out.println("ISBN: " + books.get(i).getISBN());
            System.out.println("Due Date: " + books.get(i).getDueDate().toString());
        }
        System.out.println();
    }

    public static Book addNewBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author Name: ");
        String authorName = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String ISBN = scanner.next();
        Date publicationDate = Date.enterDate();
        System.out.print("Enter the Number Of Copies: ");
        int numOfCopies = scanner.nextInt();
        System.out.print("Enter break Due Date fines: ");
        String breakDueDate = scanner.next();
        System.out.print("Enter if user lost the book how much he will pay: ");
        String lost = scanner.next();
        return new Book(title, authorName, ISBN, publicationDate, numOfCopies, breakDueDate, lost);
    }

    public static void saveLibrarianToFile(Librarian librarian) {
        try {
            FileWriter writer = new FileWriter("Librarian.txt", true);
            writer.write(librarian.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Librarian> loadLibrarianFromFile() {
        ArrayList<Librarian> librarianArrayList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Librarian.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("/");
                Librarian librarian = new Librarian(parts[0], parts[1], parts[2], parts[3]);
                librarianArrayList.add(librarian);
            }
            br.close();
            FileWriter writer = new FileWriter("Librarian.txt");
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return librarianArrayList;
    }

    public void showInfo() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Email: " + email);
    }

    @Override
    public String toString() {
        return name + "/" +
                email + "/" +
                password + "/" +
                id + "\n";
    }
}
