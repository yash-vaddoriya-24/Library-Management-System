package org.example;

import java.util.Map;

interface BookRepo{
    void viewAvailableBooks(Map<String, Map<String, String>> books);
    void viewBorrowedBooks(Map<String, Map<String, String>> books);
    boolean SearchBook(String title, Map<String, Map<String, String>> books);
}
class BookAlreadyBorrowedException extends RuntimeException {
    public BookAlreadyBorrowedException(String message) {
        super(message);
    }
}

public class viewBooks implements BookRepo {
    // Displays books available in the library
    public void viewAvailableBooks(Map<String, Map<String, String>> BookOfLib){
        System.out.println("Available Books Details:");
        printHeader();
        for (Map.Entry<String, Map<String, String>> entry : BookOfLib.entrySet()) {
            if(!Boolean.parseBoolean(entry.getValue().get("isBorrow"))) {
                printBookDetails(entry);
            }
        }
    }
    // Prints the header for the book table
    private void printHeader(){
        System.out.printf("%-15s | %-30s | %-20s | %-30s%n", "ISBN", "TITLE", "AUTHOR", "PUBLISHER YEAR");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
    }

    // Prints the details of a book
    private void printBookDetails(Map.Entry<String, Map<String, String>> entry){
        String isbn = entry.getKey();
        Map<String, String> bookDetails = entry.getValue();
        System.out.printf("%-15s | %-30s | %-20s | %-30s%n", isbn, bookDetails.get("title"), bookDetails.get("author"), bookDetails.get("publisherYear"));
    }

    //Displays books that have been borrowed
    public void viewBorrowedBooks(Map<String, Map<String, String>> BookOfLib){
        System.out.println("Borrow Books Details:");
        printHeader();
        for (Map.Entry<String, Map<String, String>> entry : BookOfLib.entrySet()) {
            if(Boolean.parseBoolean(entry.getValue().get("isBorrow"))) {
                printBookDetails(entry);
            }
        }
    }

    public boolean SearchBook(String title, Map<String, Map<String, String>> bookOfLib) {
        title = title.trim().toLowerCase();
        if(title.isEmpty()){
            throw new RuntimeException("Title cannot be empty");
        }
        for (Map.Entry<String, Map<String, String>> entry : bookOfLib.entrySet()) {
            Map<String, String> bookDetails = entry.getValue();
            String bookTitle = bookDetails.get("title").toLowerCase();

            if (title.equals(bookTitle)) {
                boolean isBorrowed = Boolean.parseBoolean(bookDetails.get("isBorrow"));
                if (!isBorrowed) {
                    printHeader();
                    printBookDetails(entry);
                    return true; // Book found and available
                } else {
                    throw new BookAlreadyBorrowedException("The book '" + title + "' is already borrowed.");
                }
            }
        }

        throw new BookNotFoundException("Book with the title '" + title + "' not found.");
    }
}
