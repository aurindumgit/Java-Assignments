package LibrarySystem;

class Book {
    static int nextBookNumber = 500;

    String bookId;
    String title;
    String author;
    boolean isAvailable;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.bookId = "BOOK-" + nextBookNumber++;
        Library.totalBooks++;
    }

    void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("\nSUCCESS: BOOK \"" + title + "\" BORROWED.");
        } else {
            System.out.println("\nUNAVAILABLE: BOOK \"" + title + "\" IS CURRENTLY NOT AVAILABLE.");
        }
    }

    void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("\nSUCCESS: BOOK \"" + title + "\" RETURNED.");
        } else {
            System.out.println("\nINVALID RETURN: BOOK \"" + title + "\" WAS NOT BORROWED.");
        }
    }

    void displayBookInfo() {
        System.out.println("\n===== BOOK DETAILS =====");
        System.out.println("BOOK ID : " + bookId);
        System.out.println("TITLE   : " + title);
        System.out.println("AUTHOR  : " + author);
        System.out.println("STATUS  : " + (isAvailable ? "AVAILABLE" : "NOT AVAILABLE"));
    }
}

public class Library {

    static int totalBooks = 0;
    static String libraryName = "Central Library";
    static int maxBorrowLimit = 3;

    static void displayLibraryStats() {
        System.out.println("\n===== LIBRARY STATS =====");
        System.out.println("LIBRARY NAME     : " + libraryName);
        System.out.println("TOTAL BOOKS      : " + totalBooks);
        System.out.println("MAX BORROW LIMIT : " + maxBorrowLimit);
    }

    static void updateBorrowLimit(int newLimit) {
        maxBorrowLimit = newLimit;
        System.out.println("\nUPDATE: MAX BORROW LIMIT CHANGED TO " + maxBorrowLimit);
    }

    static int getTotalBooks() {
        return totalBooks;
    }

    public static void main(String[] args) {

        System.out.println("===== LIBRARY MANAGEMENT SYSTEM =====");

        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee");
        Book book3 = new Book("1984", "George Orwell");

        book1.displayBookInfo();
        book1.borrowBook();
        book1.borrowBook();
        book1.returnBook();

        book2.returnBook();
        book2.borrowBook();
        book2.returnBook();

        book3.displayBookInfo();

        displayLibraryStats();
        updateBorrowLimit(5);

        System.out.println("\nTOTAL BOOKS IN LIBRARY: " + getTotalBooks());
    }
}
