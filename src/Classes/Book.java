package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Book {
    private String title;
    private String authorName;
    private int ISBN;
    private int numOfCopies;
    private Date publicationDate = new Date("0000", "00", "00");
    private Date dueDate = new Date("0000", "00", "00");
    private boolean borrowed;

    public Book() {
    }

    public Book(String title, String authorName, int ISBN, Date publicationDate, int numOfCopies) {
        this.title = title;
        this.authorName = authorName;
        this.ISBN = ISBN;
        this.publicationDate = publicationDate;
        this.numOfCopies = numOfCopies;
    }

    public Book(String title, String authorName, int ISBN, Date publicationDate, boolean borrowed, int numOfCopies, Date dueDate) {
        this.title = title;
        this.authorName = authorName;
        this.ISBN = ISBN;
        this.publicationDate = publicationDate;
        this.borrowed = borrowed;
        this.numOfCopies = numOfCopies;
        this.dueDate = dueDate;
    }

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

    public int getISBN() {
        return ISBN;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isBorrowed() {
        if (borrowed) {
            System.out.println("The Classes.Book is Borrowed");
            return true;
        } else {
            System.out.println("The Classes.Book is Available");
            return false;
        }
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setNumOfCopies(int numOfCopies) {
        this.numOfCopies = numOfCopies;
    }

    public static void saveBookToFile(Book book) {
        try {
            FileWriter writer = new FileWriter("Books.txt");
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
                Book book = new Book(parts[0], parts[1], Integer.parseInt(parts[2]), Date.fromStringtoDate(parts[3]), Boolean.getBoolean(parts[4]), Integer.parseInt(parts[5]), Date.fromStringtoDate(parts[6]));
                bookArrayList.add(book);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return bookArrayList;
    }

    public static void saveLostBookToFile(Book book) {
        try {
            FileWriter writer = new FileWriter("LostBooks.txt");
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
                Book book = new Book(parts[0], parts[1], Integer.parseInt(parts[2]), Date.fromStringtoDate(parts[3]), Boolean.getBoolean(parts[4]), Integer.parseInt(parts[5]), Date.fromStringtoDate(parts[6]));
                bookArrayList.add(book);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return bookArrayList;
    }

    public static void saveBorrowedBookToFile(Book book) {
        try {
            FileWriter writer = new FileWriter("BorrowedBooks.txt");
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
                Book book = new Book(parts[0], parts[1], Integer.parseInt(parts[2]), Date.fromStringtoDate(parts[3]), Boolean.getBoolean(parts[4]), Integer.parseInt(parts[5]), Date.fromStringtoDate(parts[6]));
                bookArrayList.add(book);
            }
            br.close();
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
                "," + borrowed +
                "," + numOfCopies +
                "," + dueDate.saveStyle() +
                ')';
    }

    @Override
    public String toString() {
        return title +
                "," + authorName +
                "," + ISBN +
                "," + publicationDate +
                "," + borrowed +
                "," + numOfCopies +
                "," + dueDate;
    }
}
