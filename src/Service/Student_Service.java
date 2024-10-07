package Service;

import Classes.Date;
import Classes.Library;
import Classes.Student;
import DataBase.Helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Student_Service {
    Student student;

    public Student_Service(Student student) {
        this.student = student;
    }

    public static void newStudent(Student student) {
        String query = "insert into student (ID, Email, Name, Password)" + "values ('" + student.getId() + "', '" + student.getEmail() + "', '" + student.getName() + "', '" + student.getPassword() + "')";
        Helper.executeUpdate(query);
    }

    public static Student getStudent(String userId, String password) {
        String query = "select * from main.student where id= ? and password= ? ";
        Connection connection = Helper.getConnection();
        ResultSet resultSet;
        Student student;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
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

    public void presentBorrowedBooks() {
        String query = "select * from borrowed_books join books on borrowed_books.ISBN = books.ISBN where student_id='" + student.getId() + "'";
        Library.listBooks(query);
    }

    public void presentLostBooks() {
        String query = "select * from lost_books join books on lost_books.ISBN = books.ISBN where student_id='" + student.getId() + "'";
        Library.listBooks(query);
    }

    public void presentReturnedBooks() {
        String query = "select * from returned_books join books on returned_books.ISBN = books.ISBN where student_id='" + student.getId() + "'";
        Library.listBooks(query);
    }

    public void BorrowBook(int ISBN) {
        String query = "select * from books where ISBN = '" + ISBN + "'";
        ResultSet resultSet = Helper.executeQuery(query);
        try {
            if (!resultSet.isBeforeFirst()) {
                System.out.println("That book isn't with you.");
            } else if (student.getBorrowedBooks() < student.getMaximumNumberOfBooksBorrowed()) {
                resultSet.next();
                if (Integer.parseInt(resultSet.getString("copies")) > 1) {
                    query = "insert into borrowed_books (ISBN, Due_Date, Student_ID) values (" + ISBN + ", '" + Date.setDueDate() + "', '" + student.getId() + "');";
                    Helper.executeUpdate(query);
                    query = "update books set Copies = Copies -1 where ISBN = '" + ISBN + "'";
                    Helper.executeUpdate(query);
                    query = "update student set Num_of_Borrowed_books = Num_of_Borrowed_books +1 where id = '" + student.getId() + "';";
                    Helper.executeUpdate(query);
                    student.setBorrowedBooks(student.getBorrowedBooks() + 1);
                } else System.out.println("Sorry the Book isn't available right now.");
            } else System.out.println("Sorry the Book isn't available right now.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void returnBook(int ISBN) {
        String query = "select * from books inner join borrowed_books on borrowed_books.isbn = books.isbn where student_id = '" + student.getId() + "';";
        ResultSet resultSet = Helper.executeQuery(query);
        try {
            if (!resultSet.isBeforeFirst()) {
                System.out.println("That book isn't with you.");
            } else {
                resultSet.next();
                query = "insert into returned_books (ISBN, Student_ID) values (" + ISBN + ", '" + student.getId() + "');";
                Helper.executeUpdate(query);
                query = "update books set Copies = Copies + 1 where ISBN = '" + ISBN + "';";
                Helper.executeUpdate(query);
                query = "update student set Num_of_Borrowed_books = Num_of_Borrowed_books - 1 where id = '" + student.getId() + "';";
                Helper.executeUpdate(query);
                query = "delete from borrowed_books where student_id='" + student.getId() + "' and isbn = '" + ISBN + "';";
                Helper.executeUpdate(query);
                student.setBorrowedBooks(student.getBorrowedBooks() - 1);
                if (Date.brokeDueDate(Date.fromStringtoDate(resultSet.getString("due_date")))) {
                    student.setFines(student.getIntFines() + Integer.parseInt(resultSet.getString("delay_fine")));
                    query = "update student set fines = " + student.getIntFines() + " where student_id='" + student.getId() + "';";
                    Helper.executeUpdate(query);
                    query = "insert into fines (ISBN, Student_ID, Fine_Type) values ('" + ISBN + "', '" + student.getId() + "', 'DueDate Break');";
                    Helper.executeUpdate(query);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void lostBook(int ISBN) {
        String query = "select * from books inner join borrowed_books on borrowed_books.isbn = books.isbn where student_id = '" + student.getId() + "';";
        ResultSet resultSet = Helper.executeQuery(query);
        try {
            if (!resultSet.isBeforeFirst()) {
                System.out.println("That book isn't with you.");
            } else {
                resultSet.next();
                query = "insert into lost_books (ISBN, Student_ID) values (" + ISBN + ", '" + student.getId() + "');";
                Helper.executeUpdate(query);
                query = "update student set Num_of_Borrowed_books = Num_of_Borrowed_books - 1 where id = '" + student.getId() + "';";
                Helper.executeUpdate(query);
                query = "delete from borrowed_books where student_id='" + student.getId() + "' and isbn = '" + ISBN + "';";
                Helper.executeUpdate(query);
                student.setFines(student.getIntFines() + Integer.parseInt(resultSet.getString("lost_fine")));
                query = "update student set fines = " + student.getIntFines() + " where id='" + student.getId() + "';";
                Helper.executeUpdate(query);
                query = "insert into fines (ISBN, Student_ID, Fine_Type) values ('" + ISBN + "', '" + student.getId() + "', 'Lost The Book');";
                Helper.executeUpdate(query);
                student.setBorrowedBooks(student.getBorrowedBooks() - 1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
