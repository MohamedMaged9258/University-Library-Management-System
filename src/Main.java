import Classes.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> studentArrayList = Student.loadStudentsFromFile();
        ArrayList<Staff> staffArrayList = Staff.loadStaffFromFile();
        ArrayList<Book> bookArrayList = Book.loadBooksFromFile();
        ArrayList<Book> lostBookArrayList = Book.loadLostBooksFromFile();
        ArrayList<Book> borrowedBookArrayList = Book.loadBorrowedBooksFromFile();
        Student student = new Student();
        Staff staff = new Staff();
        Library library = new Library(studentArrayList, staffArrayList, bookArrayList, lostBookArrayList, borrowedBookArrayList);

        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                1.Sign in.
                2.Sign Up.
                """);
        int x = scanner.nextInt();
        scanner.nextLine();
        if (x == 1) {
            String user = sign_in(studentArrayList, staffArrayList);
            String s = user.charAt(0) + String.valueOf(user.charAt(1));
            if (s.equals("SD")) {
                int temp = Integer.parseInt(user.split("D")[1]);
                student = studentArrayList.get(temp);
                studentArrayList.remove(studentArrayList.get(temp));
            } else if (s.equals("ST")) {
                int temp = Integer.parseInt(user.split("T")[1]);
                staff = staffArrayList.get(temp);
                staffArrayList.remove(staffArrayList.get(temp));
                x = 2;
            }
        } else if (x == 2) {
            Object user = sign_up();
            if (user instanceof Student) {
                student = (Student) user;
                x = 1;
            } else if (user instanceof Staff) {
                staff = (Staff) user;
            }
        }

        boolean running = true;
        if (x == 1) {
            System.out.println(student);
            while (running) {
                System.out.println("""
                        \n
                        0.Quit
                        1.Your Borrowed Books
                        2.Your Returned Books
                        3.Borrow Book
                        4.Return Book
                        5.Lost Book
                        6.Show Fines
                        """);
                x = scanner.nextInt();
                switch (x) {
                    case 0 -> {
                        studentArrayList.add(student);
                        for (Student value : studentArrayList) {
                            Student.saveStudentToFile(value);
                        }
                        Library.saveNewFiles(library);
                        running = false;
                    }
                    case 1 -> student.presentBorrowedBooks();
                    case 2 -> student.presentReturnedBooks();
                    case 3 -> {
                        library.presentBooks();
                        System.out.print("Please Choose Book Number: ");
                        int y = scanner.nextInt();
                        Student.BorrowBook(student, library, library.getBookArrayList().get(y - 1));
                    }
                    case 4 -> {
                        student.presentBorrowedBooks();
                        System.out.print("Please Choose Book Number: ");
                        int y = scanner.nextInt();
                        Student.returnBook(student, library, student.getBorrowedBooksList().get(y - 1));
                    }
                    case 5 -> {
                        student.presentBorrowedBooks();
                        System.out.print("Please Choose Book Number: ");
                        int y = scanner.nextInt();
                        Student.lostBook(student, library, student.getBorrowedBooksList().get(y - 1));
                    }
                    case 6 -> System.out.println("You have to pay: " + student.getFines());
                    default -> System.out.println("Please choose a number from the list.🤨");
                }
            }
        }
    }

    public static String sign_in(ArrayList<Student> students, ArrayList<Staff> staffArrayList) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please Enter your ID: ");
            String Id = scanner.next();
            if ((Id.charAt(0) + String.valueOf(Id.charAt(1))).equals("SD")) {
                System.out.print("Please Enter Your Password: ");
                String password = scanner.next();
                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getId().equals(Id)) {
                        if (students.get(i).getPassword().equals(password)) {
                            System.out.println("Sign In Success.👌");
                            System.out.println("Welcome " + students.get(i).getName());
                            return "SD" + i;
                        } else System.out.println("The Password Is wrong.🤨");
                    } else if (students.size() - 1 == i) {
                        System.out.println("Please Check your ID and try again.😊");
                    }
                }
            } else if ((Id.charAt(0) + String.valueOf(Id.charAt(1))).equals("ST")) {
                System.out.print("Please Enter Your Password: ");
                String password = scanner.next();
                for (int i = 0; i < staffArrayList.size(); i++) {
                    if (staffArrayList.get(i).getId().equals(Id)) {
                        if (staffArrayList.get(i).getPassword().equals(password)) {
                            System.out.println("Sign In Success.👌");
                            System.out.println("Welcome " + staffArrayList.get(i).getName());
                            return "ST" + i;
                        } else System.out.println("The Password Is wrong.🤨");
                    } else if (students.size() - 1 == i) {
                        System.out.println("Please Check your ID and try again.😊");
                    }
                }
            } else System.out.println("Please Check your ID and try again.😊");
        }
    }

    public static Object sign_up() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to your University Library \n");
        while (true) {
            System.out.println("""
                    1.Librarian
                    2.Student
                    """);
            int x = scanner.nextInt();
            scanner.nextLine();
            if (x == 1) {
                System.out.print("Please Enter Your Name: ");
                String name = scanner.nextLine();
                System.out.print("Please Enter Your Email: ");
                String email = scanner.nextLine();
                System.out.print("Please Enter Your Password: ");
                String password = scanner.next();
                Staff.saveStaffToFile(new Staff(name, email, password));
                return new Staff(name, email, password);
            } else if (x == 2) {
                System.out.print("Please Enter Your Name: ");
                String name = scanner.nextLine();
                System.out.print("Please Enter Your Email: ");
                String email = scanner.nextLine();
                System.out.print("Please Enter Your Password: ");
                String password = scanner.next();
                return new Student(name, email, password);
            } else System.out.println("Please try again.");
        }
    }

}