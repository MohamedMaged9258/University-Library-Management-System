package Classes;

public class Book {
    private final String title;
    private final String authorName;
    private final String breakDueDate;
    private final String lost;
    private final String ISBN;
    int numOfCopies;
    private final Date publicationDate;

    //Constructors
    public Book(String title, String authorName, String ISBN, String publicationDate, String breakDueDate, String lost) {
        this.title = title;
        this.authorName = authorName;
        this.ISBN = ISBN;
        this.publicationDate = Date.fromStringtoDate(publicationDate);
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

    public int getNumOfCopies() {
        return numOfCopies;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getBreakDueDate() {
        return breakDueDate;
    }

    public String getLost() {
        return lost;
    }

    //Methods
    @Override
    public String toString() {
        return "(" +
                title +
                "," + authorName +
                "," + ISBN +
                "," + publicationDate.saveStyle() +
                "," + numOfCopies +
                "," + breakDueDate +
                "," + lost +
                ')';
    }
}
