package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Book {
    private String title;
    private String authorName;
    private String studentId;
    private String breakDueDate;
    private String lost;
    private String ISBN;
    int numOfCopies;
    private Date publicationDate = new Date("0000", "00", "00");
    private Date dueDate = new Date("0000", "00", "00");

    //Constructors
    public Book() {
    }

    public Book(String title, String authorName, String ISBN, String publicationDate, String dueDate, String lost) {
        this.title = title;
        this.authorName = authorName;
        this.ISBN = ISBN;
        this.publicationDate = Date.fromStringtoDate(publicationDate);
        this.dueDate = Date.fromStringtoDate(dueDate);
        this.lost = lost;
    }

    public Book(String title, String authorName, String ISBN, Date publicationDate, int numOfCopies, Date dueDate, String studentId, String breakDueDate, String lost) {
        this.title = title;
        this.authorName = authorName;
        this.ISBN = ISBN;
        this.publicationDate = publicationDate;
        this.numOfCopies = numOfCopies;
        this.dueDate = dueDate;
        this.studentId = studentId;
        this.breakDueDate = breakDueDate;
        this.lost = lost;
    }

    //Getters
    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void getDate() {
        System.out.println(publicationDate);
    }

    public int getNumOfCopies() {
        return numOfCopies;
    }

    public String getISBN() {
        return ISBN;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getBreakDueDate() {
        return breakDueDate;
    }

    public String getLost() {
        return lost;
    }

    //Setters
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setNumOfCopies(int numOfCopies) {
        this.numOfCopies = numOfCopies;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }


    //Methods
    public static void saveBookToFile(Book book, boolean append) {
        try {
            FileWriter writer = new FileWriter("Books.txt", append);
            writer.write(book.saveStyle() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Book> loadBooksFromFile() {
        ArrayList<Book> bookArrayList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Books.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].replace("(", "");
                    parts[i] = parts[i].replace(")", "");
                }
                Book book = new Book(parts[0], parts[1], parts[2], Date.fromStringtoDate(parts[3]), Integer.parseInt(parts[4]), Date.fromStringtoDate(parts[5]), parts[6], parts[7], parts[8]);
                bookArrayList.add(book);
            }
            br.close();
            FileWriter writer = new FileWriter("Books.txt");
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return bookArrayList;
    }

    public static void saveLostBookToFile(Book book, boolean append) {
        try {
            FileWriter writer = new FileWriter("LostBooks.txt", append);
            writer.write(book.saveStyle() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Book> loadLostBooksFromFile() {
        ArrayList<Book> bookArrayList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("LostBooks.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].replace("(", "");
                    parts[i] = parts[i].replace(")", "");
                }
                Book book = new Book(parts[0], parts[1], parts[2], Date.fromStringtoDate(parts[3]), Integer.parseInt(parts[4]), Date.fromStringtoDate(parts[5]), parts[6], parts[7], parts[8]);
                bookArrayList.add(book);
            }
            br.close();
            FileWriter writer = new FileWriter("LostBooks.txt");
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return bookArrayList;
    }

    public static void saveBorrowedBookToFile(Book book, boolean append) {
        try {
            FileWriter writer = new FileWriter("BorrowedBooks.txt", append);
            writer.write(book.saveStyle() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Book> loadBorrowedBooksFromFile() {
        ArrayList<Book> bookArrayList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("BorrowedBooks.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].replace("(", "");
                    parts[i] = parts[i].replace(")", "");
                }
                Book book = new Book(parts[0], parts[1], parts[2], Date.fromStringtoDate(parts[3]), Integer.parseInt(parts[4]), Date.fromStringtoDate(parts[5]), parts[6], parts[7], parts[8]);
                bookArrayList.add(book);
            }
            br.close();
            FileWriter writer = new FileWriter("BorrowedBooks.txt");
            writer.write("");
            writer.close();

        } catch (IOException e) {
            System.out.println(e);
        }
        return bookArrayList;
    }

    public String saveStyle() {
        return "(" +
                title +
                "," + authorName +
                "," + ISBN +
                "," + publicationDate.saveStyle() +
                "," + numOfCopies +
                "," + dueDate.saveStyle() +
                "," + studentId +
                "," + breakDueDate +
                "," + lost +
                ')';
    }

    @Override
    public String toString() {
        return "(" +
                title +
                "," + authorName +
                "," + ISBN +
                "," + publicationDate.saveStyle() +
                "," + numOfCopies +
                "," + dueDate.saveStyle() +
                "," + studentId +
                "," + breakDueDate +
                "," + lost +
                ')';
    }
}
