package Classes;

import DataBase.Helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Student extends User {
    private int borrowedBooks;
    private final int maximumNumberOfBooksBorrowed = 5;
    private int fines = 0;
    private ArrayList<Book> borrowedBooksList = new ArrayList<>();
    private ArrayList<Book> lostBookArrayList = new ArrayList<>();
    private ArrayList<Book> returnedBookArrayList = new ArrayList<>();

    public Student() {
    }

    public Student(String name, String email, String password) {
        super(name, email, password);
    }

    public Student(String name, String email, String password, String id) {
        super(name, email, password, id);
    }

    public Student(String name, String email, String password, String id, int borrowedBooks, int fines) {
        super(name, email, password, id);
        this.borrowedBooks = borrowedBooks;
        this.fines = fines;
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

    // Methods
    public static void newStudent(Student student) {
        String query = "insert into student (ID, Email, Name, Password)" + "values ('" + student.getId() + "', '" + student.getEmail() + "', '" + student.getName() + "', '" + student.getPassword() + "')";
        Helper.executeUpdate(query);
    }

    public static Object getStudent(String userId, String password) {
        String query = "select * from student where id='" + userId + "' and password='" + password + "'";
        ResultSet resultSet = Helper.executeQuery(query);
        Object student;
        try {
            if (resultSet.next()) {
                student = new Student(resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("id"), Integer.parseInt(resultSet.getString("Num_of_Borrowed_books")), Integer.parseInt(resultSet.getString("fines")));
            } else {
                student = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    public Object searchBookByISPN(String ISBN) {
        for (Book value : borrowedBooksList) {
            if (value.getISBN().equals(ISBN)) {
                return value;
            }
        }
        return "Not Available";
    }

    public void presentBorrowedBooks() {
        String query = "select * from borrowed_books join books on borrowed_books.ISBN = books.ISBN where student_id='" + getId() + "'";
        Library.listBooks(query);
    }

    public void presentLostBooks() {
        String query = "select * from lost_books join books on lost_books.ISBN = books.ISBN where student_id='" + getId() + "'";
        Library.listBooks(query);
    }

    public void presentReturnedBooks() {
        String query = "select * from returned_books join books on returned_books.ISBN = books.ISBN where student_id='" + getId() + "'";
        Library.listBooks(query);
    }

    public void addToBorrowedBooksList(Book book) {
        book.setDueDate(Date.setDueDate());
        book.setStudentId(getId());
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

    public void BorrowBook(int ISBN) {
        String query = "select * from books where ISBN = '" + ISBN + "'";
        ResultSet resultSet = Helper.executeQuery(query);
        try {
            if (!resultSet.isBeforeFirst()) {
                System.out.println("That book isn't with you.");
            } else if (getBorrowedBooks() < maximumNumberOfBooksBorrowed) {
                resultSet.next();
                if (Integer.parseInt(resultSet.getString("copies")) > 1) {
                    query = "insert into borrowed_books (ISBN, Due_Date, Student_ID) values (" + ISBN + ", '" + Date.setDueDate() + "', '" + getId() + "');";
                    Helper.executeUpdate(query);
                    query = "update books set Copies = Copies -1 where ISBN = '" + ISBN + "'";
                    Helper.executeUpdate(query);
                    query = "update student set Num_of_Borrowed_books = Num_of_Borrowed_books +1 where id = '" + getId() + "';";
                    Helper.executeUpdate(query);
                    borrowedBooks++;
                } else System.out.println("Sorry the Book isn't available right now.");
            } else System.out.println("Sorry the Book isn't available right now.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void returnBook(int ISBN) {
        String query = "select * from books join borrowed_books on books.isbn = borrowed_books.isbn where ISBN = '" + ISBN + "'";
        ResultSet resultSet = Helper.executeQuery(query);
        try {
            if (!resultSet.isBeforeFirst()) {
                System.out.println("That book isn't with you.");
            } else {
                resultSet.next();
                query = "insert into returned_books (ISBN, Student_ID) values (" + ISBN + ", '" + getId() + "');";
                Helper.executeUpdate(query);
                query = "update books set Copies = Copies + 1 where ISBN = '" + ISBN + "';";
                Helper.executeUpdate(query);
                query = "update student set Num_of_Borrowed_books = Num_of_Borrowed_books - 1 where id = '" + getId() + "';";
                Helper.executeUpdate(query);
                query = "delete from borrowed_books where student_id='" + getId() + "' and isbn = '" + ISBN + "';";
                Helper.executeUpdate(query);
                borrowedBooks--;
                if (Date.brokeDueDate(Date.fromStringtoDate(resultSet.getString("due_date")))) {
                    fines += Integer.parseInt(resultSet.getString("delay_fine"));
                    query = "update student set fines = " + fines + " where student_id='" + getId() + "';";
                    Helper.executeUpdate(query);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void lostBook(int ISBN) {
        String query = "select * from books where ISBN = '" + ISBN + "'";
        ResultSet resultSet = Helper.executeQuery(query);
        try {
            if (!resultSet.isBeforeFirst()) {
                System.out.println("That book isn't with you.");
            } else {
                resultSet.next();
                query = "insert into lost_books (ISBN, Student_ID) values (" + ISBN + ", '" + getId() + "');";
                Helper.executeUpdate(query);
                query = "update student set Num_of_Borrowed_books = Num_of_Borrowed_books - 1 where id = '" + getId() + "';";
                Helper.executeUpdate(query);
                query = "delete from borrowed_books where student_id='" + getId() + "' and isbn = '" + ISBN + "';";
                Helper.executeUpdate(query);
                fines += Integer.parseInt(resultSet.getString("lost_fine"));
                query = "update student set fines = " + fines + " where id='" + getId() + "';";
                Helper.executeUpdate(query);
                borrowedBooks--;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateId() {
        Random random = new Random();
        return "SD" + String.format("%04d", random.nextInt(10000));
    }

    public void showInfo() {
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getId());
        System.out.println("email: " + getEmail());
        System.out.println("Borrowed Books Numbers: " + borrowedBooks);
    }

    @Override
    public String toString() {
        return getName() + "," +
                getId() + "," +
                getEmail() + "," +
                getPassword() + "," +
                borrowedBooks + "," +
                maximumNumberOfBooksBorrowed + "," +
                borrowedBooksList.toString() + "," +
                lostBookArrayList.toString() + "," +
                returnedBookArrayList.toString() + "," +
                fines + "\n";
    }
}
