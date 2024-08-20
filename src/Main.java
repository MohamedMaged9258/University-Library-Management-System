import Classes.Book;
import Classes.Librarian;
import Classes.Library;
import Classes.Student;
import DataBase.Helper;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //        public static void main(String[] args) {
//            Helper helper = new Helper();
//            Librarian librarian = new Librarian("temp", "temp@gmail.com", "12345");
//            helper.newLibrarian(librarian);
//        }
    public static void main(String[] args) {
        ArrayList<Student> studentArrayList = Student.loadStudentsFromFile();
        ArrayList<Librarian> librarianArrayList = Librarian.loadLibrarianFromFile();
        ArrayList<Book> bookArrayList = Book.loadBooksFromFile();
        ArrayList<Book> lostBookArrayList = Book.loadLostBooksFromFile();
        ArrayList<Book> borrowedBookArrayList = Book.loadBorrowedBooksFromFile();

        Student student = new Student();
        Librarian librarian = new Librarian();
        Library library = new Library(studentArrayList, librarianArrayList, bookArrayList, lostBookArrayList, borrowedBookArrayList);

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
                        4.Borrow Book
                        5.Return Book
                        6.Lost Book
                        7.Show Fines
                        8.Search by ISBN
                        """);
                System.out.print("Choose A Number: ");
                x = scanner.nextInt();
                switch (x) {
                    case 0 -> {
                        library.addToStudentList(student);
                        Library.saveNewFiles(library);
                        System.out.println("Please Remember that your ID is: " + student.getId());
                        running = false;
                    }
                    case 1 -> student.showInfo();
                    case 2 -> student.presentBorrowedBooks();
                    case 3 -> student.presentReturnedBooks();
                    case 4 -> {
                        library.presentBooks();
                        System.out.print("Please Choose Book Number: ");
                        int y = scanner.nextInt();
                        Student.BorrowBook(student, library, library.getBookArrayList().get(y - 1));
                    }
                    case 5 -> {
                        student.presentBorrowedBooks();
                        System.out.print("Please Choose Book Number: ");
                        int y = scanner.nextInt();
                        Student.returnBook(student, library, student.getBorrowedBooksList().get(y - 1));
                    }
                    case 6 -> {
                        student.presentBorrowedBooks();
                        System.out.print("Please Choose Book Number: ");
                        int y = scanner.nextInt();
                        Student.lostBook(student, library, student.getBorrowedBooksList().get(y - 1));
                    }
                    case 7 -> System.out.println("You have to pay: " + student.getFines());
                    case 8 -> {
                        System.out.print("Enter ISBN: ");
                        String isbn = scanner.next();
                        System.out.println();
                        if (library.searchBookByISPN(isbn) instanceof Book && ((Book) library.searchBookByISPN(isbn)).getNumOfCopies() > 1) {
                            library.presentBook((Book) library.searchBookByISPN(isbn));
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
                        library.addToLibrarianList(librarian);
                        Library.saveNewFiles(library);
                        System.out.println("Please Remember that your ID is: " + librarian.getId());
                        running = false;
                    }
                    case 1 -> librarian.showInfo();
                    case 2 -> library.addNewBook(Librarian.addNewBook());
                    case 3 -> {
                        library.presentStudents();
                        System.out.print("Please Choose Student Number: ");
                        int y = scanner.nextInt();
                        System.out.println();
                        Librarian.checkStudentBorrowedBooks(studentArrayList.get(y - 1));
                    }
                    case 4 -> library.presentBorrowedBooks();
                    case 5 -> library.presentLostBooks();
                    case 6 -> library.presentBooks();
                    case 7 -> library.sortBooksByTitle();
                    case 8 -> library.sortBooksByAuthorName();
                    case 9 -> library.sortBooksByISBN();
                    default -> System.out.println("Please choose a number from the list.🤨");
                }
            }
        }
    }

    public static Object sign_in() {
//        new SignInGUI(students, librarianArrayList);
//        return null;
        Helper helper = new Helper();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please Enter your ID: ");
            String Id = scanner.next();
            if ((Id.charAt(0) + String.valueOf(Id.charAt(1))).equals("SD")) {
                System.out.print("Please Enter Your Password: ");
                String password = scanner.next();
                Object student = helper.getStudent(Id, password);
                if (student instanceof Student) {
                    return student;
                } else {
                    System.out.println("Please Check your ID Or Password and try again.😊");
                }
            } else if ((Id.charAt(0) + String.valueOf(Id.charAt(1))).equals("ST")) {
                System.out.print("Please Enter Your Password: ");
                String password = scanner.next();
                Object librarian = helper.getLibrarian(Id, password);
                if (librarian instanceof Librarian) {
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
                helper.newLibrarian(librarian);
                return librarian;
            } else if (x == 2) {
                System.out.print("Please Enter Your Name: ");
                String name = scanner.nextLine();
                System.out.print("Please Enter Your Email: ");
                String email = scanner.nextLine();
                System.out.print("Please Enter Your Password: ");
                String password = scanner.next();
                Student student = new Student(name, email, password);
                helper.newStudent(student);
                return student;
            } else System.out.println("Please try again.");
        }
    }

}