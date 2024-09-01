package org.example;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

interface BookManager {
    boolean addBookDetails(Library library, Map<String, Map<String, String>> bookDetails);
}
public class BookManagement implements BookManager{
    //Add Functionality of LibraryManagementSystem
    public boolean addBookDetails(Library book, Map<String, Map<String, String>> bookOfLib) {
        LocalDate localDate = LocalDate.now();
        String isbn = book.generateISBN();
        int currentSize = bookOfLib.size();

        Map<String, String> bookDetails = new HashMap<>();
        bookDetails.put("title", book.getTitle().trim().toLowerCase());
        bookDetails.put("author", book.getAuthor().trim());
        bookDetails.put("publisherYear", book.getPublisherYear().trim());
        bookDetails.put("isBorrow", "false"); // Initially not borrowed

        bookOfLib.put(isbn, bookDetails);
        if (currentSize + 1 == bookOfLib.size()) {
            return true; // Indicating success
        }
        throw new RuntimeException("Error while adding new book");
    }
}
