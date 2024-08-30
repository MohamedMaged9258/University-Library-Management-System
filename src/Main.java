import Classes.Book;
import Classes.Librarian;
import Classes.Library;
import Classes.Student;
import DataBase.Helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Helper helper = new Helper();

        Student student = new Student();
        Librarian librarian = new Librarian();
        Library library = new Library();

        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                1.Sign in.
                2.Sign Up.
                """);
        System.out.print("Choose A Number: ");
        int x = scanner.nextInt();
        scanner.nextLine();
        if (x == 1) {
            Object user = sign_in();
            if (user instanceof Student) {
                student = (Student) user;
            } else if (user instanceof Librarian) {
                librarian = (Librarian) user;
                x = 2;
            }
        } else if (x == 2) {
            Object user = sign_up();
            if (user instanceof Student) {
                student = (Student) user;
                x = 1;
            } else if (user instanceof Librarian) {
                librarian = (Librarian) user;
            }
        }

        boolean running = true;
        if (x == 1) {
            while (running) {
                System.out.println("""
                        \n
                        0.Quit
                        1.Show Info
                        2.Your Borrowed Books
                        3.Your Returned Books
                        4.Your Lost Books
                        5.Borrow Book
                        6.Return Book
                        7.Lost Book
                        8.Show Fines
                        9.Search by ISBN
                        """);
                System.out.print("Choose A Number: ");
                x = scanner.nextInt();
                switch (x) {
                    case 0 -> {
                        System.out.println("Please Remember that your ID is: " + student.getId());
                        running = false;
                    }
                    case 1 -> student.showInfo();
                    case 2 -> student.presentBorrowedBooks();
                    case 3 -> student.presentReturnedBooks();
                    case 4 -> student.presentLostBooks();
                    case 5 -> {
                        Library.CheckBooks();
                        System.out.print("Please Choose Book ISBN: ");
                        int y = scanner.nextInt();
                        student.BorrowBook(y);
                    }
                    case 6 -> {
                        student.presentBorrowedBooks();
                        System.out.print("Please Choose Book ISBN: ");
                        int y = scanner.nextInt();
                        student.returnBook(y);
                    }
                    case 7 -> {
                        if (student.getBorrowedBooks() > 0) {
                            student.presentBorrowedBooks();
                            System.out.print("Please Choose Book ISBN: ");
                            int y = scanner.nextInt();
                            student.lostBook(y);
                        } else System.out.println("You have no books");
                    }
                    case 8 -> System.out.println("You have to pay: " + student.getFines());
                    case 9 -> {
                        System.out.print("Enter ISBN: ");
                        int isbn = scanner.nextInt();
                        System.out.println();
                        Book book = library.searchBookByISPN(isbn);
                        if (book != null) {
                            library.presentBook(book);
                        } else System.out.println("This Book isn't Available At This moment");
                    }
                    default -> System.out.println("Please choose a number from the list.🤨");
                }
            }
        } else if (x == 2) {
            while (running) {
                System.out.println("""
                        \n
                        0.Quit
                        1.Show Info
                        2.Add new Book
                        3.Check Student Borrowed Books
                        4.Check Borrowed Books
                        5.Check Lost Books
                        6.Check Books
                        7.Sort By Title
                        8.Sort By Author Name
                        9.Sort By ISBN
                        """);
                System.out.print("Choose A Number: ");
                x = scanner.nextInt();
                System.out.println();
                switch (x) {
                    case 0 -> {
                        System.out.println("Please Remember that your ID is: " + librarian.getId());
                        running = false;
                    }
                    case 1 -> librarian.showInfo();
                    case 2 -> {
                        Librarian.addNewBook();
                    }
                    case 3 -> {
                        Library.presentStudents();
                        System.out.print("Please Choose Student ID: ");
                        String y = scanner.next();
                        System.out.println();
                        Librarian.CheckStudentBorrowedBooksByID(y);
                    }
                    case 4 -> Library.CheckBorrowedBooks();
                    case 5 -> Library.CheckLostBooks();
                    case 6 -> Library.CheckBooks();
                    case 7 -> Library.SortByTitle();
                    case 8 -> Library.SortByAuthorName();
                    case 9 -> Library.SortByISBN();
                    default -> System.out.println("Please choose a number from the list.🤨");
                }
            }
        }
    }

    public static Object sign_in() {
        Helper helper = new Helper();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please Enter your ID: ");
            String Id = scanner.next();
            if ((Id.charAt(0) + String.valueOf(Id.charAt(1))).equals("SD")) {
                System.out.print("Please Enter Your Password: ");
                String password = scanner.next();
                Student student = Student.getStudent(Id, password);
                if (student != null) {
                    return student;
                } else {
                    System.out.println("Please Check your ID Or Password and try again.😊");
                }
            } else if ((Id.charAt(0) + String.valueOf(Id.charAt(1))).equals("ST")) {
                System.out.print("Please Enter Your Password: ");
                String password = scanner.next();
                Librarian librarian = Librarian.getLibrarian(Id, password);
                if (librarian != null) {
                    return librarian;
                } else {
                    System.out.println("Please Check your ID Or Password and try again.😊");
                }
            } else System.out.println("Please Check your ID and try again.😊");
        }
    }

    public static Object sign_up() {
        Scanner scanner = new Scanner(System.in);
        Helper helper = new Helper();
        System.out.println("Welcome to your University Library \n");
        while (true) {
            System.out.println("""
                    1.Librarian
                    2.Student
                    """);
            System.out.print("Choose A Number: ");
            int x = scanner.nextInt();
            System.out.println();
            scanner.nextLine();
            if (x == 1) {
                System.out.print("Please Enter Your Name: ");
                String name = scanner.nextLine();
                System.out.print("Please Enter Your Email: ");
                String email = scanner.nextLine();
                System.out.print("Please Enter Your Password: ");
                String password = scanner.next();
                Librarian librarian = new Librarian(name, email, password);
                Librarian.newLibrarian(librarian);
                return librarian;
            } else if (x == 2) {
                System.out.print("Please Enter Your Name: ");
                String name = scanner.nextLine();
                System.out.print("Please Enter Your Email: ");
                String email = scanner.nextLine();
                System.out.print("Please Enter Your Password: ");
                String password = scanner.next();
                Student student = new Student(name, email, password);
                Student.newStudent(student);
                return student;
            } else System.out.println("Please try again.");
        }
    }
}